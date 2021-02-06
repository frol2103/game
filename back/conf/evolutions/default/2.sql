-- User

-- !Ups

create unique index user_in_game_unicity on user_in_game(fk_user_id, fk_game_id);

