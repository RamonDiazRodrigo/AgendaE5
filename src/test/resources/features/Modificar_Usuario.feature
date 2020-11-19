Feature: Modificar Usuario

  Scenario: Modificacion de usuario
  Scenario Outline: <testCase> <expectedResult>
  
     Given el usuario se ha logueado y quiere cambiar los valores de los campos del usuario para actualizarlos
      
        | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | Nº Telefono     |
        | <dni>	  | <contraseña>		| <nombre>		     | <apellidos>       | <e-mail>		 | <n_telefono>	  |

    When el usuario  se modifica los valores 
  
        | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | Nº Telefono     |
        | <dni>	  | <contraseña>		| <nombre>		     | <apellidos>       | <e-mail>		 | <n_telefono>	  |
    		
    Then se modifica los valores del usuario y se sustituye en la base de datos '<expectedResult>'
    

      Examples:
		| testCase                      | expectedResult |  | DNI	   |Contraseña	    | Nombre         | Apellidos        | E-mail  | Nº Telefono     |
    |  dni incorrecto             | FAILS          | 05777666H      | 1234							|    Juan           | Garcia Funez    | J.Garcia@gmail.com    | 678954098  | 1  |
    |  contraseña incorrecto             | FAILS          | 70678543K      | hjytu3            | Javier     | Moraga Martin        | Moraga Martin  | Javier.Mor@gmail.com  | 678453234 |
    |  nombre incorrecto              | FAILS          | 56789034J     | 6578T         | Alejandro      | Fernandez Infante    |  A.fer@gmail.com      |  678904532 | 
    |  apellidos incorrecto          | FAILS          | 70568980N      | 3456           | Jose    | Rubio Menchero   | Joru@gmail.com   |   675431289  |
    |  e-mail incorrecto            | FAILS          | 05789065G     | 123n8          | Adrian      | Gomez Asencio     | adrian.gom@gmail.com   | 67432128 | 
    |  n-telefono incorrecto           | FAILS          | 05689908T      | 7896c            | Laura     | Rodriguez Maestre   | LauraRo@gmail.com    | 621245678 | 
    |
    | En el resto de casos          | IS SUCCESFUL   | 0522773M     | 123567            | Julian   | Ruiz Garcia     | Jurugar@gmail.com  | 654345678 | 
    