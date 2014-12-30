# Feeds schema

# --- !Ups

CREATE TABLE feeds (
    id     integer,
    name    varchar(100),
    url    varchar(500),
    PRIMARY KEY(id)
); 

# --- !Downs

DROP TABLE feeds;
