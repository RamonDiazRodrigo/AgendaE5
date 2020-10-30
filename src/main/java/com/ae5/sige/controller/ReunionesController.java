package com.ae5.sige.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ae5.sige.model.Reunion;
import com.ae5.sige.service.ReunionServiceInt;
import com.ae5.sige.service.UsuarioServiceInt;

@RestController
@RequestMapping("/AgendaE5")
@CrossOrigin(origins = "http://localhost:4200")
public class ReunionesController {
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
    	List<Reunion> listReuniones = new ArrayList<>();
    	List<String> listReunionesID = usuarioService.findReuniones(dni); 
    	while(!listReunionesID.isEmpty()) {
    		listReuniones.add(reunionService.findByReunionId(listReunionesID.remove(0)));
    	}
        return ResponseEntity.ok(listReuniones); 
    }


}



