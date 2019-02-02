-- запрос, который выводит самый дорогой продукт
SELECT name, price FROM product
 	WHERE price=(SELECT MAX(price) FROM product)
;