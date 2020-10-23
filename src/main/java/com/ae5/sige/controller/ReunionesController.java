package com.ae5.sige.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae5.sige.model.Reunion;
import com.ae5.sige.service.ReunionServiceInt;

@RestController
@RequestMapping("/AgendaE5")
@CrossOrigin(origins = "http://localhost:4200")
public class ReunionesController {


    @Autowired
    private ReunionServiceInt reunionRep;

    @GetMapping("/Reuniones")
    public List<Reunion> findAll(){
    	System.out.println("controller find all");
    	Reunion reunion = new Reunion("7", "Prueba 1", "prueba guardado", "nadie","19:49", "22/10", new ArrayList<String>());
        //reunionRep.saveReunion(reunion);
        List<Reunion> aux = reunionRep.findAll();
        System.out.println(aux.get(0));
    	return aux;
    }


    @GetMapping("/Reuniones/{id}")
    public Reunion find(@PathVariable("id") String id) throws Exception{
        return reunionRep.findByReunionId(id);

    }
}


