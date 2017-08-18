# --- !Ups

create table "USERS" (
   ID INT PRIMARY KEY     NOT NULL,
   CREATED DATETIME NOT NULL,
   UPDATED DATETIME,
   LAST_LOGIN DATETIME
);

# --- !Downs

drop table "USERS" if exists;
