-- Table: weather.log

-- DROP TABLE weather.log;

CREATE SCHEMA weather;

ALTER SCHEMA weather
    OWNER to postgres;

CREATE TABLE weather.log
(
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    temp double precision NOT NULL,
    logdate date NOT NULL,
    id integer NOT NULL
);

ALTER TABLE weather.log
    OWNER to postgres;
