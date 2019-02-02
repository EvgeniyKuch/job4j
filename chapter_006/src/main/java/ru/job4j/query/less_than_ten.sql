-- запрос, который выводит тип продуктов, которых осталось меньше 10 штук
SELECT t.name, SUM(p.quantity) AS quantity FROM type AS t
	INNER JOIN product AS p
		ON t.id=p.type_id
	GROUP BY t.name
	HAVING SUM(p.quantity) < 10
;