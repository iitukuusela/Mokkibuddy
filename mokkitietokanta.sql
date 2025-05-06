CREATE DATABASE mokkidb;
USE mokkidb;
CREATE TABLE mokki (
	id INT AUTO_INCREMENT PRIMARY KEY,
    henkilo_maara INT,
    etaisyys VARCHAR(50),
    sauna VARCHAR(10),
    poreamme VARCHAR(10),
    hinta_per_yo DECIMAL(10, 2)
);