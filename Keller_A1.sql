/*Asnwer 1*/
USE om;

SELECT CONCAT(customer_last_name, ', ' , customer_first_name) AS 'Customer Name', 
CONCAT('Contact #: ', LEFT(customer_phone, 3), '-', RIGHT(LEFT(customer_phone, 6), 3), '-',RIGHT(customer_phone, 4)) AS 'Customer Contact Number', 
CONCAT(customer_address,', ' , customer_city,', ', customer_state,' ', customer_zip) AS 'Customer Address'
FROM customers
WHERE customer_last_name > 'B' AND customer_last_name < 'F'
ORDER BY customer_last_name;

/*Answer 2*/
USE om;
SELECT order_id AS 'Order ID', 
CONCAT(customer_first_name, ' ', customer_last_name) AS 'Customer Name',
order_date, shipped_date
FROM orders JOIN customers USING(customer_id) 
-- Join is used to combine orders and customers, where the customer id's match
-- An example of this customers who we have on record, but haven't ordered anything yet
-- (From a customer list inhereted from another company)
-- USING is used because the table columns are the same
WHERE shipped_date IS NOT NULL AND (order_date > '2014-07-10' AND order_date < '2014-08-03')

UNION

SELECT order_id, 
CONCAT(customer_first_name, ' ', customer_last_name) AS 'Customer Name',
order_date, 'Not Yet Shipped' AS shipped_date
FROM orders JOIN customers USING(customer_id)
WHERE shipped_date IS NULL AND (order_date > '2014-07-10' AND order_date < '2014-08-03')
ORDER BY order_date;


/*Answer 3*/
USE ap;

SELECT vendor_name, CONCAT(vendor_contact_last_name, ' ' , vendor_contact_first_name) AS 'Contact Full Name', 
invoice_id AS 'Invoice ID',
(invoice_total - payment_total - credit_total) AS 'Balance Due'
FROM vendors LEFT JOIN invoices USING(vendor_id)
WHERE (invoice_total - payment_total - credit_total) = 0
ORDER BY vendor_name;

/*Answer 4*/
USE ap;

SELECT terms_description, vendor_state, vendor_name, vendor_id
FROM vendors JOIN terms ON default_terms_id = terms_id
-- INNER JOIN here because we only want it to join on terms_id
-- ON instead of USING because the column names are different
/*WHERE vendor_state = 'OH' OR vendor_state = 'NJ' OR
vendor_state = 'PA' OR vendor_state = 'NC' OR
vendor_state = 'NV'*/
WHERE vendor_state > 'N' AND vendor_state < 'Q'
ORDER BY terms_description;
