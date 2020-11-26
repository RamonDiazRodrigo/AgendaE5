Feature: Crear reunion
Se podrán guardar reuniones en la base de datos

	Scenario: Se podrá crear una reunion e introducirla al sistema
	
		Given se registra un usuario con los siguientes campos
		|	Titulo   | Descripcion	  	| Organizador   | Fecha | Hora inicio        | Hora fin    | Lista asistentes   | 
    |Reunion de personal | Miguel      | Aitor Ocio    | 28/12/2020  | 12:00   | 14:30    | Jose Manuel Bastante, Manuel Morales, Ivan Ruiz, Alvaro Sanchez, Daniel Gonzalez, Ramon Diaz |	
			
		
		When la reunion guarda sus datos con los campos requeridos
		Then el resultado de guardar la reunion es correcto