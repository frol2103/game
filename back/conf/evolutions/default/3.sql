-- User

-- !Ups

alter table lit_round add column `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP;
alter table lit_round add column `story_id` varchar(255) NOT NULL default "old";
