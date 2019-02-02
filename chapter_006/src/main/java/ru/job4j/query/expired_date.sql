-- запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце
SELECT name, expired_date FROM product
 	WHERE EXTRACT(MONTH FROM expired_date) - EXTRACT(MONTH FROM CURRENT_DATE) = 1
;