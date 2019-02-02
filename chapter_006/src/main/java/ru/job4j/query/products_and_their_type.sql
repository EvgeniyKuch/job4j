-- Вывести все продукты и их тип
SELECT p.name AS product, t.name AS type FROM product AS p
	INNER JOIN type AS t
		ON t.id=p.type_id
;