INSERT INTO Vaccine_Data(name, doses_needed, min_age) VALUES ('Pfizer-BioNtech', 2, 18);
INSERT INTO Vaccine_Data(name, doses_needed, min_age) VALUES ('Moderna', 2, 18);
INSERT INTO Vaccine_Data(name, doses_needed, min_age) VALUES ('Szputnyik', 2, 18);

INSERT INTO PATIENT (full_name, age, gender, is_vaccinated, vaccine_name, vaccination_date) VALUES ('Johan Lassos', 21, 'MALE', false, null, null);
INSERT INTO PATIENT (full_name, age, gender, is_vaccinated, vaccine_name, vaccination_date) VALUES ('Amanda Fleirmann', 32, 'FEMALE', false, null, null);
INSERT INTO PATIENT (full_name, age, gender, is_vaccinated, vaccine_name, vaccination_date) VALUES ('Tamás Déli', 50, 'MALE', true, 'Pfizer-BioNtech', '2021-02-12');

INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent Borbála Kórház', 'Komárom-Esztergom Megye', '2800 Tatabánya', 'Dózsa Gy. út 77., "H" épület földszint');
INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent György Egyetemi Oktató Kórház', 'Fejér Megye', '8000 Székesfehérvár', 'Seregélyesi út 3. Rendelőintézet II. szint');
INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent Imre Egyetemi Oktatókórház', 'Pest Megye', '1115 Budapest', 'Tétényi út 12-16. „K” épület földszint 24, 25, 26 és 27-es számú ambulancia');

INSERT INTO vaccine(vaccine_data_name) VALUES ('Pfizer-BioNtech');
INSERT INTO vaccine(vaccine_data_name) VALUES ('Pfizer-BioNtech');
INSERT INTO vaccine(vaccine_data_name) VALUES ('Pfizer-BioNtech');

INSERT INTO vaccine(vaccine_data_name) VALUES ('Moderna');
INSERT INTO vaccine(vaccine_data_name) VALUES ('Moderna');
INSERT INTO vaccine(vaccine_data_name) VALUES ('Moderna');

INSERT INTO vaccine(vaccine_data_name) VALUES ('Szputnyik');
INSERT INTO vaccine(vaccine_data_name) VALUES ('Szputnyik');
INSERT INTO vaccine(vaccine_data_name) VALUES ('Szputnyik');

INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (1, 1);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (1, 4);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (1, 7);

INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (2, 2);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (2, 3);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (2, 8);

INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (3, 5);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (3, 6);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (3, 9);


