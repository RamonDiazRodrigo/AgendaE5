package com.ae5.bbdd.Steps;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ae5.sige.model.Reunion;
import com.ae5.sige.repository.ReunionRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CrearReunionSteps {

  @Autowired
  private ReunionRepository reunionRepository; 
  private Reunion r = new Reunion(); 

  @Given("se registra una reunion con los siguientes campos")
  public void se_registra_una_reunion_con_los_siguientes_campos(DataTable dataTable) {
    /*
     Titulo   | Descripcion	  	| Organizador   | Fecha | Hora inicio        | Hora fin    | Lista asistentes   | 
     */

    List<Map<String, String>> a = dataTable.asMaps(String.class, String.class);

    r.setTitulo(a.get(0).get("Titulo"));
    r.setDescripcion(a.get(0).get("Descripcion"));
    r.setOrganizador(a.get(0).get("Organizador"));
    r.setFecha(a.get(0).get("Fecha"));
    r.setHoraIni(a.get(0).get("Hora inicio"));
    r.setHoraFin(a.get(0).get("Hora fin"));
    List<String> aux = new ArrayList<>();
    aux.add(a.get(0).get("Lista asistentes"));
    r.setListaAsistentes(aux);
    

  }

  @When("la reunion guarda sus datos con los campos requeridos")
  public void el_usuario_guarda_la_reunion_con_los_campos_requeridos() {
    this.reunionRepository.saveReunion(r);

  }

  @Then("el resultado de guardar la reunion es correcto")
  public void el_resultado_de_guardar_la_reunion_es_correcto() {
   
  }

}
