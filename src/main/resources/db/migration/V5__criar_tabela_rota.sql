CREATE TABLE t_rota (
    id_rota                    INTEGER NOT NULL PRIMARY KEY,
    dt_rota                    TIMESTAMP NOT NULL,
    t_recipiente_id_recipiente INTEGER NOT NULL,
    t_caminhao_id_caminhao     INTEGER NOT NULL,
    FOREIGN KEY (t_recipiente_id_recipiente) REFERENCES t_recipiente(id_recipiente),
    FOREIGN KEY (t_caminhao_id_caminhao) REFERENCES t_caminhao(id_caminhao)
);

CREATE SEQUENCE SEQ_AUTOMATIC_T_ROTA
START WITH 1
INCREMENT BY 1 NOCACHE
NOCYCLE;
