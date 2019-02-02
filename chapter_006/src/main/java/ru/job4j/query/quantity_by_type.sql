-- запрос, который выводит количество всех продуктов определенного типа
SELECT t.name, SUM(p.quantity) FROM type AS t
	INNER JOIN product AS p
		ON t.id=p.type_id
	GROUP BY t.name
;