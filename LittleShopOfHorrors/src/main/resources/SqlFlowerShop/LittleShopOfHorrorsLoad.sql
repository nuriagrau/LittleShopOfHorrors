-- insert flowershop
INSERT INTO `FlowerShop` (name) VALUES
("The Little Shop of Horrors");

-- insert product
INSERT INTO `Product` (flowerShopId, name, price, stock, productType) VALUES
(1,"LemonTree", 49, 2, "TREE"),
(1, "Magnolia", 105, 3, "TREE"),
(1, "Audrey II", 1500.00, 1, "FLOWER"),
(1, "Rose", 5.00, 100, "FLOWER"),
(1, "Daffodil", 3.00, 50, "FLOWER"),
(1, "Wooden Pot", 25.00, 30, "DECORATION"),
(1, "Plastic Pot", 15.00, 50, "DECORATION");

-- insert tree
INSERT INTO `Tree` (productId, heightCm) VALUES
((SELECT productId FROM Product WHERE name = "LemonTree") , 69);

INSERT INTO `Tree` (productId, heightCm) VALUES
((SELECT productId FROM Product WHERE name = "Magnolia"), 99);

-- insert flower
INSERT INTO `Flower` (productId, colour) VALUES
((SELECT productId FROM Product WHERE name = "Audrey II"), "Greenish") ;

INSERT INTO `Flower` (productId, colour) VALUES
((SELECT productId FROM Product WHERE name = "Rose") , "Red");

INSERT INTO `Flower` (productId, colour) VALUES
((SELECT productId FROM Product WHERE name = "Daffodil"), "Yellow" );

-- insert decoration
INSERT INTO `Decoration` (productId, material) VALUES
((SELECT productId FROM Product WHERE name = "Wooden Pot"), "WOOD");

INSERT INTO `Decoration` (productId, material) VALUES
((SELECT productId FROM Product WHERE name = "Plastic Pot"), "PLASTIC");

-- insert ticket
INSERT INTO `Ticket` (ticketValue) VALUES
(0.0);

-- insert ticketline
INSERT INTO `TicketLine` (ticketId, productId, quantity) VALUES
(1000, (SELECT productId FROM Product WHERE name = "Audrey II"), 1);

-- insert ticket
INSERT INTO `Ticket` (ticketValue) VALUES
(0.0);

INSERT INTO `TicketLine` (ticketId, productId, quantity) VALUES
(1001, (SELECT productId FROM Product WHERE name = "Rose"), 5);

INSERT INTO `TicketLine` (ticketId, productId, quantity) VALUES
(1001, (SELECT productId FROM Product WHERE name = "Daffodil"), 10);

-- update lineValue with price * quantity
UPDATE `TicketLine` SET lineValue = quantity * (SELECT price FROM Product WHERE Product.productId = TicketLine.productId);

-- update total value with sum of ticket line value
UPDATE `Ticket` SET ticketValue = (SELECT SUM(lineValue) FROM TicketLine WHERE Ticket.ticketId = TicketLine.ticketId GROUP BY ticketId);

UPDATE flowershop
SET stockValue = (SELECT SUM(price * stock) FROM Product WHERE Product.flowerShopId = 1);