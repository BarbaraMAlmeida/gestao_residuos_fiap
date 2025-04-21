CREATE TABLE IF NOT EXISTS t_emergencia (
    id_emergencia              INTEGER NOT NULL PRIMARY KEY,
    dt_emergencia              DATE NOT NULL,
    status                     VARCHAR2(20) NOT NULL,
    ds_emergencia              CLOB,
    t_recipiente_id_recipiente INTEGER NOT NULL,
    t_caminhao_id_caminhao     INTEGER NOT NULL,
    FOREIGN KEY (t_recipiente_id_recipiente) REFERENCES t_recipiente(id_recipiente),
        FOREIGN KEY (t_caminhao_id_caminhao) REFERENCES t_caminhao(id_caminhao)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_AUTOMATIC_T_EMERGENCIA
START WITH 1
INCREMENT BY 1 NOCACHE
NOCYCLE;
