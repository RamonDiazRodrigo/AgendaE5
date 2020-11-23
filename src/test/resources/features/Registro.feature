Feature: Registrarse en la aplicación como asistente

Scenario Outline: <testCase><resultado>


Given abrir la aplicación web en el navegador e iniciar el formulario de registro
When introducir los datos de registro
    |DNI|password|nombre|apellidos|correo|telefono|
    |<DNI>|<password>|<nombre>|<apellidos>|<correo>|<telefono>|
Then pulsamos REGISTRARSE


    Examples: 
      | testCase |     resultado             |DNI|password|nombre|apellidos|correo|telefono|
      | Case 1   | Registro Correcto         |09887534S|vdsafdas23|Jorge|Gomez Sanchez|jorge2@gmail.com|676543212|
      
