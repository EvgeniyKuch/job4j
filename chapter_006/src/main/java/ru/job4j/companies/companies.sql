-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
select p.name as name, c.name as company from
	person as p inner join company as c 
		on p.company_id=c.id
	where p.company_id != 5;


-- 2) Select the name of the company with the maximum number 
--    of persons + number of persons in this company	
select c.name as company, count(*) as quantity from
	company as c inner join person as p
		on p.company_id = c.id
	group by company
	order by quantity desc
	limit 1;
	