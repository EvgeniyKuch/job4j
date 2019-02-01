--CREATE DATABASE tracker;
CREATE TABLE roles (
 	id SERIAL 	PRIMARY KEY,
 	name 		VARCHAR(2000)
);
CREATE TABLE rules (
 	id SERIAL 	PRIMARY KEY,
 	rule 		VARCHAR(2000)
);
CREATE TABLE roles_rules_compose (
 	id SERIAL 	PRIMARY KEY,
 	role_id 	INTEGER REFERENCES roles(id),
	rule_id 	INTEGER REFERENCES rules(id)
);
CREATE TABLE users (
 	id SERIAL 	PRIMARY KEY,
 	name 		VARCHAR(2000),
	role_id 	INTEGER REFERENCES roles(id)
);
CREATE TABLE categories (
 	id SERIAL 	PRIMARY KEY,
 	name 		VARCHAR(2000)
);
CREATE TABLE states (
 	id SERIAL 	PRIMARY KEY,
 	name 		VARCHAR(2000)
);
CREATE TABLE items (
 	id SERIAL 	PRIMARY KEY,
	user_id 	INTEGER REFERENCES users(id),
	category_id INTEGER REFERENCES categories(id),
	state_id 	INTEGER REFERENCES states(id),
	item 		VARCHAR(2000)
);
CREATE TABLE comments (
 	id SERIAL 	PRIMARY KEY,
 	comment 	VARCHAR(2000),
	item_id 	INTEGER REFERENCES items(id)
);
CREATE TABLE attachs (
 	id SERIAL 	PRIMARY KEY,
 	path_to_file VARCHAR(2000),
	item_id 	INTEGER REFERENCES items(id)
);
-- Init
INSERT INTO rules(rule) VALUES ('Admin');
INSERT INTO roles(name) VALUES ('Admin');
INSERT INTO roles_rules_compose (rule_id, role_id) VALUES (1, 1);
INSERT INTO users(name, role_id) VALUES ('Eugene', 1);
INSERT INTO categories(name) VALUES ('Category1');
INSERT INTO states(name) VALUES ('State1');
INSERT INTO items(user_id, category_id, state_id, item) VALUES (1, 1, 1, 'Item1');
INSERT INTO comments (item_id, comment) VALUES (1, 'Comment1');
INSERT INTO attachs (item_id, path_to_file) VALUES (1, 'Path1');
