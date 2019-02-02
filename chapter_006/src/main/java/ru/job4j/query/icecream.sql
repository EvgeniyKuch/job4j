-- запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select name from product
	where name like '%мороженное%'
;