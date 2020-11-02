import static org.junit.Assert.assertEquals;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class PasosLogin{
	
	WebDriver driver;
	List<Map<String, String>> a;

	
	@Given("abrir la aplicación web en el navegador")
	public void abrir_la_aplicacion_web_en_el_navegador() {
		Path path = FileSystems.getDefault().getPath("src/test/resources/geckodriver.exe");
	    System.setProperty("webdriver.gecko.driver", path.toString());
	    FirefoxOptions fo = new FirefoxOptions();
	    fo.addArguments("--headless");
	    WebDriver driver = new FirefoxDriver(fo);
	    
	    //AÑADIR DIRECCION
	    driver.get("http://localhost:8080/");
	}
	
	@When("introducir los datos de acceso")
	public void introducir_los_datos_de_acceso(io.cucumber.datatable. DataTabledataTable) {
		a = dataTable.asMaps(String.class, String.class);
		
		driver.findElement(By.xpath("//input[@placeholder='DNI']")).sendKeys(a.get(0).get("DNI"));
		driver.findElement(By.xpath("//input[@placeholder='Contraseña']")).sendKeys(a.get(0).get("password"));

	}
	
	@Then("pulsamos entrar")
	public void pulsamos_entrar() {
		driver.findElement(By.xpath("//input[@value='Acceder']")).click();
		
		String expectedUrl = driver.getCurrentUrl();
		 
		
		//AÑADIR DIRECCIONES
		if(a.get(0).get("testCase")=="Case 1") {
			assertEquals("http://localhost:8080/", expectedUrl);
		}else if(a.get(0).get("testCase")=="Case 2") {
			assertEquals("http://localhost:8080/", expectedUrl);
		}else {
			assertEquals("http://localhost:8080/", expectedUrl);

		}
		
		Thread.sleep(7000);
		driver.close();
	}
	
	
	
	
}