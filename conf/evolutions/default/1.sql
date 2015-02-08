# Feeds schema

# --- !Ups

CREATE TABLE feed (
    id     serial,
    name    varchar(100),
    url    varchar(500)
); 

# --- !Downs

DROP TABLE feed;
