/*Question 2*/
SELECT vendor_name, vendor_state
FROM vendors
WHERE vendor_id IN
	-- DISTINCT is only here to speed things up
	(SELECT DISTINCT vendor_id 
    FROM ap.invoice_line_items JOIN ap.invoices
    USING (invoice_id)
    WHERE invoice_line_items > 3000)
ORDER BY vendor_name;

/*Question 3*/
SELECT vendor_name AS 'Vendor Name', 
	invoice_number AS 'Invoice Number',
	CAST(invoice_date AS DATETIME)  AS 'Invoice Date/Time',
    CONCAT('$', FORMAT(invoice_total, 2)) AS 'invoice_total'
FROM invoices i JOIN vendors v
	ON i.vendor_id = i.vendor_id
WHERE invoice_date =
-- This is a corelated subquery because this subquery requires that 'two copies'
-- of invoices exist, one instance where invoices joins vendors, and one instance
-- where you get that max date of invoice date 
	(SELECT MAX(invoice_date)
    FROM invoices
    WHERE vendor_id = i.vendor_id)
ORDER BY vendor_name;    


/*Question 4*/
-- A correlated subquery is a query that can't stand on its own because it requires 
-- information that is only in the query 

SELECT UPPER(vendor_name) AS "Vendor Name",
	REPLACE(REPLACE(REPLACE(vendor_phone, '(', ''), ')', '.'), '-', '.')
    AS 'Vendor Phone',
    DATE_FORMAT(MAX(invoice_date), '%M %d, %Y') AS 'Latest Invoice'
FROM vendors JOIN invoices
	USING (vendor_id)
GROUP BY vendor_name
ORDER BY vendor_name;

    


