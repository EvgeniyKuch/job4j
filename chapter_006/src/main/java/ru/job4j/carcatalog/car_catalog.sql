CREATE TABLE car_body (
	id SERIAL 		PRIMARY KEY,
	name 			VARCHAR(255)
);

CREATE TABLE engine(
	id SERIAL 		PRIMARY KEY,
	name 			VARCHAR(255)
);

CREATE TABLE transmission(
	id SERIAL 		PRIMARY KEY,
	name 			VARCHAR(255)
);

CREATE TABLE car(
	id SERIAL 		PRIMARY KEY,
	name 			VARCHAR(255),
	id_body 		INTEGER REFERENCES car_body(id) NOT NULL,
	id_engine 		INTEGER REFERENCES engine(id) NOT NULL,
	id_transmission INTEGER REFERENCES transmission(id) NOT NULL
);

INSERT INTO car_body(name) VALUES ('sedan');
INSERT INTO car_body(name) VALUES ('hatchback');
INSERT INTO car_body(name) VALUES ('station wagon');
INSERT INTO car_body(name) VALUES ('liftback');
INSERT INTO car_body(name) VALUES ('coupe');
INSERT INTO car_body(name) VALUES ('convertible');
INSERT INTO car_body(name) VALUES ('limousine');

INSERT INTO transmission(name) VALUES ('manual');
INSERT INTO transmission(name) VALUES ('robotic');
INSERT INTO transmission(name) VALUES ('automatic');
INSERT INTO transmission(name) VALUES ('CVT');

INSERT INTO engine(name) VALUES ('internal combustion engines');
INSERT INTO engine(name) VALUES ('steam engines');
INSERT INTO engine(name) VALUES ('electric motors');

INSERT INTO car(name, id_body, id_engine, id_transmission)
	VALUES ('accent', '1', '1', '3');
	
INSERT INTO car(name, id_body, id_engine, id_transmission)
	VALUES ('mazda 3', '1', '1', '1');
	
INSERT INTO car(name, id_body, id_engine, id_transmission)
	VALUES ('Tesla Model S', '4', '3', '3');
	
-- список всех машин и все привязанные к ним детали.
SELECT 
	c.name AS name,
	b.name AS body,
	e.name AS engine,
	t.name AS transmission
	FROM car AS c 
		INNER JOIN car_body AS b
			ON c.id_body = b.id
		INNER JOIN engine AS e
			ON c.id_engine = e.id
		INNER JOIN transmission AS t
			ON c.id_transmission = t.id
;

-- неиспользыемые кузова
SELECT b.name AS body
	FROM car_body AS b
		LEFT OUTER JOIN car AS c
		ON c.id_body = b.id
	WHERE c.id_body IS NULL;
;

-- неиспользуемые двигатели
SELECT e.name AS engine
	FROM engine AS e
		LEFT OUTER JOIN car AS c
		ON c.id_engine = e.id
	WHERE c.id_engine IS NULL;
;

-- неиспользуемые коробки
SELECT t.name AS transmission
	FROM transmission AS t
		LEFT OUTER JOIN car AS c
		ON c.id_transmission = t.id
	WHERE c.id_transmission IS NULL;
;