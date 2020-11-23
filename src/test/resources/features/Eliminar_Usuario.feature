Feature: Eliminar usuario

	Scenario: Eliminacion de usuario
		Scenario Outline: <testCase> <expectedResult>
  
     Given el administrador quiere eliminar a un usuario registrado
      
        | DNI	   |Contrasena	    | Nombre         | Apellidos        | E-mail  | N Telefono     |


    When el administrador  eliminara el usuario seleccionado
    
    	     | DNI	   |Contrasena	    | Nombre         | Apellidos        | E-mail  | N Telefono     |
    	      | <dni>  |<contrasena>    | <nombre>        | <apellidos>       | <e-mail>  | <n_telefono>    |
    		
    Then se elimina el usuario  de la base de datos
    
    Examples: 

      	| testCase                      | expectedResult | | DNI	   |Contrasena	    | Nombre         | Apellidos        | E-mail  | N Telefono     |
	

    