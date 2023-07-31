--liquibase formatted sql
--changeset semenovio:SN1-init-schema

CREATE SCHEMA snprofile;
CREATE TABLE snprofile.profile
(
    `id`        INT         NOT NULL,
    `userId`    INT         NOT NULL,
    `firstName` VARCHAR(45) NOT NULL,
    `lastName`  VARCHAR(45) NOT NULL,
    `birthdate` DATE NULL,
    PRIMARY KEY (`id`)
);
