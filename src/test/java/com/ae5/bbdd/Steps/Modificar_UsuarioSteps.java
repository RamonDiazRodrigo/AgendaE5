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
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class Modificar_UsuarioSteps {

  WebDriver driver;
  DataTable dataTable;

  UsuarioRepository usuarioRepository;
  
  List<Map<String, String>> a;

  @Given("el usuario se ha logueado y quiere cambiar los valores de los campos del usuario para actualizarlos")
  public void el_usuario_se_ha_logueado_y_quiere_cambiar_los_valores_de_los_campos_del_usuario_para_actualizarlos() {

    Path path = FileSystems.getDefault().getPath("src/test/resources/features/drivers/geckodriver.exe");
    System.setProperty("webdriver.gecko.driver", path.toString());
    FirefoxOptions fo = new FirefoxOptions();
    fo.addArguments("--headless");
    WebDriver driver = new FirefoxDriver(fo);
    driver.get("http://localhost:4200/auth/login");

    a = dataTable.asMaps(String.class, String.class);

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("Usuario"));
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
    driver.findElement(By.xpath("//input[@value='Acceder']")).click();
  }

  @When("el usuario  se modifica los valores")
  public void el_usuario_se_modifica_los_valores() {
	  a = dataTable.asMaps(String.class, String.class);
   
    driver.findElement(By.xpath("//input[@value='Lista de usuarios']")).click();
    driver.findElement(By.xpath("//input[@value='Modificar usuario']")).click();


    

  }

  @Then("se modifica los valores del usuario y se sustituye en la base de datos")
  public void se_modifica_los_valores_del_usuario_y_se_sustituye_en_la_base_de_datos() {
    driver.findElement(By.xpath("//input[@value='Modificar']")).click();

    driver.close();
  }
}