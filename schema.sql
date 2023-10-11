DROP DATABASE IF EXISTS chall_weWelcom;

CREATE DATABASE chall_weWelcom;

use chall_weWelcom;

DROP TABLE IF EXISTS restaurants;

CREATE TABLE IF NOT EXISTS restaurants (
	restaurant_id INT NOT NULL AUTO_INCREMENT,
    restaurant_name VARCHAR(50) NOT NULL,
    restaurant_address VARCHAR(255) NOT NULL,
    restaurant_phone_number VARCHAR (20) NOT NULL,
    PRIMARY KEY (restaurant_id)
);

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
	user_id INT NOT NULL AUTO_INCREMENT,
    user_first_name VARCHAR(255) NOT NULL,
    user_last_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS reviews;

CREATE TABLE IF NOT EXISTS reviews (
	review_id INT NOT NULL AUTO_INCREMENT,
    review TEXT NOT NULL,
    restaurant_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (restaurant_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);