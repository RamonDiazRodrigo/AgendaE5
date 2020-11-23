Feature: Modificar Usuario

  Scenario: Modificacion de usuario
  Scenario Outline: <testCase> 
  
     Given el usuario se ha logueado y quiere cambiar los valores de los campos del usuario para actualizarlos
      
        | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | N Telefono     |
        | <dni>	  | <contraseña>		| <nombre>		     | <apellidos>       | <e-mail>		 | <n_telefono>	  |

    When el usuario  se modifica los valores 
  
        | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | N Telefono     |
        | <dni>	  | <contrasena>		| <nombre>		     | <apellidos>       | <e-mail>		 | <n_telefono>	  |
    		
    Then se modifica los valores del usuario y se sustituye en la base de datos 
    

      Examples:
		| testCase       | expectedResult           |   DNI	        |Contrasena	    | Nombre      | Apellidos            | E-mail                   | N Telefono |
		
   