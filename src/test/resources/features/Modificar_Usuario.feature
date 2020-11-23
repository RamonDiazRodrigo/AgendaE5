Feature: Modificar Usuario

  Scenario: Modificacion de usuario

  Scenario Outline: <testCase> <expectedResult>
    Given el usuario se ha logueado y quiere cambiar los valores de los campos del usuario para actualizarlos   
    When el usuario  se modifica los valores
      | DNI   | Contraseña   | Nombre   | Apellidos   | E-mail   | Nº Telefono  |
      | <dni> | <contraseña> | <nombre> | <apellidos> | <e-mail> | <n_telefono> |
    Then se modifica los valores del usuario y se sustituye en la base de datos '<expectedResult>'

    Examples: 
      | testCase              | expectedResult | DNI       | Contraseña | Nombre    | Apellidos         | E-mail               | Nº Telefono          |         
      | dni incorrecto        | FAILS          | 05777666  |     123456 | Juan      | Garcia Funez      | J.Garcia@gmail.com   |            678954098 |        
      | contraseña incorrecto | FAILS          | 70678543K | hjy        | Javier    | Moraga Martin     | Javier.Mor@gmail.com |            678453234 |              
      | e-mail incorrecto     | FAILS          | 05789065G | 123n8hj    | Adrian    | Gomez Asencio     | adriangomez          |            674321287 |           
      | n-telefono incorrecto | FAILS          | 05689908T | 7896cghjj  | Laura     | Rodriguez Maestre | LauraRo@gmail.com    |            62124567  |           
      | En el resto de casos  | IS SUCCESFUL   | 05227738M |   1q2w3e4r | Julian    | Ruiz Garcia       | Jurugar@gmail.com    |            654345678 |           
