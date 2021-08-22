VaccineDB is a Spring application to help management and documentation of the available vaccines in registered vaccination points, and also to help track of patients that are to be or already were administered one of the accessible vaccines.
The application also has Docker support, to build and run an image from it simply run the "vaccineDB_build" and "vaccineDB_run" files respectively.

The application has 3 main model classes, these are:
-Patient
-Vaccine
-Vaccination Point


The "Patient" class is responsible for storing the basic data of persons applied for vaccination, and also the type of vaccine they have been administered with.

The "Vaccine" class stores the data of all the available vaccines and their data (the name, dosage and minimum age for safe injection).

The "Vaccination Point" class is to register the locations where vaccines are stocked.


The endpoints for the application and their usage are the following:


​GET 			/vaccine			-Lists every available vaccine
PUT			​/vaccine			-Updates an entry or saves a new one with input data.
POST ​			/vaccine			-Registers a new vaccine
GET			​/vaccine​/{id}			-Returns the vaccine of the input id.
DELETE			​/vaccines/{id}			-Deletes the vaccine of the input id.

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

