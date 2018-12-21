DELIMITER $$
DROP PROCEDURE IF EXISTS usp_get_older$$

CREATE PROCEDURE usp_get_older(minion_id INT)
  BEGIN
    UPDATE minions AS m SET m.age = m.age + 1
    WHERE m.id = minion_id;
    END$$
    DELIMITER ;