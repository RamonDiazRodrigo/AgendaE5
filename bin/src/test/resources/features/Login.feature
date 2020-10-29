Feature: Acceder al sistema


Scenario Outline: <testCase><resultado>

Given abrir la aplicación web en el navegador
When introducir los datos de acceso
    | usuario | password |
    |<usuario>|<password>|
Then pulsamos entrar

    Examples: 
      | testCase |     resultado             |   usuario   |   password |
      | Case 1   | Login Correcto            | EduMG       | 1q2w3e4r   |
      | Case 2   | Login Correcto            | CarlosAdmin | 12qwerty34 |		
      | Case 3   | No usuario Introducido    |		   | 1q2w3e4r   |
      | Case 4   | No password Introducida   | EduMG       |            |
      | Case 5   | Password Incorrecta       | EduMG       | rte653das  |
      | Case 6   | Usuario Incorrecto	     | EduAnt      | 12345ffsa  |
