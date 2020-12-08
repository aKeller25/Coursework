USE ap;

SELECT count(*)
FROM invoices;

/*Question 10
USE ap;

SELECT invoice_number, payment_date, invoice_due_date, credit_total
FROM invoices
WHERE (credit_total > 0) & (payment_date > invoice_due_date);*/

/*Question 9
USE ex;

SELECT CONCAT(customer_last_name, ', ', customer_first_name) AS 'Full Name'
FROM customers; */



/*Question 8
USE om;

SELECT DISTINCT artist
FROM items
ORDER BY artist;*/

/*Question 7
USE ap;

SELECT invoice_number, payment_date, payment_total
FROM invoices 
WHERE payment_date IS NULL; */


/*Question 6
USE ap;

SELECT vendor_name, vendor_city
FROM vendors
WHERE vendor_state = 'CA'
ORDER BY vendor_city, vendor_name;*/

/* Question 5
USE om;

SELECT customer_last_name, customer_first_name
FROM customers
WHERE customer_state = 'DC'; */


/*USE ap;

SELECT * FROM vendor_city 
WHERE vendor_city LIKE 'San%' ORDER BY vendor_city DESC; 
-- ASC is the default orderdepartmentsdepartmentsdepartments*/