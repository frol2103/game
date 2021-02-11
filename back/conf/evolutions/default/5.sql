-- User

-- !Ups

drop index user_reference on user_reference;
create unique index user_reference on user_reference(reference,service);
