USE ForestGlenInn;

DROP PROCEDURE IF EXISTS Set_VIP_status;

DELIMITER //
/* 
	ForestGlenInn has a VIP progam. Set the status for a given guest, based on number of reservations
		- If the guest has stayed more than 7 times, they get GOLD status
        - If the guest has stayed 5 to 7 times, they get SILVER status
		- If the guest has stayed at least 3 times but less than 5, they get a BRONZE status
        - If they have not stayed or have stayed less than 3 times, they are not a VIP
*/
CREATE PROCEDURE Set_VIP_status
(
    /* one input parameters for guest ID  */
	guest_id_param INT
)
BEGIN
	DECLARE sql_error INT DEFAULT FALSE;
	DECLARE num_stays_var INT;
	DECLARE VIP_status_var VARCHAR(10);
    
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    SET sql_error = TRUE;
    
    /* Default to 'Not a VIP' */
    SET VIP_status_var = 'Not a VIP';
    
	/* calculate the number of reservations */
	/* HINT: write a SELECT statement which queries the reservations table using the guest_id parameter. 
	Use the INTO keyword to store the number of stays into the local variable num_stays_var declared above */
	SELECT COUNT(*)
    INTO num_stays_var
    FROM resertvations
    WHERE guest_id = guest_id_param
    GROUP BY guest_id;
    
    /* calculate the VIP status */
    /* HINT: use a sequence of IF/ELSEIF statements to assign the VIP_status_var based on num_stays */
	IF num_stays_var > 7 THEN
		SET VIP_status_var = 'Gold';
	ELSEIF num_stays_var >= 5 THEN
		SET VIP_status_var = 'Silver';
	ELSEIF num_stays_var >= 3 THEN
		SET VIP_status_var = 'Bronze';
	END IF;

	START TRANSACTION;  

	/* update the VIP status for this guest */
    /* HINT: write an UPDATE statement against the guests table using VIP_status_var and guest_id_param */
	UPDATE guests
    SET VIP_status = 'Gold'
    WHERE guest_id = 2;
    
    
	IF sql_error = FALSE THEN
		COMMIT;
	ELSE
		ROLLBACK;
	END IF;
END//

DELIMITER ;

CALL Set_VIP_status(2);

