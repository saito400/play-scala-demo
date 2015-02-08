# Pages schema

# --- !Ups

CREATE TABLE page (
    id     serial,
    url    varchar(500),
    feed_id serial,
    read   boolean,
    save   boolean
);

# --- !Downs

DROP TABLE page;
