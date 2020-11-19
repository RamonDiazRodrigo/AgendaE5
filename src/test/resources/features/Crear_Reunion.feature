Feature: Crear Reunion
Se podrán guardar reuniones en la base de datos

	Scenario: Se podrá crear una reunion  e introducirla al sistema
	
		Given se registra la reunion con los siguientes campos
		|	Titulo  | Descripcion	  	| Organizador     | Fecha | Hora inicio       | Hora fin   | Lista asistentes                |   		
    |Control proyecto| Reunion donde veremos como abordar el proyecto      | Daniel Gonzalez    | 24/11/2020   | 12:00     | 13:50    | Manuel Morales, Iván Ruiz|	
			
		
		When la reunion guarda sus datos con los campos requeridos
		Then el resultado de guardar la reunion es correcto