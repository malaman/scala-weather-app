# --- !Ups

create table "CITIES" (
   ID   INT PRIMARY KEY     NOT NULL,
   NAME VARCHAR(255)        NOT NULL,
   LON  REAL                NOT NULL,
   LAT  REAL                NOT NULL,
   CREATED DATETIME         NOT NULL,
   UPDATED DATETIME
);

# --- !Downs

drop table "CITIES" if exists;
