create database DB_daret;

use DB_daret;

create table utilisateur(
	id Bigint not null auto_increment,
	nom varchar(100) not null,
	prenom varchar(100) not null,
	mail varchar(30) not null,
	mot_passe VARCHAR(1000),
    activer bool default true,
    roles varchar(100) not null,
    date_inscription date not null,
	primary key(id)
);

CREATE TABLE darets (
    id bigint NOT NULL auto_increment,
    nom VARCHAR(100) NOT NULL,
    date_debut DATE NOT NULL,
    periodicite VARCHAR(10) NOT NULL,
    montant DOUBLE NOT NULL,
    maximun INT NOT NULL,
    description VARCHAR(500) NOT NULL,
    activer BOOLEAN DEFAULT true, -- para fazer uma exclusao logica
    PRIMARY KEY (id)
);
create table participation(
	id bigint primary key,
	id_membre bigint not null ,
	id_daret bigint not null,
    quantite int default 1, -- combien de place il vas prendre dans le dare --maximum 2
    date_participation date not null,
	CONSTRAINT fk_participant FOREIGN KEY (id_membre) REFERENCES utilisateur(id),
	constraint fk_daret FOREIGN KEY (id_daret) REFERENCES darets(id)
);
create table payments(
	id bigint primary key,
	id_membre bigint not null,
	id_daret bigint not null,
    id_admin bigint not null,
    date_payement date not null,
	CONSTRAINT fk_participant_payements FOREIGN KEY (id_membre) REFERENCES utilisateur(id),
	constraint fk_daret_payements FOREIGN KEY (id_daret) REFERENCES darets(id),
    constraint fk_admin FOREIGN KEY (id_admin) REFERENCES utilisateur(id)
);

create table versement( -- table que contien les registre des payements fait aux utilisateur
	id bigint primary key auto_increment,
    id_daret bigint not null,
    id_membre bigint not null,
    date_versement date not null,
    CONSTRAINT fk_versement_membre FOREIGN KEY (id_membre) REFERENCES utilisateur(id),
	constraint fk_versement_daret FOREIGN KEY (id_daret) REFERENCES darets(id)
); 

------------------------------------------------------------------------------------------------------------------------

INSERT INTO utilisateur (nom, prenom, mail, mot_passe,roles, date_inscription, activer)
VALUES ('semedo', 'ima', 'email@exemplo.com', 'senha123','MEMBRE', '2023-12-31', true);

INSERT INTO utilisateur (nom, prenom, mail, mot_passe,roles, date_inscription, activer)
VALUES ('junior', 'erickson', 'junior@exemplo.com', 'senha123','ADMIM', '2024-1-31', true);

INSERT INTO utilisateur (nom, prenom, mail, mot_passe,roles, date_inscription, activer)
VALUES ('mide', 'tchancarna', 'mide@exemplo.com', 'senha123','MEMBRE', '2024-1-23', true);

ALTER TABLE darets CHANGE COLUMN Montant_periode montant_periode  DOUBLE NOT NULL;
ALTER TABLE darets DROP COLUMN tour_role;

INSERT INTO `DB_daret`.`utilisateur`
(`id`,
`nom`,
`prenom`,
`mail`,
`mot_passe`,
`activer`,
`roles`,
`date_inscription`)
VALUES
(	
	1,
	"junior",
	"user",
	"junior@exemplo.com",
	"$2a$12$GOCW1OBmjkZeOwOy5NCndOYw8.YGl8nRegYMlDGep4lYrjtVcNZ6C",
    true,
	"ADMIN",
    "2024-01-20"
);

INSERT INTO `DB_daret`.``
(`id`,`id_membre`,`id_daret`,`quantite`,`date_participation`)
VALUES
(4,3,1,1,"2024-01-20");

------------------------------------------------------------------------------------------------------------
SELECT SUM(quantite) FROM `DB_daret`.`participation` Where id_daret = 2;

SELECT * FROM `DB_daret`.`participation`;

SELECT * FROM `DB_daret`.`darets`;

SELECT * FROM `DB_daret`.`payments`;

SELECT * FROM `DB_daret`.`utilisateur`;

SELECT d.id,d.nom,d.description,d.date_debut,d.periodicite,d.maximun_participant,d.activer,d.montant 
FROM darets d INNER JOIN participation p ON p.id_daret = d.id 
WHERE p.id_membre = 3;

SELECT m.nom, p.date_payement, d.nom, a.nom FROM payments p join darets d on p.id_daret = d.id join utilisateur m on p.id_membre = m.id join utilisateur a on p.id_admin = a.id;

SELECT * FROM utilisateur WHERE id = (SELECT MIN(id) FROM utilisateur WHERE date_inscription = (SELECT MIN(date_inscription) FROM utilisateur);

-- proximo usuario que deve receber os versement
SELECT * FROM utilisateur where id not in (select id_membre from versement where id_daret = 1) ORDER BY date_inscription ASC LIMIT 1;


SELECT u.* FROM participation p Join utilisateur u on p.id_membre = u.id WHERE p.id_daret = 1 AND u.id NOT IN (select id_membre from versement where id_daret = 1) ORDER BY date_inscription ASC LIMIT 1;


