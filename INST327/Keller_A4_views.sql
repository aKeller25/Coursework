
/*Question 1*/
USE ForestGlenInn;

CREATE OR REPLACE VIEW A4_Q1 AS
	SELECT CONCAT(first_name, ', ', last_name) AS 'Guest Name', 
    DATE_FORMAT(check_in_date,'%W, %M %d, %Y') AS check_in_date, 
    DATE_FORMAT(check_out_date,'%W, %M %d, %Y') as check_out_date
    FROM reservations
		JOIN guests USING(guest_id)
	WHERE DATE_FORMAT(check_in_date,'%W') != 'Saturday' AND DATE_FORMAT(check_out_date,'%W') = 'Sunday' 
	ORDER BY DATE_FORMAT(check_in_date,'%Y'), DATE_FORMAT(check_in_date,'%c'), DATE_FORMAT(check_in_date,'%e'), last_name, first_name;
    
/*Question 2*/
USE ForestGlenInn;

CREATE OR REPLACE VIEW A4_Q2 AS
	SELECT CONCAT(first_name, ', ', last_name) AS 'Guest Name', 
    room_number, 
    DATE_FORMAT(check_in_date,'%W, %M %d, %Y') AS check_in_date, 
    DATE_FORMAT(check_out_date,'%W, %M %d, %Y') as check_out_date,
    ABS(DATEDIFF(check_in_date, check_out_date)) AS length_of_reservation,
    CONCAT('$', base_price) as price_per_night
    FROM reservations
		JOIN guests USING(guest_id)
        JOIN rooms USING(room_id)
        JOIN room_types USING(room_type_id)
	WHERE ABS(DATEDIFF(check_in_date, check_out_date)) > 3
	ORDER BY ABS(DATEDIFF(check_in_date, check_out_date)) DESC, last_name, first_name;
    