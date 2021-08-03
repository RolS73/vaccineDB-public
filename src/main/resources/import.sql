INSERT INTO Vaccine_Data(name, doses_needed, min_age) VALUES ('Pfizer-BioNtech', 2, 18);
INSERT INTO Vaccine_Data(name, doses_needed, min_age) VALUES ('Moderna', 2, 18);
INSERT INTO Vaccine_Data(name, doses_needed, min_age) VALUES ('Szputnyik', 2, 18);

INSERT INTO PATIENT (full_name, age, gender, is_vaccinated, vaccine_id, vaccination_date) VALUES ('Johan Lassos', 21, 'MALE', false, null, null);
INSERT INTO PATIENT (full_name, age, gender, is_vaccinated, vaccine_id, vaccination_date) VALUES ('Amanda Fleirman', 32, 'FEMALE', false, null, null);


INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent Borbála Kórház', 'Komárom-Esztergom Megye', '2800 Tatabánya', 'Dózsa Gy. út 77., "H" épület földszint');
INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent György Egyetemi Oktató Kórház', 'Fejér Megye', '8000 Székesfehérvár', 'Seregélyesi út 3. Rendelőintézet II. szint');
INSERT INTO Vaccination_point (name, region, city, address) VALUES ('Szent Imre Egyetemi Oktatókórház', 'Pest Megye', '1115 Budapest', 'Tétényi út 12-16. „K” épület földszint 24, 25, 26 és 27-es számú ambulancia');

