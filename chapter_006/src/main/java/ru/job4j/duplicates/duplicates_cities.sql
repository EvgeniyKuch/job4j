-- Удалить дубликаты через SQL

-- Создаём таблицу
create table if not exists cities (
	id serial primary key,
	name varchar(255)
);
delete from cities;

-- Вносим исходные данные
insert into cities(name) values('Москва');
insert into cities(name) values('СПб');
insert into cities(name) values('Казань');
insert into cities(name) values('Москва');
insert into cities(name) values('СПб');
insert into cities(name) values('Казань');
insert into cities(name) values('Москва');
insert into cities(name) values('СПб');
insert into cities(name) values('Казань');
insert into cities(name) values('Москва');
insert into cities(name) values('СПб');
insert into cities(name) values('Казань');

-- Исходное состояние таблицы
select * from cities;
-- id |  name
------+-------
--  1 | Москва
--  2 | СПб
--  3 | Казань
--  4 | Москва
--  5 | СПб
--  6 | Казань
--  7 | Москва
--  8 | СПб
--  9 | Казань
-- 10 | Москва
-- 11 | СПб
-- 12 | Казань

-- Удаляем дубликаты
delete from cities 
	where id not in (
		select min(id) from cities
		group by name
);

-- Состояние таблицы после удаления дубликатов
select * from cities;
-- id |  name
------+--------
--  1 | Москва
--  2 | СПб
--  3 | Казань