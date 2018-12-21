CREATE TABLE planets  (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE spaceports (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    planet_id INT(11) NOT NULL,
    
    CONSTRAINT `fk_spaceports_planets`
    FOREIGN KEY (planet_id)
    REFERENCES planets(id)
);

CREATE TABLE spaceships (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    manufacturer VARCHAR(30) NOT NULL,
    light_speed_rate INT(11) DEFAULT 0
);


CREATE TABLE colonists (
	id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    ucn CHAR(10) UNIQUE NOT NULL,
    birth_date DATE NOT NULL
    
);

CREATE TABLE journeys (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    journey_start DATETIME NOT NULL,
    journey_end DATETIME NOT NULL,
    purpose ENUM('Medical', 'Technical', 'Educational', 'Military') NOT NULL,
    destination_spaceport_id INT(11) NOT NULL,
    spaceship_id INT(11) NOT NULL,
    
    CONSTRAINT `fk_journeys_spaceports`
    FOREIGN KEY (destination_spaceport_id)
    REFERENCES spaceports(id),
    
    CONSTRAINT `fk_journeys_spaceships`
    FOREIGN KEY (spaceship_id)
    REFERENCES spaceships(id)
);


CREATE TABLE travel_cards (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    card_number CHAR(10) NOT NULL UNIQUE,
    job_during_journey ENUM('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook') NOT NULL ,
    colonist_id INT(11) NOT NULL,
    journey_id INT(11) NOT NULL,
    
    CONSTRAINT `fk_travel_cards_colonists`
    FOREIGN KEY (colonist_id)
    REFERENCES colonists(id),
    
    CONSTRAINT `fk_travel_cards_jurneys`
    FOREIGN KEY (journey_id)
    REFERENCES journeys(id)
    
);