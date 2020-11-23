package com.ae5.bbdd.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.ae5.sige.model.Usuario;
import com.ae5.sige.repository.UsuarioRepository;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class Modificar_UsuarioSteps {

  WebDriver driver;
  DataTable dataTable;
  String testCase="";
  UsuarioRepository usuarioRepository;
  Usuario usuario = new Usuario();
  
  List<Map<String, String>> a;

  @Given("el usuario se ha logueado y quiere cambiar los valores de los campos del usuario para actualizarlos")
  public void el_usuario_se_ha_logueado_y_quiere_cambiar_los_valores_de_los_campos_del_usuario_para_actualizarlos() {

    Path path = FileSystems.getDefault().getPath("src/test/resources/geckodriver.exe");
    System.setProperty("webdriver.gecko.driver", path.toString());
    FirefoxOptions fo = new FirefoxOptions();
    fo.addArguments("--headless");
    WebDriver driver = new FirefoxDriver(fo);
    driver.get("http://localhost:4200/login");

    a = dataTable.asMaps(String.class, String.class);

    driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("Usuario"));
    driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("contraseña"));
    driver.findElement(By.xpath("//input[@value='Acceder']")).click();
    driver.findElement(By.linkText("Ramon Diaz Rodrigo")).click();
    driver.findElement(By.xpath("//input[@id='modificar']")).click();
   
    
    
   
  }

  @When("el usuario  se modifica los valores")
  public void el_usuario_se_modifica_los_valores(DataTable dataTable) {
	  a = dataTable.asMaps(String.class, String.class);
	  	driver.findElement(By.xpath("//input[@id='nombre']")).sendKeys(a.get(0).get("nombre"));
	    driver.findElement(By.xpath("//input[@id='apellidos']")).sendKeys(a.get(0).get("apellidos"));
	    driver.findElement(By.xpath("//input[@name='dni']")).sendKeys(a.get(0).get("DNI"));
	    driver.findElement(By.xpath("//input[@id='contrasena']")).sendKeys(a.get(0).get("password"));
	    driver.findElement(By.xpath("//input[@id='telefono']")).sendKeys(a.get(0).get("telefono"));
	    driver.findElement(By.xpath("//input[@id='correo']")).sendKeys(a.get(0).get("correo"));
	    testCase = a.get(0).get("testCase");
	    usuario.setNombre(a.get(0).get("nombre"));
	    usuario.setApellidos(a.get(0).get("apellidos"));
	    usuario.setDni(a.get(0).get("DNI"));
	    usuario.setContrasena(a.get(0).get("password"));
	    usuario.setTelefono(a.get(0).get("telefono"));
	    usuario.setCorreo(a.get(0).get("correo"));
    

  }

  @Then("se modifica los valores del usuario y se sustituye en la base de datos")
  public void se_modifica_los_valores_del_usuario_y_se_sustituye_en_la_base_de_datos() {
    driver.findElement(By.xpath("//input[@id='modificar']")).click();
    Usuario usuario2 = usuarioRepository.findOne(usuario.getDni()).get();
    usuario.setListaReuniones(usuario2.getListaReuniones());
    if (testCase.equals("En el reso de casos")) {
        assertEquals(usuario, usuario2);
      }

    driver.close();
  }
}