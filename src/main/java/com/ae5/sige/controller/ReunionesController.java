package com.ae5.sige.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae5.sige.encryption.Encriptacion;
import com.ae5.sige.model.Reunion;
import com.ae5.sige.model.Usuario;
import com.ae5.sige.service.ReunionServiceInt;
import com.ae5.sige.service.UsuarioServiceInt;

@RestController
@RequestMapping("/AgendaE5")
@CrossOrigin(origins = {"http://localhost:4200","https://agenda-e5.herokuapp.com"}, allowedHeaders = "*")
public class ReunionesController {
	
	private static final Log LOG = LogFactory.getLog(ReunionesController.class);
	/**
	   * Interfaz CitasService.
	   * 
	   * @author ae5
	   */
	  private final ReunionServiceInt reunionService;
	  /**
	   * Interfaz UsuarioService.
	   * 
	   * @author ae5
	   */
	  private final UsuarioServiceInt usuarioService;

	  @Autowired
	  /**
	   * Contructor CitaController.
	   * 
	   * @author ae5
	   */
	  public ReunionesController(final ReunionServiceInt reunionService, final UsuarioServiceInt usuarioService) {
	    this.usuarioService = usuarioService;
	    this.reunionService = reunionService;
	  }

    @GetMapping("/Reuniones")
    public List<Reunion> findAll(){ 	
    	return reunionService.findAll();
    }


    @GetMapping("/Reuniones/{dni}")
    public ResponseEntity<List<Reunion>> find(@PathVariable("dni") String dni) throws Exception{
    	final String dniEncriptado = Encriptacion.encriptar(dni);
    	List<Reunion> listReuniones = new ArrayList<>();
    	List<String> listReunionesID = usuarioService.findReuniones(dniEncriptado); 
    	while(!listReunionesID.isEmpty()) {
    		listReuniones.add(reunionService.findByReunionId(listReunionesID.remove(0)));
    	}
        return ResponseEntity.ok(listReuniones); 
    }
    @PostMapping("/CancelarReuniones/{dni}")

   	public ResponseEntity<Usuario> cancelar(@PathVariable final String dni, @RequestBody final String id) throws Exception {
   		LOG.info("[SERVER] Borrando cita:  " + id);
       	Usuario user = usuarioService.findByUsernusuario(dni);
       	Reunion reunion = reunionService.findByReunionId(id);
       	String admin = Encriptacion.encriptar("admin");
       	
       	if(user.getTipo().equals(admin)|| user.getDni().equals(reunion.getOrganizador())) {
           LOG.info("[SERVER] Borrando cita:  " + id);
           List<String> listaAsistentes = reunion.getListaAsistentes();
           while(!listaAsistentes.isEmpty()){
           	String dniasistente = listaAsistentes.remove(0);
           	Usuario asist = usuarioService.findByUsernusuario(dniasistente);
           	List<String> listaReuniones = asist.getListaReuniones();
           	listaReuniones.remove(listaReuniones.indexOf(id));
           	asist.setListaReuniones(listaReuniones);
           	
           	usuarioService.updateUsuario(Encriptacion.encriptarUsuario(asist));
           }
           LOG.info("Se han eliminado los asistentes de la reunion");
           reunionService.deleteReunion(id);
       	}else {
           	List<String> listaReuniones = user.getListaReuniones();
           	listaReuniones.remove(listaReuniones.indexOf(id));
           	user.setListaReuniones(listaReuniones);
           	usuarioService.updateUsuario(user);
           	LOG.info("Se ha eliminado el asistente de la reunion");
       		
       	}
       
       	
           return ResponseEntity.ok().build();
         }
    @GetMapping("/ModificarReunion/{dni}")
    public ResponseEntity<Reunion> modificarReunion(@PathVariable final String dni, @RequestBody final String reunion) throws JSONException, Exception{
    	LOG.info(reunion);
		
    	final JSONObject jso = new JSONObject(reunion);
	
		final Reunion reu = reunionService.findByReunionId(jso.getString("id"));
		
		reu.setDescripcion(jso.getString(""));

	//	reunionService.updateReunion(reu);
		
    	return ResponseEntity.ok().build();
    }

    

}



