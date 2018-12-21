-- Section 4: Programmability – 30 pts BUHTIG
-- 15. Commit
-- Create a stored procedure udp_commit which accepts the following parameters:
-- username, password, message, issue_id
-- And checks the following things:
-- If the username does NOT exist in the users table:
--  Throw an exception with error code ‘45000’ and message ‘No such user!’.
-- If the password does NOT match the username in the users table:
--  Throw an exception with error code ‘45000’ and message ‘Password is incorrect!’.
-- If there is no issue with the given id in the issues table:
--  Throw an exception with error code ‘45000’ and message ‘The issue does not exist!’.
-- If all checks pass, extract the id of the corresponding user,
-- from the users table, then the repository_id of the issue, from the issues table,
-- and INSERT a new commit into the commits table with the extracted data.
-- The procedure should also update the issue’s status to ‘closed’.

DROP PROCEDURE IF EXISTS udp_commit;

DELIMITER $$
CREATE PROCEDURE udp_commit
    (username VARCHAR(30), password VARCHAR(30), message VARCHAR(255), issue_id INT)
BEGIN
    START TRANSACTION;
    
    IF ((SELECT COUNT(u.id) FROM `users` AS u WHERE u.username = username) = 0) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'No such user!';
        ROLLBACK;
    ELSEIF ((SELECT u.password FROM `users` AS u WHERE u.username = username) <> password) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Password is incorrect!';
        ROLLBACK;
    ELSEIF ((SELECT COUNT(i.id) FROM `issues` AS i WHERE i.id = issue_id) = 0) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'The issue does not exist!';
        ROLLBACK;
    ELSE
        INSERT INTO `commits` 
            (`message`, `issue_id`, `repository_id`, `contributor_id`)
        VALUES
            (message,
            issue_id,
            (SELECT i.repository_id FROM `issues` AS i WHERE i.id = issue_id),
            (SELECT u.id FROM `users` AS u WHERE u.username = username));
        UPDATE `issues` AS i 
        SET 
            i.issue_status = 'closed'
        WHERE
            i.id = issue_id;
        COMMIT;
    END IF;
END $$
DELIMITER ;

CALL udp_commit('WhoDenoteBel', 'ajmISQi*', 'Fixed issue: blah', 2);


-- 16. Filter Extensions
-- Create a stored procedure udp_findbyextension which accepts the following parameters:
-- extension
-- And extracts all files that have the given extension. (like index.html for example)
-- The procedure should extract the file’s id, name and size.
-- The file’s size should have “KB” attached to it as a suffix.
-- The files should be ordered ascending by file id.

DROP PROCEDURE IF EXISTS udp_findbyextension;

DELIMITER $$
CREATE PROCEDURE udp_findbyextension(extension VARCHAR(100))
BEGIN
    SELECT 
        f.id, 
        f.name AS 'caption', 
        CONCAT(f.size, 'KB') AS 'user'
    FROM 
        `files` AS f 
    WHERE 
        f.name LIKE (CONCAT('%', extension))
    ORDER BY f.id;
END $$
DELIMITER ;

CALL udp_findbyextension('html');

-- ===================================================================================
-- INSTAGRAPH

# Create a stored procedure udp_post which accepts the following parameters:
# •	username
# •	password
# •	caption
# •	path
# And checks the following things:
# If the password does NOT match the username in the users table:
# Throw an exception with error code ‘45000’ and message ‘Password is incorrect!’.
# If there is no picture with the given path in the pictures table:
# Throw an exception with error code ‘45000’ and message ‘The picture does not exist!’.
# If all checks pass, extract the id of the corresponding user, from the users table, 
#then the picture id from the pictures table and INSERT a new post into the posts table with the extracted data.
-- 15.	Post 10/10

DROP PROCEDURE IF EXISTS udp_post;

DELIMITER $$
CREATE PROCEDURE udp_post
    (username VARCHAR(30), password VARCHAR(30), caption VARCHAR(255), path VARCHAR(255))
BEGIN
    START TRANSACTION;
    
    IF ((SELECT u.password FROM `users` AS u WHERE u.username = username) <> password) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Password is incorrect!';
        ROLLBACK;
    ELSEIF ((SELECT COUNT(p.id) FROM `pictures` AS p WHERE p.path = path) = 0) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'The picture does not exist!';
        ROLLBACK;
    ELSE
        INSERT INTO `posts` 
            (`caption`, `user_id`, `picture_id`)
        VALUES
            (caption,
            (SELECT u.id FROM `users` AS u WHERE u.usernam = username),
            (SELECT pic.id FROM `pictures` AS pic WHERE pic.path = path));
        COMMIT;
    END IF;
END $$
DELIMITER ;

-- -------------------------------------------------------------------------------------------------------
-- 16.	Filter 10/10
# Create a stored procedure udp_filter which accepts the following parameters:
# •	hashtag
# And extracts all posts that CONTAIN the given hashtag in their caption.
# The procedure should extract the user’s username.
# The hashtag will be given WITHOUT the ‘#’ sign.
# The posts should be ordered ascending by post id.

DROP PROCEDURE IF EXISTS udp_findbyextension;

DELIMITER $$
CREATE PROCEDURE udp_ffilter(hashtag VARCHAR(100))
BEGIN
    SELECT 
        f.id, 
        f.caption, 
        (SELECT username FROM users u WHERE u.id = f.user_id) AS 'user'
    FROM 
        `posts` AS f 
    WHERE 
        f.caption LIKE (CONCAT('%','#', hashtag, '%'))
    ORDER BY f.id;
END $$
DELIMITER ;

CALL udp_findbyextension('html');

-- -------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------


-- 17. Get Reports
-- Create a user defined function with the name
-- udf_get_reports_count(employee_id INT, status_id INT)
-- that receives an employee’s Id and a status Id returns the
-- sum of the reports he is assigned to with the given status.

-- Example usage:
SELECT 
    id,
    first_name,
    last_name,
    UDF_GET_REPORTS_COUNT(id, 2) AS reports_count
FROM
    employees AS e
ORDER BY e.id;


CREATE FUNCTION udf_get_reports_count(employee_id INT, status_id INT)
RETURNS INT
RETURN (
    SELECT COUNT(r.id) 
    FROM `reports` AS r 
    WHERE r.employee_id = employee_id 
        AND r.status_id = status_id);


-- 18. Assign Employee
-- Create a user defined stored procedure with the name
-- usp_assign_employee_to_report(employee_id INT, report_id INT)
-- that receives an employee’s Id and a report’s Id and assigns
-- the employee to the report only if the department of the employee
-- and the department of the report’s category are the same.
-- If the assigning is not successful rollback any changes and throw
-- an exception with message:
-- “Employee doesn't belong to the appropriate department!”

-- Example usage:
CALL usp_assign_employee_to_report(30, 1); -- Response: Employee doesn't belong to the appropriate department!

CALL usp_assign_employee_to_report(17, 2);
SELECT employee_id FROM reports WHERE id = 2; -- Response: 17

DELIMITER $$
CREATE PROCEDURE usp_assign_employee_to_report(employee_id INT, report_id INT)
BEGIN
	DECLARE employee_department_id INT DEFAULT (SELECT e.department_id FROM `employees` AS e WHERE e.id = employee_id);
	DECLARE report_category_id INT DEFAULT (SELECT r.category_id FROM `reports` AS r WHERE r.id = report_id);
	DECLARE category_department_id INT DEFAULT (SELECT c.department_id FROM `categories` AS c WHERE c.id = report_category_id);
	
	START TRANSACTION;
    IF(employee_department_id != category_department_id) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Employee doesn\'t belong to the appropriate department!';
        ROLLBACK;
    ELSE
        UPDATE `reports` AS r
            SET r.employee_id = employee_id
            WHERE r.id = report_id;
        COMMIT;
    END IF;
END $$
DELIMITER ;


-- 19. Close Reports
-- Create a trigger which changes the status_id to “completed”
-- of each report after a close_date is entered for the report. 
-- Example usage:

UPDATE reports 
SET 
    close_date = NOW()
WHERE
    employee_id = 1;
    
-- Expected Response: 2 row(s) affected

DELIMITER $$
CREATE TRIGGER `tr_report_closed`
BEFORE UPDATE ON `reports`
FOR EACH ROW
BEGIN
    IF (ISNULL(OLD.close_date) <> ISNULL(NEW.close_date)) THEN
        SET NEW.status_id = 3;
    END IF;
END $$
DELIMITER ;


-- Section 5. Bonus (10 pts)
-- 20. Categories Revision
-- Select all categories which have reports with status “waiting” or
-- “in progress” and show their total number in the column “Reports Number”.
-- In the third column fill the main status type of reports for the
-- category (e.g. 2 reports with status “waiting” and 3 reports with
-- status “in progress” result in value “in progress”).
-- If they are equal just fill in “equal”.
/* Example:
category_name       reports_number  main_status
Animal in Danger    1               in progress
Art Events          2               equal
Dangerous Building  1               waiting
…                   …               …           */

SELECT 
    c.name AS 'category_name',
    COUNT(r.id) AS 'reports_number',
    IF(COUNT(IF(r.status_id = 1, 1, NULL)) = COUNT(IF(r.status_id = 2, 1, NULL)),
        'equal',
        IF(COUNT(IF(r.status_id = 1, 1, NULL)) > COUNT(IF(r.status_id = 2, 1, NULL)),
            'waiting',
            'in progress')) AS 'main_status'
FROM
    `categories` AS c
        JOIN
    `reports` AS r ON c.id = r.category_id
WHERE
    r.status_id IN (1 , 2)
GROUP BY c.id
ORDER BY c.name;

