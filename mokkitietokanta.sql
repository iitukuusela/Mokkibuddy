CREATE DATABASE mokkidb;
USE mokkidb;
CREATE TABLE mokki (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    henkilo_maara INTEGER,
    etaisyys TEXT,
    sauna TEXT,
    poreamme TEXT,
    hinta_per_yo REAL
);
