USE my_guitar_shop;

/*Question 1*/
DROP TABLE IF EXISTS orders_items_copy;
DROP TABLE IF EXISTS products_copy;

CREATE TABLE order_items_copy AS
SELECT *
FROM order_items;

CREATE TABLE products_copy AS
SELECT *
FROM products;

/*Question 2*/
INSERT INTO products VALUES
(DEFAULT, 4, 'P45B', 'Yamaha P45b', 'The P45 is in an 88-note weighted-keyboard digital piano',
519.99, 10.00, '2019-06-26');

/*Question 3*/
INSERT INTO order_items
(order_id, product_id, item_price, discount_amount, quantity)
VALUES
(9, 11, 519.99, (519.99 * 0.1), 2);

/*Question 4*/
UPDATE products
SET list_price = 598, discount_percent = 15.00
WHERE product_id = 11;

/*Question 5 */
UPDATE order_items
SET item_price = 349.50
WHERE product_id = 5;

/*Question 6*/
SET SQL_SAFE_UPDATES=0;

DELETE FROM order_items
WHERE product_id = 11;

DELETE FROM products
WHERE product_id = 11;

SET SQL_SAFE_UPDATES=1;





