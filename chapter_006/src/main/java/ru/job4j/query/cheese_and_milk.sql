-- запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.name, t.name FROM product AS p
	INNER JOIN type AS t
		ON p.type_id=t.id
	WHERE t.name='СЫР' OR t.name='МОЛОКО'
;