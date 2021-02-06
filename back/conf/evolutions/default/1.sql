-- User

-- !Ups

CREATE TABLE user (
                      id bigint(20) NOT NULL AUTO_INCREMENT,
                      name varchar(255) NOT NULL,
                      uuid varchar(36) NOT NULL,
                      `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      PRIMARY KEY (id)
);

create table user_reference(
                       id bigint(20) NOT NULL AUTO_INCREMENT,
                       fk_user_id bigint(20) NOT NULL, foreign key (`fk_user_id`) references `user` (`id`),
                       reference varchar(36) NOT NULL,
                       `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id)

);

create unique index user_uuid on user(uuid);
create unique index user_reference on user_reference(reference);

CREATE TABLE game (
                      id bigint(20) NOT NULL AUTO_INCREMENT,
                      uuid varchar (36) NOT NULL,
                      game_type varchar(255) NOT NULL,
                      `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      state varchar(255) NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE user_in_game (
                      id bigint(20) NOT NULL AUTO_INCREMENT,
                      fk_game_id bigint(20) NOT NULL,
                      fk_user_id bigint(20) NOT NULL,
                      role varchar(255) NULL,
                      foreign key (`fk_user_id`) references `user` (`id`),
                      foreign key (`fk_game_id`) references `game` (`id`),
                      PRIMARY KEY (id)
);

CREATE TABLE lit_round (
                      id bigint(20) NOT NULL AUTO_INCREMENT,
                      fk_game_id bigint(20) NOT NULL,
                      fk_original_user_id bigint(20) NOT NULL,
                      fk_user_id bigint(20) NOT NULL,
                      text varchar(255),
                      fk_file_id varchar(36),
                      foreign key (`fk_user_id`) references `user` (`id`),
                      foreign key (`fk_original_user_id`) references `user` (`id`),
                      foreign key (`fk_game_id`) references `game` (`id`),
                      PRIMARY KEY (id)
);

CREATE TABLE file (
    id varchar(36) NOT NULL,
    value longblob NOT NULL,
    name varchar (255) not null,
    `mime` varchar(255) not null,
    `fk_user_id` bigint(20) NOT NULL,
    `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`id`),
    PRIMARY KEY (id)
);
