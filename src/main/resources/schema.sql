CREATE TABLE IF NOT EXISTS `USER`
(    `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
     `LOGIN` VARCHAR(20) NOT NULL,
     `PASSWORD` VARCHAR(20) NOT NULL,
     `NAME` VARCHAR(30) NOT NULL,
     `LASTNAME` VARCHAR(30),
     PRIMARY KEY (`ID`)

) ;

CREATE TABLE IF NOT EXISTS `POI`
(    `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
     `NAME` VARCHAR(30),
     `X_COORDANATE` BIGINT(10),
     `Y_COORDANATE` BIGINT(10),
     PRIMARY KEY (`ID`)
);
