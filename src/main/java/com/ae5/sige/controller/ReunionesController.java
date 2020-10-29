package com.ae5.sige.controller;


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
	   * @author e3corp
	   */
	  private final ReunionServiceInt reunionService;
	  /**
	   * Interfaz UsuarioService.
	   * 
	   * @author e3corp
	   */
	  private final UsuarioServiceInt usuarioService;

	  @Autowired
	  /**
	   * Contructor CitaController.
	   * 
	   * @author e3corp
	   */
	  public ReunionesController(final ReunionServiceInt reunionService, final UsuarioServiceInt usuarioService) {
	    this.usuarioService = usuarioService;
	    this.reunionService = reunionService;
	  }

    @GetMapping("/Reuniones")
    public List<Reunion> findAll(){
        List<Reunion> aux = reunionService.findAll();
        System.out.println(aux.get(0));
    	return aux;
    }


    @GetMapping("/Reuniones/{id}")
    public Reunion find(@PathVariable("id") String id) throws Exception{
        return reunionService.findByReunionId(id); 
    }
    
    @RequestMapping(value = "/Reuniones/{dni}", method = RequestMethod.GET)
    /**
     * @author e3corp
     */
    public ResponseEntity<List<Reunion>> getListadoCitasByPaciente(@PathVariable(required = true) final String dni) {
      final List<Reunion> citas = reunionService.getReunionesByUsuario(dni);
      return ResponseEntity.ok(citas);
    }

}



