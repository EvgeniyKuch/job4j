-- запрос получение всех продуктов с типом "СЫР"
select p.name from product as p
	inner join type as t
		on p.type_id=t.id
	where t.name='СЫР'
;