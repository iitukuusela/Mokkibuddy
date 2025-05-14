CREATE DATABASE asiakasdb;
USE asiakasdb;
CREATE TABLE varaus (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nimi VARCHAR(100),
    sahkoposti VARCHAR(100),
    puhelin VARCHAR(20),
    henkilo_lkm INT,
    mokki VARCHAR(100),
    lisa_sanky BOOLEAN,
    siivous BOOLEAN,
    myohainen_uloskirjautuminen BOOLEAN,
    summa DECIMAL(10, 2),
    kortti_numero VARCHAR(20),
    voimassaoloaika VARCHAR(10),
    turvakoodi VARCHAR(10),
    saapumispvm DATE,
    lahtopaivamaara DATE
    
);