CREATE TABLE t_recipiente (
    id_recipiente   INTEGER NOT NULL,
    max_capacidade  INTEGER NOT NULL,
    atual_nvl       INTEGER NOT NULL,
    status          VARCHAR2(20) NOT NULL,
    ultima_att      TIMESTAMP NOT NULL,
    latitude_recip  FLOAT NOT NULL,
    longitude_recip FLOAT NOT NULL
);

CREATE SEQUENCE SEQ_AUTOMATIC_T_RECIPIENTE
START WITH 1
INCREMENT BY 1 NOCACHE
NOCYCLE;
