INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Pfizer-BioNtech', 2, 18);
INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Pfizer-BioNtech', 2, 18);
INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Pfizer-BioNtech', 2, 18);

INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Moderna', 2, 18);
INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Moderna', 2, 18);
INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Moderna', 2, 18);

INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Szputnyik', 2, 18);
INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Szputnyik', 2, 18);
INSERT INTO vaccine(name, doses_needed, min_age) VALUES ('Szputnyik', 2, 18);

INSERT INTO PATIENT (full_name, age, gender, is_vaccinated) VALUES ('Johan Lassos', 21, 'MALE', false);
INSERT INTO PATIENT (full_name, age, gender, is_vaccinated) VALUES ('Amanda Fleirmann', 32, 'FEMALE', false);
INSERT INTO PATIENT (full_name, age, gender, is_vaccinated, vaccine_doses_received, vaccine_id, vaccination_date) VALUES ('Tamás Déli', 50, 'MALE', true, 1, 1, '2021-02-12');

INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent Borbála Kórház', 'Komárom-Esztergom Megye', '2800 Tatabánya', 'Dózsa Gy. út 77., "H" épület földszint');
INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent György Egyetemi Oktató Kórház', 'Fejér Megye', '8000 Székesfehérvár', 'Seregélyesi út 3. Rendelőintézet II. szint');
INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent Imre Egyetemi Oktatókórház', 'Pest Megye', '1115 Budapest', 'Tétényi út 12-16. „K” épület földszint 24, 25, 26 és 27-es számú ambulancia');

INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (1, 1);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (1, 4);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (1, 7);

INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (2, 2);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (2, 3);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (2, 8);

INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (3, 5);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (3, 6);
INSERT INTO VACCINATION_POINT_VACCINE_STOCK(VACCINATION_POINT_ID, VACCINE_STOCK_ID) VALUES (3, 9);


