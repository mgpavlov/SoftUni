# -- 01

# INSERT INTO travel_cards (card_number, job_during_journey, colonist_id, journey_id)
# SELECT 
# (CASE 
# WHEN c.birth_date > '1980-1-1' THEN concat(year(c.birth_date), day(c.birth_date), left(c.ucn, 4))
# ELSE concat(year(c.birth_date), Month(c.birth_date), right(c.ucn, 4))
# END), 
# (
# CASE 
# WHEN c.id%2 = 0 THEN 'Pilot'
# WHEN c.id%3 = 0 THEN 'Cook'
# ELSE 'Engineer'
# END
# ),
# c.id,
# LEFT(c.ucn, 1)
# FROM colonists c
# WHERE c.id BETWEEN 96 AND 100;


# # •	If the journey’s id is dividable by 2 without remainder – ‘Medical’.
# # •	If the journey’s id is dividable by 3 without remainder – ‘Technical’.
# # •	If the journey’s id is dividable by 5 without remainder – ‘Educational’.
# # •	If the journey’s id is dividable by 7 without remainder – ‘Military’. 

# -- 02 
# UPDATE journeys j
# SET j.purpose = 
# CASE 
# WHEN j.id%2 = 0  THEN 'Medical'
# WHEN j.id%3 = 0  THEN 'Technical'
# WHEN j.id%5 = 0  THEN 'Educational'
# WHEN j.id%7 = 0  THEN 'Military'
# ELSE j.purpose
# END;

# # 03.	Data Deletion
# # REMOVE from colonists, those which are not assigned to any journey.

# # DELETE c
# # FROM colonists c
# # LEFT JOIN travel_cards tc
# # ON c.id = tc.colonist_id
# # LEFT JOIN journeys j
# # ON j.id = tc.journey_id
# # WHERE j.id IS NULL;


# SELECT *
# FROM colonists c
# LEFT JOIN travel_cards tc
# ON c.id = tc.colonist_id
# LEFT JOIN journeys j
# ON j.id = tc.journey_id
# ;



# 04.Extract all travel cards
# Extract from the database, all travel cards. Sort the results by card number ascending.
# Required Columns
# •	card_number
# •	job_during_journey

SELECT t.card_number, t.job_during_journey
FROM `travel_cards` t
ORDER BY t.card_number;



# 05.Extract all colonists
# Extract from the database, all colonists. Sort the results by first name, them by last name, and finally by id in ascending order.

# Required Columns
# •	id
# •	full_name(first_name + last_name separated by a single space)
# •	ucn

SELECT c.id, concat(c.first_name, ' ', c.last_name) as full_name, c.ucn
FROM colonists c
ORDER BY c.first_name, c.last_name, c.id;

# 06.	Extract all military journeys
# Extract from the database, all Military journeys. Sort the results ascending by journey start.

SELECT j.id, j.journey_start, j.journey_end
FROM journeys j
WHERE j.purpose = 'Military'
ORDER BY j.journey_start;


# 07.	Extract all pilots
# Extract from the database all colonists, which have a pilot job. Sort the result by id, ascending.

# Required Columns
# •	id
# •	full_name

SELECT c.id, concat(c.first_name, ' ', c.last_name) as full_name
FROM colonists c
JOIN travel_cards t
ON c.id = t.colonist_id
WHERE t.job_during_journey = 'Pilot'
ORDER BY c.id ;

# 08.	Count all colonists that are on technical journey
# Count all colonists, that are on technical journey. 

# Required Columns
# •	Count

SELECT count(j.id) count
FROM colonists c
JOIN travel_cards t
ON c.id = t.colonist_id
JOIN journeys j
ON j.id = t.journey_id
WHERE j.purpose = 'Technical'
GROUP BY j.purpose;


# 09.Extract the fastest spaceship
# Extract from the database the fastest spaceship and its destination spaceport name. In other words, the ship with the highest light speed rate.

# Required Columns
# •	spaceship_name
# •	spaceport_name

SELECT s.name, sp.name
FROM spaceships s
JOIN journeys j
ON s.id = j.spaceship_id
JOIN spaceports sp
ON sp.id = j.destination_spaceport_id
ORDER BY s.light_speed_rate DESC
LIMIT 1;


# 10.Extract spaceships with pilots younger than 30 years
# Extract from the database those spaceships, which have pilots, 
#younger than 30 years old. In other words, 30 years from 01/01/2019. 
#Sort the results alphabetically by spaceship name.

# Required Columns
# •	name
# •	manufacturer
SELECT  s.name, s.manufacturer
FROM spaceships s
JOIN journeys j
ON s.id = j.spaceship_id
JOIN travel_cards t
ON t.journey_id = j.id
JOIN colonists c
ON c.id = t.colonist_id
WHERE c.birth_date > '1988-12-31' AND t.job_during_journey = 'Pilot'
GROUP BY s.id
ORDER BY s.name;




# 11. Extract all educational mission planets and spaceports
# Extract from the database names of all planets and their spaceports, 
#which have educational missions. 
#Sort the results by spaceport name in descending order.

# Required Columns
# •	planet_name
# •	spaceport_name


SELECT p.name as planet_name, sp.name as spaceport_name
FROM journeys j
JOIN spaceports sp
ON sp.id = j.destination_spaceport_id
JOIN planets p
ON p.id = sp.planet_id
WHERE j.purpose = 'Educational'
ORDER BY sp.name DESC;


# 12. Extract all planets and their journey count
# Extract from the database all planets’ names and their journeys count.
# Order the results by journeys count, descending and by planet name ascending.
# Required Columns
# •	planet_name
# •	journeys_count


SELECT p.name as planet_name, count(j.id) as journeys_count
FROM journeys j
RIGHT JOIN spaceports sp
ON sp.id = j.destination_spaceport_id
RIGHT JOIN planets p
ON p.id = sp.planet_id
GROUP BY planet_name
ORDER BY journeys_count DESC, planet_name;


# 13.Extract the shortest journey
# Extract from the database the shortest journey, 
#its destination spaceport name, planet name and purpose.
# Required Columns
# •	Id
# •	planet_name
# •	spaceport_name
# •	journey_purpose


SELECT j.id, p.name as planet_name, sp.name as spaceport_name, j.purpose as journey_purpose
FROM journeys j
JOIN spaceports sp
ON sp.id = j.destination_spaceport_id
JOIN planets p
ON p.id = sp.planet_id
GROUP BY j.id
HAVING MIN(datediff(j.journey_end, j.journey_start))
ORDER BY MIN(datediff(j.journey_start, j.journey_end)) DESC
limit 1
;

# 14.Extract the less popular job
# Extract from the database the less popular job in the longest journey. In other words, the job with less assign colonists.

# Required Columns
# •	job_name

# Example
# job_name
# Engineer

SELECT t.job_during_journey as job_name
from (SELECT j.id, p.name as planet_name, sp.name as spaceport_name, j.purpose as journey_purpose
FROM journeys j
JOIN spaceports sp
ON sp.id = j.destination_spaceport_id
JOIN planets p
ON p.id = sp.planet_id
GROUP BY j.id
HAVING MIN(datediff(j.journey_end, j.journey_start))
ORDER BY MIN(datediff(j.journey_start, j.journey_end))
limit 1) as tb
JOIN travel_cards t
ON t.journey_id = tb.id
GROUP BY t.job_during_journey
ORDER BY count(DISTINCT t.colonist_id)
limit 1;



-- longest jurney

SELECT j.id, p.name as planet_name, sp.name as spaceport_name, j.purpose as journey_purpose
FROM journeys j
JOIN spaceports sp
ON sp.id = j.destination_spaceport_id
JOIN planets p
ON p.id = sp.planet_id
GROUP BY j.id
HAVING MIN(datediff(j.journey_end, j.journey_start))
ORDER BY MIN(datediff(j.journey_start, j.journey_end))
limit 1
;



# 15. Get colonists count
# Create a user defined function with the name 
#udf_count_colonists_by_destination_planet (planet_name VARCHAR (30)) 
#that receives planet name and returns the count of all colonists sent to that planet.



CREATE FUNCTION udf_count_colonists_by_destination_planet(planet_name VARCHAR (30))
RETURNS INT
RETURN (
    SELECT COUNT(*) as count
FROM planets p
LEFT JOIN spaceports sp
ON p.id = sp.planet_id
JOIN journeys j
ON sp.id = j.destination_spaceport_id
JOIN travel_cards t
ON t.journey_id = j.id
JOIN colonists c
ON c.id = t.colonist_id
WHERE p.name = planet_name
GROUP BY p.name);













SET GLOBAL log_bin_trust_function_creators = 1;


SELECT COUNT(t.colonist_id)
FROM planets p
left JOIN spaceports sp
ON p.id = sp.planet_id
JOIN journeys j
ON sp.id = j.destination_spaceport_id
JOIN travel_cards t
ON t.journey_id = j.id
WHERE p.name = 'Otroyphus'
GROUP BY p.name;


# 16. Modify spaceship
# Create a user defined stored procedure with 
# the name udp_modify_spaceship_light_speed_rate(spaceship_name VARCHAR(50)
# , light_speed_rate_increse INT(11)) 
# that receives a spaceship name and light speed increase value 
# and increases spaceship light speed only if the given spaceship exists. 
# If the modifying is not successful rollback any changes and 
# throw an exception with error code ‘45000’ 
# and message: “Spaceship you are trying to modify does not exists.” 



DELIMITER $$
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate
			(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
    IF ((SELECT COUNT(s.id) FROM `spaceships` AS s WHERE s.name = spaceship_name) = 0) THEN
        SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
        ROLLBACK;
    ELSE
        UPDATE `spaceships` AS s
        SET 
            s.light_speed_rate = s.light_speed_rate + light_speed_rate_increse
        WHERE
            s.name = spaceship_name;
        COMMIT;
    END IF;
END $$
DELIMITER ;

CALL `udp_modify_spaceship_light_speed_rate` ('Na Pesho koraba', 1914);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'Na Pesho koraba';

execute udp_modify_spaceship_light_speed_rate ('USS Templar', 5);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'USS Templar';





SELECT count(*) as count
FROM travel_cards t
WHERE t.journey_id IN (SELECT j.id
from journeys j
WHERE j.purpose = 'Technical');



#planet id
SELECT p.id
from planets p
WHERE p.name='Otroyphus';

#letishta  na planetite
SELECT sp.id
from spaceports
WHERE sp.planet_id IN (SELECT p.id
from planets p
WHERE p.name='Otroyphus');

SELECT j.id
from journeys j
WHERE j.destination_spaceport_id IN (SELECT sp.id
from spaceports
WHERE sp.planet_id IN (SELECT p.id
from planets p
WHERE p.name='Otroyphus'));


SELECT count(*)
FROM travel_cards t
WHERE t.journey_id IN (SELECT j.id
from journeys j
WHERE j.destination_spaceport_id IN (SELECT sp.id
from spaceports sp
WHERE sp.planet_id IN (SELECT p.id
from planets p
WHERE p.name='Otroyphus')));
