/*Question 2*/
SELECT vendor_name,
	CONCAT('$ ', FORMAT(SUM(invoice_total),2)) AS "invoice_total_sum"
FROM vendors JOIN invoices USING(vendor_id)
GROUP BY vendor_id
ORDER BY vendor_name;

/*Question 3*/
SELECT vendor_name,
	COUNT(DISTINCT invoice_id) AS invoice_count, 
    CONCAT('$ ', FORMAT(SUM(invoice_total),2)) AS "invoice_total_sum",
    CONCAT('$ ', FORMAT(AVG(invoice_total),2)) AS "invoice_average"
FROM vendors JOIN invoices USING(vendor_id)
GROUP BY vendor_name
ORDER BY invoice_count DESC;

/*Question 4*/
SELECT vendor_name, COUNT(DISTINCT account_number) AS "number_of_accounts"
FROM vendors 
	JOIN invoices USING(vendor_id)-- ON vendors.default_account_number = invoice_line_items.account_number
    JOIN invoice_line_items USING(invoice_id)
-- Use multiple joins (vendors, invoice, [End goal] general_ledger_accounts?, and more?) to make it work??
GROUP BY vendor_id
HAVING  COUNT(DISTINCT account_number)  > 1 -- AND invoices.payment_total > 0
ORDER BY vendor_id;
/*Having vs. where??*/

/*Question 5*/
SELECT DISTINCT vendor_name
FROM vendors 
WHERE vendor_id IN 
	(SELECT DISTINCT vendor_id FROM invoices)
ORDER BY vendor_name;

/*Question 6*/
/*SELECT AVG(payment_total) FROM invoices
	WHERE payment_total > 0*/

SELECT invoice_number, invoice_total
FROM invoices
WHERE payment_total >
	(SELECT AVG(payment_total) FROM invoices
	WHERE payment_total > 0)
ORDER BY invoice_total DESC;
