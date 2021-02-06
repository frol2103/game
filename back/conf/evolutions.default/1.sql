-- User

-- !Ups

CREATE TABLE user (
                      id bigint(20) NOT NULL AUTO_INCREMENT,
                      uuid varchar(36)NOT NULL,
                      firstname varchar(255) NOT NULL,
                      lastname varchar(255) NOT NULL,
                      reference varchar(255) NOT NULL,
                      `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      PRIMARY KEY (id)
);

