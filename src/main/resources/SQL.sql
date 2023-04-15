DROP DATABASE IF EXISTS wishlistDatabase;
CREATE DATABASE IF NOT EXISTS wishlistDatabase;
USE wishlistDatabase;

CREATE TABLE user (
    userID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (userID)
);

CREATE TABLE wishlist (
    wishlistID INT NOT NULL AUTO_INCREMENT,
    wishlistName VARCHAR(255) NOT NULL,
    userID INT NOT NULL,
    PRIMARY KEY (wishlistID),
    FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE item (
    itemID INT NOT NULL AUTO_INCREMENT,
    itemName VARCHAR(255) NOT NULL,
    wishlistID INT NOT NULL,
    PRIMARY KEY (itemID),
    FOREIGN KEY (wishlistID) REFERENCES wishlist(wishlistID)
);

-- Tilføj testdata
INSERT INTO user (username, password, email) VALUES
('Batman', 'Batmanrocks', 'Bat@Man.com')


