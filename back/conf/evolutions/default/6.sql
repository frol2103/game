-- User

-- !Ups
CREATE TABLE dil_round (
                           id bigint(20) NOT NULL AUTO_INCREMENT,
                           uuid varchar(255) NOT NULL,
                           fk_game_id bigint(20) NOT NULL,
                           fk_user_id bigint(20) NOT NULL,
                           question varchar(255) NOT NULL,
                           timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           foreign key (`fk_user_id`) references `user` (`id`),
                           foreign key (`fk_game_id`) references `game` (`id`),
                           PRIMARY KEY (id)
);

CREATE TABLE dil_response (
                           id bigint(20) NOT NULL AUTO_INCREMENT,
                           uuid varchar(255) NOT NULL,
                           fk_game_id bigint(20) NOT NULL,
                           fk_user_id bigint(20) NOT NULL,
                           fk_round_id bigint(20) NOT NULL,
                           response varchar(255) NOT NULL,
                           timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           foreign key (`fk_user_id`) references `user` (`id`),
                           foreign key (`fk_game_id`) references `game` (`id`),
                           foreign key (`fk_round_id`) references `dil_round` (`id`),
                           PRIMARY KEY (id)
);

create unique index dil_one_response_by_user on dil_response(fk_user_id, fk_round_id);

CREATE TABLE dil_choice (
                              id bigint(20) NOT NULL AUTO_INCREMENT,
                              uuid varchar(255) NOT NULL,
                              fk_game_id bigint(20) NOT NULL,
                              fk_user_id bigint(20) NOT NULL,
                              fk_round_id bigint(20) NOT NULL,
                              fk_response_id bigint(20) NOT NULL,
                              timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              foreign key (`fk_round_id`) references `dil_round` (`id`),
                              foreign key (`fk_user_id`) references `user` (`id`),
                              foreign key (`fk_game_id`) references `game` (`id`),
                              foreign key (`fk_response_id`) references `dil_response` (`id`),
                              PRIMARY KEY (id)
);
create unique index dil_one_choice_by_user on dil_choice(fk_user_id, fk_round_id);
