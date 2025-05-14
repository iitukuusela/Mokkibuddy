CREATE DATABASE mokkidb;
USE mokkidb;
CREATE TABLE mokki (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    henkilo_maara INTEGER,
    etaisyys TEXT,
    sauna TEXT,
    poreamme TEXT,
    hinta_per_yo REAL
);
