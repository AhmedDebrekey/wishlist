CREATE DATABASE IF NOT EXISTS wishlist_db;
USE wishlist_db;

CREATE TABLE IF NOT EXISTS wishlist (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    owner_name  VARCHAR(255) NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS wish (
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(500),
    price       DOUBLE,
    link        VARCHAR(1000),
    reserved    BOOLEAN        NOT NULL DEFAULT FALSE,
    wishlist_id BIGINT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_wish_wishlist
        FOREIGN KEY (wishlist_id)
        REFERENCES wishlist (id)
        ON DELETE CASCADE
);
