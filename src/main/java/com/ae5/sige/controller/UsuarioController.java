package com.ae5.sige.controller;

import java.util.ArrayList;
import java.util.List;

import com.ae5.sige.model.Reunion;
import com.ae5.sige.model.Usuario;
import com.ae5.sige.service.ReunionServiceInt;
import com.ae5.sige.service.UsuarioService;
import com.ae5.sige.encryption.Encriptacion;
import com.ae5.sige.exception.UserNotFound;
import com.ae5.sige.encryption.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.jni.User;


@RestController
@RequestMapping("/AgendaE5")
@CrossOrigin(origins = {"http://localhost:4200","https://agenda-e5.herokuapp.com"}, allowedHeaders = "*")
public class UsuarioController {

	private static final Log LOG = LogFactory.getLog(UsuarioController.class);
	private final UsuarioService usuarioService;
	private final ReunionServiceInt reunionService;

	@Autowired
	/**
	 * @author ae5
	 */
	public UsuarioController(final UsuarioService usuarioService, final ReunionServiceInt reunionService) {
		this.usuarioService = usuarioService;
		 this.reunionService = reunionService;
	}

	/**
	 * Login de usuario
	 * 
	 * @author ae5
	 */

	@GetMapping("/usuarios")
	public ResponseEntity<String> getUserPassword(@RequestParam("dni") final String dni,
			@RequestParam("password") final String pass) {		
		final String dniEncriptado = Encriptacion.encriptar(dni);
		final String passEncrip = Encriptacion.encriptar(pass);
		LOG.info(dniEncriptado +" "+ passEncrip );
		final Usuario usuario = usuarioService.getUserBynusuarioAndPassword(dniEncriptado, passEncrip);

		LOG.info("[SERVER] Buscando usuario: " + dni);
		if (usuario != null) {
			JSONArray token = Token.createToken(usuario);
			LOG.info("[SERVER] Usuario encontrado: " + usuario.getNombre());
			return ResponseEntity.ok(token.toString());
		} else {
			LOG.info("[SERVER] No se ha encontrado ningún usuario con esos datos.");
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Obtiene un usuario mediante su dni.
	 * 
	 * @author ae5
	 */
	@GetMapping("/perfil/{dni}")
	// @ApiOperation(value = "Find an user", notes = "Return a user by DNI")

	public ResponseEntity<Usuario> userByDni(@PathVariable final String dni) {
		LOG.info("[SERVER] Buscando usuario: " + dni);
		
		try {
			Usuario user = usuarioService.findByUsernusuario(dni);
			LOG.info("[SERVER] Usuario encontrado.");
			return ResponseEntity.ok(user);
		} catch (UserNotFound u) {
			
			LOG.error("[SERVER] Usuario no encontrado.");
			return ResponseEntity.notFound().build();
		}
		
	}

	/**
	 * Registramos un usuario y guardamos ese usuario en la base de datos.
	 * 
	 * @author ae5.
	 * @throws JSONException 
	 */
	@PostMapping()

	public ResponseEntity<Usuario> registrarUsuario(@RequestBody final String usuario) throws JSONException {
		final JSONObject jso = new JSONObject(usuario);
		LOG.info(usuario);
		final String dni = jso.getString("dni");
		final String contrasena = jso.getString("password");
		final String dniEncriptado = Encriptacion.encriptar(dni);
		final String contrasenaEncrip = Encriptacion.encriptar(contrasena);

		Usuario usuario1 = usuarioService.getUserBynusuarioAndPassword(dniEncriptado,contrasenaEncrip);
		if (usuario1 == null) {
			String nombre = null;
			String apellidos = null;
			String correo = null;
			String telefono = null;
			String tipo = "asistente";
			List<String> listaReuniones = new ArrayList<>();
			try {
				LOG.info("[SERVER] Registrando usuario...");
				nombre = jso.getString("nombre");
				apellidos = jso.getString("apellidos");
				telefono = jso.getString("telefono");
				correo = jso.getString("correo");

			} catch (JSONException j) {
				LOG.info("[SERVER] Error en la lectura del JSON.");
				LOG.info(j.getMessage());
				return ResponseEntity.badRequest().build();
			}

			usuario1 = new Usuario(contrasena, nombre, apellidos, dni, telefono, correo, tipo, listaReuniones);
			usuarioService.saveUsuario(usuario1);
			LOG.info("[SERVER] Usuario registrado.");
			LOG.info("[SERVER] " + usuario1.toString());
			return ResponseEntity.ok().build();
		} else {
			LOG.info("[SERVER] Error: El usuario ya está registrado.");
			LOG.info("[SERVER] " + usuario1.toString());
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Borra un usuario en funcion de su dni.
	 * 
	 * @author ae5
	 * @throws Exception 
	 */
	@DeleteMapping("/deleteUser/{dni}")


	public ResponseEntity<Void> deleteUser(@PathVariable final String dni) throws Exception {
		LOG.info("Delete user " + dni);
	 	Usuario user = usuarioService.findByUsernusuario(Encriptacion.encriptar(dni));
	 	 List<String> listaReuniones = user.getListaReuniones();
	 	 while(!listaReuniones.isEmpty()){
	 		String idreunion = listaReuniones.remove(0);
	 		Reunion reunion = reunionService.findByReunionId(idreunion);
	 		List<String> listaAsistentes = reunion.getListaAsistentes();
	 		listaAsistentes.remove(listaAsistentes.indexOf(dni));
	 		reunion.setListaAsistentes(listaAsistentes);
	 		reunionService.updateReunion(reunion);
	 	 }
		usuarioService.deleteUsuario(Encriptacion.encriptar(dni));
		return ResponseEntity.noContent().build();
	}

	/**
	 * actualiza un usuario en funcion de su dni.
	 * 
	 * @author ae5
	 * @throws JSONException 
	 */
	@PutMapping("update/{dni}")

	public ResponseEntity<Usuario> updateUsuario(@RequestBody final String mensajerecibido,
			@PathVariable final String dni) throws JSONException {
		final JSONObject jso = new JSONObject(mensajerecibido);
		final String DniEncriptado = Encriptacion.encriptar(jso.getString("dni"));
		final Usuario usuario = usuarioService.findByUsernusuario(DniEncriptado);
		LOG.info("El json que nos llega es:" + mensajerecibido);
		if (usuario == null) {
			LOG.info("[SERVER] Error: el usuario no existe.");
			return ResponseEntity.badRequest().build();
		} else {
			try {
				LOG.info("[SERVER] Actualizando usuario...");

				// Depende de los campos que queramos que puedan actualizarse
				final String nombre = jso.getString("nombre");
				final String apellidos = jso.getString("apellidos");
				final String telefono = jso.getString("telefono");
				final String correo = jso.getString("correo");
				final String contrasena = jso.getString("contrasena");

				usuario.setNombre(nombre);
				usuario.setApellidos(apellidos);
				usuario.setTelefono(telefono);
				usuario.setCorreo(correo);
				usuario.setContrasena(contrasena);
				
				
				
			} catch (JSONException j) {
				LOG.error("[SERVER] Error en la lectura del JSON.");
				LOG.info(j.getMessage());
				return ResponseEntity.badRequest().build();
			}
			usuarioService.updateUsuario(Encriptacion.encriptarUsuario(usuario));
			LOG.info("[SERVER] Usuario actualizada.");
			LOG.info("[SERVER] " + Encriptacion.encriptarUsuario(usuario).toString());
			return ResponseEntity.ok().build();
		}
	}

	@GetMapping("/admin-usuarios")
	public ResponseEntity<List<Usuario>> verUsuarios(){
		List<Usuario> users = usuarioService.findAll();
		LOG.info("[SERVER] Usuarios encontrados" + users.size());
		for(int i=0; i<users.size();i++)
			LOG.info(users.get(i));
		return ResponseEntity.ok(users);
	}
	
	/**
	 * Registramos un usuario como admin y guardamos ese usuario en la base de datos.
	 * 
	 * @author ae5.
	 * @throws JSONException 
	 */
	
	@PostMapping("/adminregistro")

	public ResponseEntity<Usuario> admin_registrarUsuario(@RequestBody final String usuario) throws JSONException {
		final JSONObject jso = new JSONObject(usuario);
		LOG.info(usuario);
		final String dni = jso.getString("dni");
		final String contrasena = jso.getString("password");
		final String dniEncriptado = Encriptacion.encriptar(dni);
		final String contrasenaEncrip = Encriptacion.encriptar(contrasena);

		Usuario usuario1 = usuarioService.getUserBynusuarioAndPassword(dniEncriptado,contrasenaEncrip);
		if (usuario1 == null) {
			String nombre = null;
			String apellidos = null;
			String correo = null;
			String telefono = null;
			String tipo = null;
			List<String> listaReuniones = new ArrayList<>();
			try {
				LOG.info("[SERVER] Registrando usuario...");
				nombre = jso.getString("nombre");
				apellidos = jso.getString("apellidos");
				telefono = jso.getString("telefono");
				correo = jso.getString("correo");
				tipo = jso.getString("tipo");

			} catch (JSONException j) {
				LOG.info("[SERVER] Error en la lectura del JSON.");
				LOG.info(j.getMessage());
				return ResponseEntity.badRequest().build();
			}

			usuario1 = new Usuario(contrasena, nombre, apellidos, dni, telefono, correo, tipo, listaReuniones);
			usuarioService.saveUsuario(usuario1);
			LOG.info("[SERVER] Usuario registrado.");
			LOG.info("[SERVER] " + usuario1.toString());
			return ResponseEntity.ok().build();
		} else {
			LOG.info("[SERVER] Error: El usuario ya está registrado.");
			LOG.info("[SERVER] " + usuario1.toString());
			return ResponseEntity.badRequest().build();
		}
	}


}
