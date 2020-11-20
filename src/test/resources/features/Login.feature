Feature: Acceder al sistema

Scenario Outline: Accediendo al sistema
Given abrir la aplicación web en el navegador
When introducir los datos de acceso
    |DNI	|password		|testCase		|
    |<DNI>|<password>	|<testCase>	|
Then pulsamos entrar

Examples:
		|DNI			|password		|testCase	|
		|05722902L|Contraseña1|Case1		|

