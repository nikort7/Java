DROP TABLE Competition_Country CASCADE CONSTRAINTS PURGE;
DROP TABLE Sport_Sportsmen CASCADE CONSTRAINTS PURGE;
DROP TABLE Trainer_Sportsmen CASCADE CONSTRAINTS PURGE;
DROP TABLE Trainer CASCADE CONSTRAINTS PURGE;
DROP TABLE Ranks CASCADE CONSTRAINTS PURGE;
DROP TABLE Competition_Sportsmen CASCADE CONSTRAINTS PURGE;
DROP TABLE Sportsmen CASCADE CONSTRAINTS PURGE;
DROP TABLE Country CASCADE CONSTRAINTS PURGE;
DROP TABLE Competition CASCADE CONSTRAINTS PURGE;
DROP TABLE Sport CASCADE CONSTRAINTS PURGE;
DROP TABLE Userclube CASCADE CONSTRAINTS PURGE;

/*ALTER SESSION SET NLS_DATE_FORMAT = 'DD.MM.YYYY';*/
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD';

CREATE TABLE Sport
(
	id_sport             INTEGER NOT NULL ,
	Name_sport           VARCHAR2(50) NULL ,
    PRIMARY KEY (id_sport)
);

CREATE TABLE Competition
(
	id_comp              INTEGER NOT NULL ,
	Name_comp            VARCHAR2(100) NULL ,
	Place_comp           VARCHAR2(100) NULL ,
	Date_comp            VARCHAR2(10) NULL ,
	id_sport             INTEGER NOT NULL ,
    PRIMARY KEY (id_comp),
    FOREIGN KEY (id_sport) REFERENCES Sport (id_sport) ON DELETE SET NULL
);

CREATE TABLE Country
(
	id_country           INTEGER NOT NULL ,
	Name_country         VARCHAR2(20) NULL ,
    PRIMARY KEY (id_country)
);

CREATE TABLE Ranks
(
	id_zv                INTEGER NOT NULL ,
	Name_zv              VARCHAR2(70) NULL ,
    PRIMARY KEY (id_zv)
);

CREATE TABLE Sportsmen
(
	id_s                 INTEGER NOT NULL ,
	FIO                  VARCHAR2(40) NULL , 
	Date_Birthday        VARCHAR2(10) NULL ,
    Sex                  VARCHAR2(2) NULL ,
	id_country           INTEGER NOT NULL ,
    PRIMARY KEY (id_s),
    FOREIGN KEY (id_country) REFERENCES Country (id_country) ON DELETE SET NULL,
    FOREIGN KEY (id_s) REFERENCES Userclube (user_id)
);

/*-------------------------------------------------------------*/
CREATE PROCEDURE ADDSPORTSMEN (USERPHONE_ IN VARCHAR, PASSWORD_ IN VARCHAR, 
                               FIO_ IN VARCHAR, DATA_BIRTHDAY_ IN VARCHAR2, SEX_ IN VARCHAR, ID_COUNTRY_ IN INTEGER,
                               ID_SPORT_ IN INTEGER)
IS
BEGIN
    DECLARE
        lastID INTEGER;
    BEGIN
        INSERT INTO Userclube(userphone, userpassword) VALUES (USERPHONE_, PASSWORD_);
          SELECT MAX(user_id) into lastID FROM Userclube;
        INSERT INTO Sportsmen(id_s, FIO, Date_Birthday, Sex, id_country) 
                    VALUES (lastID, FIO_, DATA_BIRTHDAY_, SEX_, ID_COUNTRY_);
        INSERT INTO Sport_Sportsmen(id_sport, id_s) VALUES (ID_SPORT_, lastID);
    END;
END ADDSPORTSMEN;

CREATE PROCEDURE DELETESPORTSMEN (id_sportsmen IN INTEGER)
IS
BEGIN
    DELETE FROM Sportsmen WHERE id_s = id_sportsmen;
    DELETE FROM Userclube WHERE user_id = id_sportsmen;
    DELETE FROM SPORT_SPORTSMEN WHERE ID_S = id_sportsmen;
END DELETESPORTSMEN;

/*-------------------------------------------------------------*/

CREATE TABLE Competition_Sportsmen
(
	id_s                 INTEGER NOT NULL ,
	id_comp              INTEGER NOT NULL ,
    PRIMARY KEY (id_s, id_comp),
    FOREIGN KEY (id_s) REFERENCES Sportsmen (id_s),
    FOREIGN KEY (id_comp) REFERENCES Competition (id_comp)
);

CREATE TABLE Trainer
(
	id_t                 INTEGER NOT NULL ,
	FIO                  VARCHAR2(40) NULL ,
	Date_Birthday        VARCHAR2(10) NULL ,
    Sex                  VARCHAR2(2) NULL ,
	id_country           INTEGER NULL ,
	id_zv                INTEGER NULL ,
	id_sport             INTEGER NULL ,
    PRIMARY KEY (id_t),
    FOREIGN KEY (id_t) REFERENCES Userclube (user_id),
    FOREIGN KEY (id_zv) REFERENCES Ranks (id_zv) ON DELETE SET NULL,
    FOREIGN KEY (id_sport) REFERENCES Sport (id_sport) ON DELETE SET NULL,
    FOREIGN KEY (id_country) REFERENCES Country (id_country) ON DELETE SET NULL
);

/*-------------------------------------------------------------------------------*/
CREATE PROCEDURE ADDTRAINER (USERPHONE_ IN VARCHAR2, PASSWORD_ IN VARCHAR2, 
                             FIO_ IN VARCHAR, DATE_BIRTHDAY_ IN VARCHAR2, SEX_ IN VARCHAR, ID_COUNTRY_ IN INTEGER, ID_ZV_ IN INTEGER,
                             ID_SPORT_ IN INTEGER)
IS
BEGIN
    DECLARE
        lastID INTEGER;
    BEGIN
        INSERT INTO Userclube(userphone, userpassword) VALUES (USERPHONE_, PASSWORD_);
          SELECT MAX(user_id) into lastID FROM Userclube;
        INSERT INTO Trainer (id_t, FIO, Date_Birthday, Sex, id_country, id_zv, id_sport)
                    VALUES (lastID, FIO_, DATE_BIRTHDAY_, SEX_, ID_COUNTRY_, ID_ZV_, ID_SPORT_);
    END;
END ADDTRAINER;

CREATE PROCEDURE DELETETRAINER (id_trainer IN INTEGER)
IS
BEGIN
    DELETE FROM Trainer WHERE id_t = id_trainer;
    DELETE FROM Userclube WHERE user_id = id_trainer;
END DELETETRAINER;
/*-------------------------------------------------------------------------------*/

CREATE TABLE Trainer_Sportsmen
(
	id_t                 INTEGER NOT NULL ,
	id_s                 INTEGER NOT NULL ,
    PRIMARY KEY (id_t,id_s),
    FOREIGN KEY (id_t) REFERENCES Trainer (id_t),
    FOREIGN KEY (id_s) REFERENCES Sportsmen (id_s)
);

CREATE TABLE Sport_Sportsmen
(
	id_sport             INTEGER NOT NULL ,
	id_s                 INTEGER NOT NULL ,
    PRIMARY KEY (id_sport,id_s),
    FOREIGN KEY (id_sport) REFERENCES Sport (id_sport),
    FOREIGN KEY (id_s) REFERENCES Sportsmen (id_s)
);

CREATE TABLE Competition_Country
(
	id_comp              INTEGER NOT NULL ,
	id_country           INTEGER NOT NULL ,
    PRIMARY KEY (id_comp,id_country),
    FOREIGN KEY (id_comp) REFERENCES Competition (id_comp),
    FOREIGN KEY (id_country) REFERENCES Country (id_country)
);

CREATE TABLE Userclube
(
    user_id              INTEGER NOT NULL,
	userphone            VARCHAR2(12) NOT NULL Unique,
	userpassword         VARCHAR2(50) NOT NULL,
    PRIMARY KEY (user_id)
);


/*--------------------*/
CREATE sequence sq_Userclube
START WITH 1
INCREMENT BY 1;

CREATE sequence sq_Trainer
START WITH 1
INCREMENT BY 1;

CREATE sequence sq_Sportsmen
START WITH 1
INCREMENT BY 1;

CREATE sequence sq_Competition
START WITH 1
INCREMENT BY 1;
 
CREATE OR REPLACE TRIGGER tr_Userclube
before INSERT OR UPDATE ON Userclube
FOR each ROW
BEGIN
   IF :NEW.user_id IS NULL THEN
     SELECT sq_Userclube.NEXTVAL INTO :NEW.user_id FROM dual;
   END IF;
END;
/*--------------------*/
CREATE OR REPLACE TRIGGER tr_Trainer
before INSERT OR UPDATE ON Trainer
FOR each ROW
BEGIN
   IF :NEW.id_t IS NULL THEN
     SELECT sq_Trainer.NEXTVAL INTO :NEW.id_t FROM dual;
   END IF;
END;
/*--------------------*/
CREATE OR REPLACE TRIGGER tr_Sportsmen
before INSERT OR UPDATE ON Sportsmen
FOR each ROW
BEGIN
   IF :NEW.id_s IS NULL THEN
     SELECT sq_Sportsmen.NEXTVAL INTO :NEW.id_s FROM dual;
   END IF;
END;
/*---------------------*/
CREATE OR REPLACE TRIGGER tr_Competition
before INSERT OR UPDATE ON Competition
FOR each ROW
BEGIN
   IF :NEW.id_comp IS NULL THEN
     SELECT sq_Competition.NEXTVAL INTO :NEW.id_comp FROM dual;
   END IF;
END;

DROP TRIGGER tr_Userclube;
DROP TRIGGER tr_Trainer;
DROP TRIGGER tr_Sportsmen;
DROP TRIGGER tr_Competition;
DROP SEQUENCE sq_Userclube;
DROP SEQUENCE sq_Trainer;
DROP SEQUENCE sq_Sportsmen;
DROP SEQUENCE sq_Competition;
