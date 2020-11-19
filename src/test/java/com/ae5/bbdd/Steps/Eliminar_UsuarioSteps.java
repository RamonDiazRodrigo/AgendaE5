package com.ae5.bbdd.Steps;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.ae5.sige.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Eliminar_UsuarioSteps {

  WebDriver driver;
  List<Map<String, String>> a;
  UsuarioRepository usuarioRepository;
  
  @Given("el administrador quiere eliminar a un usuario registrado")
  public void el_administrador_quiere_eliminar_a_un_usuario_registrado(
      io.cucumber.datatable.DataTable dataTable) {

	  //Mirar los drivers necesarios

  }

  @When("el administrador  eliminar� el usuario seleccionado")
  public void el_administrador_eliminara_el_usuario_seleccionado(io.cucumber.datatable.DataTable dataTable) {

    driver.findElement(By.xpath("//input[@value='Usuario']")).click();

    /* SELECCIONAR ELEMENTO DE LA TABLA DE LISTA */

    driver.findElement(By.xpath("//input[@value='Eliminar Usuario']")).click();

  }

  @Then(" se elimina el usuario  de la base de datos. {string}")
  public void se_elimina_el_usuario_de_la_base_de_datos(String string) {

    /* Comprobar que el usuario  que voy a borrar esta en la base de datos */
    driver.findElement(By.xpath("//input[@value='Eliminar']")).click();
    /* Comprobar que se ha eliminado */

    driver.close();
  }

}