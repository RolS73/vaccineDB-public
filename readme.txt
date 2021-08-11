VaccineDB is a Spring application to help management and documentation of the available vaccines in registered vaccination points, and also to help track of patients that are to be or already were administered one of the accessible vaccines.


The application has 4 main model classes, these are:
-Patient
-VaccineData
-Vaccine
-Vaccination Point


The "Patient" class is responsible for storing the basic data of persons applied for vaccination, and also the type of vaccine they have been administered with.

The "VaccineData" class stores the name and basic parameters of usage for a registered vaccine. (Like how many doses are needed, and what is the minimum age for safe administeration).

The "Vaccine" class simply stores an ID and a reference to the previously discussed VaccineData, it serves as a simple way to keep track of inventory.

The "Vaccination Point" class is to register the locations where vaccines are stocked.


The endpoints for the application and their usage are the following:


​GET 			/vaccine			-Lists every available vaccine
PUT			​/vaccine			-Updates an entry or saves a new one with input data.
POST ​			/vaccine			-Registers a new vaccine
GET			​/vaccine​/{id}			-Returns the vaccine of the input id.
DELETE			​/vaccines/{id}			-Deletes the vaccine of the input id.

GET 			​/vaccine_data			-Lists every available Vaccine type.
PUT			​/vaccine_data			-Updates an entry or saves a new one with input data.
POST 			/vaccine_data			-Inserts a new vaccine type into the database.
GET 			/vaccine_data​/{id}		-Returns the vaccine type with the selected id.
DELETE 			/vaccine_data​/{id}		-Deletes the vaccine type with the selected id, and removes all instances/references to it in the database.

GET			/vaccinationpoint		-Lists every registered vaccination point.
PUT			​/vaccinationpoint		-Updates an entry or saves a new one with input data.	
POST			​/vaccinationpoint		-Registers a new vaccination point.
GET			​/vaccinationpoint​/{id}		-Returns the vaccination point of the selected id.	
DELETE			/vaccinationpoints/{id}		-Deletes the vaccination point of the selected id, and removes its stored vaccines from the database.

GET			/patient			-Returns all patients registered in the database.
PUT			​/patient			-Updates an entry or saves a new one with input data.
POST			/patient			-Registers a new patient in the database.
GET			/patient​/{id}			-Returns a patient with the selected id.
DELETE			/patient/{id}			-Deletes a patient with the selected id.

