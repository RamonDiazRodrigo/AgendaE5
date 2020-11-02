Feature: Acceder al sistema

Scenario Outline: <testCase><resultado>

Given abrir la aplicación web en el navegador
When introducir los datos de acceso
    | DNI | password |
    |<DNI>|<password>|
Then pulsamos entrar

    Examples: 
      | testCase |     resultado             |   DNI    | password |
      | Case 1   | Login Correcto            | 05722902L|   Rcon   |
      | Case 2   | Login Correcto            | 05674364D| 1q2w3e4r |		
      | Case 3   | No usuario Introducido    |		| 1q2w3e4r |
      | Case 4   | No password Introducida   | 05674364D|          |
      | Case 5   | Password Incorrecta       | 05674364D| rte653das|
