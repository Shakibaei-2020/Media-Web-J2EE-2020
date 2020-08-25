DROP TABLE UTILISATEUR CASCADE CONSTRAINTS;
DROP TABLE DOCUMENT CASCADE CONSTRAINTS;
DROP SEQUENCE doc_seq;

CREATE TABLE DOCUMENT (
	idDoc INTEGER CONSTRAINT pk_document PRIMARY KEY,
	typeDoc INTEGER,
	title VARCHAR2(30),
	author VARCHAR2(30),
	description VARCHAR2(255),
	idUser INTEGER,
	emprunte INTEGER DEFAULT 0
);

CREATE TABLE UTILISATEUR (
	idUser INTEGER CONSTRAINT pk_utilisateur PRIMARY KEY,
	login VARCHAR2(30),
	password VARCHAR2(30),
	name VARCHAR2(30),
	bibliothecaire INTEGER DEFAULT 0
);

CREATE SEQUENCE doc_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

ALTER TABLE DOCUMENT
ADD CONSTRAINT fk_utilisateur FOREIGN KEY (idUser) REFERENCES UTILISATEUR(idUser);
 
 INSERT INTO UTILISATEUR(idUser, login, password, name)
 VALUES(1, 'jean-louis', 'mdp', 'Jean-Louis');
 INSERT INTO UTILISATEUR(idUser, login, password, name)
 VALUES(2, 'mohammad', 'mdp', 'Mohammad');
 INSERT INTO UTILISATEUR(idUser, login, password, name, bibliothecaire)
 VALUES(3, 'admin', 'admin', 'Admin', 1);
 INSERT INTO DOCUMENT(idDoc, typeDoc, title, author, description)
 VALUES(doc_seq.nextval, 0, 'Germinal', 'Emile Zola', 'Livre');
 INSERT INTO DOCUMENT(idDoc, typeDoc, title, author, description)
 VALUES(doc_seq.nextval, 0, 'Bel-Ami', 'Guy de Maupassant', 'Livre');
 INSERT INTO DOCUMENT(idDoc, typeDoc, title, author, description)
 VALUES(doc_seq.nextval, 1, 'Parasite', 'Bong Joon Ho', 'Film');
 INSERT INTO DOCUMENT(idDoc, typeDoc, title, author, description)
 VALUES(doc_seq.nextval, 1, 'Les Tuche', 'Olivier Baroux', 'Film');
 INSERT INTO DOCUMENT(idDoc, typeDoc, title, author, description)
 VALUES(doc_seq.nextval, 2, 'PES 2020', 'Konami', 'Jeu');
 INSERT INTO DOCUMENT(idDoc, typeDoc, title, author, description)
 VALUES(doc_seq.nextval, 2, 'FIFA 20', 'EA Sport', 'Jeu');
 INSERT INTO DOCUMENT(idDoc, typeDoc, title, author, description)
 VALUES(doc_seq.nextval, 2, 'GTA 5', 'Rockstar', 'Jeu');

COMMIT
/