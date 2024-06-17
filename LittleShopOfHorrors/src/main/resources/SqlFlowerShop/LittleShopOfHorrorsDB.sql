-- drop if exists
DROP DATABASE IF EXISTS `littleshopofhorrorsdb`;

-- create
CREATE DATABASE IF NOT EXISTS `littleshopofhorrorsdb`;
USE `littleshopofhorrorsdb`;

-- table flowershop
CREATE TABLE IF NOT EXISTS `FlowerShop` (
    `flowerShopId` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `stockValue` DOUBLE DEFAULT 0.0
);

-- table product
CREATE TABLE IF NOT EXISTS `Product` (
    `productId` INT AUTO_INCREMENT PRIMARY KEY,
    `flowerShopId` INT ,
    `name` VARCHAR(255) NOT NULL,
    `price` DOUBLE NOT NULL,
    `stock` INT NOT NULL,
    `productType` ENUM("TREE", "FLOWER", "DECORATION") NOT NULL,
    FOREIGN KEY (`flowerShopId`) REFERENCES FlowerShop(`flowerShopId`)
);

-- table tree
CREATE TABLE IF NOT EXISTS `Tree` (
    `productId` INT PRIMARY KEY,
    `heightCm` INT NOT NULL,
    FOREIGN KEY (`productId`) REFERENCES Product(`productId`)
    ON DELETE CASCADE
);

-- table flower
CREATE TABLE IF NOT EXISTS `Flower` (
    `productId` INT PRIMARY KEY,
    `colour` VARCHAR(255) NOT NULL,
    FOREIGN KEY (`productId`) REFERENCES Product(`productId`)
    ON DELETE CASCADE
);

-- table decoration
CREATE TABLE IF NOT EXISTS `Decoration` (
    `productId` INT PRIMARY KEY,
    `material` ENUM ("WOOD", "PLASTIC") NOT NULL,
    FOREIGN KEY (`productId`) REFERENCES Product(`productId`)
    ON DELETE CASCADE
);

-- table ticket
CREATE TABLE IF NOT EXISTS `Ticket` (
    `ticketId` INT AUTO_INCREMENT PRIMARY KEY,
    `flowerShopId` INT,
    `timeStamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `ticketValue` DOUBLE NOT NULL DEFAULT 0.0,
    FOREIGN KEY (`flowerShopId`) REFERENCES FlowerShop(`flowerShopId`)
);
ALTER TABLE ticket auto_increment=1000;

-- table ticketline
CREATE TABLE IF NOT EXISTS `TicketLine` (
    `ticketLineId` INT AUTO_INCREMENT PRIMARY KEY,
    `ticketId` INT NOT NULL,
    `productId` INT NOT NULL,
    `quantity` INT NOT NULL,
    `lineValue` DOUBLE NOT NULL DEFAULT 0.0,
    FOREIGN KEY (`ticketId`) REFERENCES Ticket(`ticketId`) ON
    DELETE CASCADE,
    FOREIGN KEY (`productId`) REFERENCES Product(`productId`)
);
