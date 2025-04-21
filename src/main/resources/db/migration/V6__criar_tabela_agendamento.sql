CREATE TABLE  t_agendamento (
    id_agendamento     INTEGER NOT NULL PRIMARY KEY,
    dt_agendamento     TIMESTAMP NOT NULL,
    status_agendamento VARCHAR2(20) NOT NULL,
    t_rota_id_rota     INTEGER NOT NULL,
    FOREIGN KEY (t_rota_id_rota) REFERENCES t_rota(id_rota)
);

CREATE SEQUENCE  SEQ_AUTOMATIC_T_AGENDAMENTO
START WITH 1
INCREMENT BY 1 NOCACHE
NOCYCLE;
