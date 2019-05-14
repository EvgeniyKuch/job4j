-- Удалить дубликаты через SQL
--create table cities (
--	id serial primary key,
--	name varchar(255)
--);

delete from cities 
	where id not in (
		select min(id) from cities
		group by name
);