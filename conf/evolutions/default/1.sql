# Feeds schema

# --- !Ups

CREATE TABLE feeds (
    name    varchar(100),
    url    varchar(500)
); 

# --- !Downs

DROP TABLE feeds;
