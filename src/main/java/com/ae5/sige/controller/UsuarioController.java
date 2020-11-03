package com.ae5.sige.controller;

import java.util.ArrayList;
import java.util.List;

import com.ae5.sige.model.Usuario;
import com.ae5.sige.service.UsuarioService;
import com.ae5.sige.exception.UserNotFound;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/AgendaE5")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	private static final Log LOG = LogFactory.getLog(UsuarioController.class);
	private final UsuarioService usuarioService;

	@Autowired
	/**
	 * @author ae5
	 */
	public UsuarioController(final UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * Obtiene la contraseña del usuario mediante su dni.
	 * 
	 * @author ae5
	 */

	@GetMapping("/usuarios")
	public ResponseEntity<Usuario> getUserPassword(@RequestParam("dni") final String dni,
			@RequestParam("password") final String pass) {
		List<String> lista = new ArrayList<>(); 
		List<String> lista1 = new ArrayList<>();
		lista.add("1");
		lista.add("2");
		lista1.add("7");
		final Usuario usuario = usuarioService.getUserBynusuarioAndPassword(dni, pass);
		LOG.info("[SERVER] Buscando usuario: " + dni);
		if (usuario != null) {
			LOG.info("[SERVER] Usuario encontrado: " + usuario.getnombre());
			return ResponseEntity.ok(usuario);
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
	@GetMapping("/{userDni}")
	// @ApiOperation(value = "Find an user", notes = "Return a user by DNI")

	public ResponseEntity<Usuario> userByDni(@PathVariable final String dni) {
		LOG.info("[SERVER] Buscando usuario: " + dni);
		Usuario user;
		try {
			user = usuarioService.findByUsernusuario(dni);
			LOG.info("[SERVER] Usuario encontrado.");
		} catch (UserNotFound u) {
			user = null;
			LOG.error("[SERVER] Usuario no encontrado.");
		}
		return ResponseEntity.ok(user);
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

		Usuario usuario1 = usuarioService.findByUsernusuario(dni);
		if (usuario1 == null) {
			String nombre = null;
			String apellidos = null;
			String correo = null;
			String telefono = null;
			String tipo = "asistente";
			List<String> listaReuniones = new ArrayList<>();
			List<String> listaReunionesNuevas = new ArrayList<>();
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

			usuario1 = new Usuario(contrasena, nombre, apellidos, dni, telefono, correo, tipo, listaReuniones,
					listaReunionesNuevas);
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
	 */
	@DeleteMapping("/{dni}")
	

	public ResponseEntity<Void> deleteUser(@PathVariable final String dni) {
		LOG.info("Delete user " + dni);
		usuarioService.deleteUsuario(dni);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * actualiza un usuario en funcion de su dni.
	 * 
	 * @author ae5
	 * @throws JSONException 
	 */
	@PutMapping("/{dni}")

	public ResponseEntity<Usuario> updateUsuario(@RequestBody final String mensajerecibido,
			@PathVariable final String dni) throws JSONException {
		final JSONObject jso = new JSONObject(mensajerecibido);
		final Usuario usuario = usuarioService.findByUsernusuario(dni);
		if (usuario == null) {
			LOG.info("[SERVER] Error: el usuario no existe.");
			return ResponseEntity.badRequest().build();
		} else {
			try {
				LOG.info("[SERVER] Actualizando usuario...");

				// Depende de los campos que queramos que puedan actualizarse
				final String nombre = jso.getString("nombre");
				final String apellidos = jso.getString("apellidos");
				final String telefono = jso.getString("numTelefono");
				final String correo = jso.getString("correo");
				final String contrasena = jso.getString("contrasena");

				usuario.setdni(dni);
				usuario.setnombre(nombre);
				usuario.setapellidos(apellidos);
				usuario.settelefono(telefono);
				usuario.setcorreo(correo);
				usuario.setContrasena(contrasena);
			} catch (JSONException j) {
				LOG.error("[SERVER] Error en la lectura del JSON.");
				LOG.info(j.getMessage());
				return ResponseEntity.badRequest().build();
			}

			usuarioService.updateUsuario(usuario);
			LOG.info("[SERVER] Usuario actualizada.");
			LOG.info("[SERVER] " + usuario.toString());
			return ResponseEntity.ok().build();
		}
	}


}
