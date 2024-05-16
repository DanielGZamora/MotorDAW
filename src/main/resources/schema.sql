DROP TABLE INCLUYE IF EXISTS;
DROP TABLE EQUIPAMIENTO IF EXISTS;
DROP TABLE MODELO IF EXISTS;
DROP TABLE MOTOR IF EXISTS;
DROP SEQUENCE IF EXISTS MOTOR_SEQ;
DROP SEQUENCE IF EXISTS MODELO_SEQ;
DROP SEQUENCE IF EXISTS EQUIPAMIENTO_SEQ;

CREATE SEQUENCE MOTOR_SEQ START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE MODELO_SEQ START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE EQUIPAMIENTO_SEQ START WITH 100 INCREMENT BY 1;


-- MOTOR (ID, Tipo, Potencia, Precio)
CREATE TABLE MOTOR (
	ID BIGINT,
	Tipo VARCHAR2(30),
	Potencia FLOAT,
	Precio FLOAT,
	CONSTRAINT PK_MOTOR PRIMARY KEY (ID),
	CONSTRAINT CK1_MOTOR CHECK ( Precio > 0 ),
	CONSTRAINT CK2_MOTOR CHECK ( Potencia > 0 )
);

-- MODELO (ID, Nombre, Precio_base, IDMOT)
CREATE TABLE MODELO (
	ID BIGINT,
	Nombre VARCHAR2(30),
	Precio_base FLOAT,
	IDMOT BIGINT,
	CONSTRAINT PK_MODELO PRIMARY KEY (ID),
	CONSTRAINT FK1_MODELO FOREIGN KEY (IDMOT) REFERENCES MOTOR (ID),
	CONSTRAINT CK1_MODELO CHECK ( Precio_base > 0 )
);

-- EQUIPAMIENTO (ID, Descripcion, Precio)
CREATE TABLE EQUIPAMIENTO (
	ID BIGINT,
	Descripcion VARCHAR2(30),
	Precio FLOAT,
	Foto VARCHAR2(30),
	CONSTRAINT PK_EQUIPAMIENTO PRIMARY KEY (ID),
	CONSTRAINT CK1_EQUIPAMIENTO CHECK ( Precio > 0 )
);

-- INCLUYE (IDMOD, IDE, Descuento)
CREATE TABLE INCLUYE (
	IDMOD BIGINT,
	IDE BIGINT,
	Descuento FLOAT,
	CONSTRAINT PK_INCLUYE PRIMARY KEY (IDMOD, IDE),
	CONSTRAINT FK1_INCLUYE FOREIGN KEY (IDMOD) REFERENCES MODELO (ID),
	CONSTRAINT FK2_INCLUYE FOREIGN KEY (IDE) REFERENCES EQUIPAMIENTO (ID)
);

COMMIT;