CREATE TABLE membership (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO membership (id, name, description) VALUES 
(1, 'Silver', 'Basic membership level'),
(2, 'Gold', 'Intermediate membership level'),
(3, 'Platinum', 'Advanced membership level'), 
(4, 'Diamond', 'Premium membership level');
