Feature: Eliminar usuario

	Scenario: Eliminacion de usuario
		Scenario Outline: <testCase> <expectedResult>
  
     Given el administrador quiere eliminar a un usuario registrado
      
        | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | Nº Telefono     |


    When el administrador  eliminará el usuario seleccionado
    
    	     | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | Nº Telefono     |
    	      | <dni>  |<contraseña>    | <nombre>        | <apellidos>       | <e-mail>  | <n_telefono>    |
    		
    Then se elimina el usuario  de la base de datos. '<expectedResult>'
    

      Examples:
		| testCase                      | expectedResult | | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | Nº Telefono     |
    | No tipo presente              | FAILS          | 05766445K    |     1234          | Móises   | Crespo Palomares     | Moisescrespo24@gmail.com  | 667890763        |
    