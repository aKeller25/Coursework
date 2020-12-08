
/*Question 3*/
USE om;

CREATE TABLE orders_copy AS
SELECT *
FROM orders;

CREATE TABLE items_copy AS
SELECT *
FROM items;

/*Question 4*/
SET SQL_SAFE_UPDATES=0;

SELECT *
FROM orders_copy
WHERE shipped_date is null;

DELETE FROM orders_copy 
WHERE shipped_date is null;
-- = null doesn't work because null is not a value

/*SELECT shipped_date
FROM orders_copy
WHERE shipped_date is null;*/

/*Question 5*/
SET SQL_SAFE_UPDATES=0;

UPDATE items_copy
SET unit_price = (unit_price * 1.2)
WHERE artist = 'Umami';

/*Question 6*/
INSERT INTO items_copy VALUES
(17, 'title_here', 'artist_here', 73)