/* Question 1 */
USE my_guitar_shop;

SELECT product_name, COUNT(*) AS num_orders, 
SUM(quantity) AS num_products, 
CONCAT('$', FORMAT(AVG(discount_amount), 2)) AS avg_discount
FROM order_items JOIN products USING (product_id)
GROUP BY product_name WITH ROLLUP;

/* Question 2 */
USE my_guitar_shop;

SELECT FORMAT(CONCAT('$', list_price), 2) AS 'Product List Price', product_name AS 'Product', description AS 'Description'
FROM products
GROUP BY product_name
HAVING list_price <= AVG(list_price)
ORDER BY list_price DESC, product_name DESC;

/* Question 3 */
-- Create agregate of the total price of each order
SELECT CONCAT(last_name, ', ', first_name) AS 'Customer Name',
COUNT(*) AS num_orders, SUM(num_items) AS num_items , CONCAT('$',FORMAT(SUM(per_order_total), 2)) AS order_total

FROM customers JOIN orders USING (customer_id) JOIN -- Join on subquery

(SELECT order_id, COUNT(item_id) AS num_items, 
SUM((item_price - discount_amount) * quantity) AS per_order_total
FROM order_items
GROUP BY order_id) order_aggregate USING (order_id)

WHERE ship_date IS NOT NULL
GROUP BY CONCAT(last_name, ', ', first_name)
ORDER BY num_items DESC, SUM(per_order_total) DESC