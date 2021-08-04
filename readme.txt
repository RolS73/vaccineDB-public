VaccineDB is a Spring application to help management and documentation of the available vaccines in registered vaccination points, and
also to help track of patients that are to be or already were administered one of the accessible vaccines.

The application has 3 main model classes (+1 for inheritance), these are:
-Patient
-Vaccine
-Vaccination Point

The "Patient" class is responsible for storing the basic data of persons applied for vaccination,
and also the type of vaccine they have been administered with.

The "Vaccine" class helps keep track of the available Vaccines in Hungary, and their basic parameters of usage. (Like how many doses are needed, and
what is the minimum age for safe administeration).
This class is set up to inherit the basic parameters of super classes, while the child class is only used to keep track of stock.

The "Vaccination Point" class is to register the locations where vaccines are stocked.


The endpoints for the application and their usage are the following:


​GET 			/vaccines			-Lists every available TYPE of vaccine
POST ​			/vaccines			-Registers a new TYPE of vaccine
GET			​/vaccines​/{id}			-Returns the vaccine TYPE of the input id.
DELETE			​/vaccines​/{id}			-Deletes the vaccine TYPE of the input id.

GET			/vaccinationpoints		-Lists every registered vaccination point.	
POST			​/vaccinationpoints		-Registers a new vaccination point.
GET			​/vaccinationpoints​/{id}		-Returns the vaccination point of the selected id.	
DELETE			/vaccinationpoints​/{id}		-Deletes the vaccination point of the selected id.

GET			/patients			-Returns all patients registered in the database.
POST			/patients			-Registers a new patient in the database.
GET			/patients​/{id}			-Returns a patient with the selected id.
DELETE			/patients​/{id}			-Deletes a patient with the selected id.

