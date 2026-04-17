USE wishlist_db;

-- Wishlists ...
INSERT INTO wishlist (owner_name, title, description) VALUES
    ('Jesus',  '25th of December 2026', 'Holy Mary'),
    ('Mary',  'Son`s Bday',  'He´s 2026 years old, and still didnt make money'),
    ('Joseph', 'Step son birthday',    'Most famous person apparently and I have to buy gifts');

-- Jesus's wishes
INSERT INTO wish (name, description, price, link, reserved, wishlist_id) VALUES
    ('New Sandals', 'Mine are completely worn out from all the walking on water', 199.00,null, FALSE, 1),
    ('Unlimited Wine', 'Tired of having to do it manually at every party', 0.00, null, FALSE, 1),
    ('Carpentry Tools', 'Dad never let me use his.', 599.00, 'https://www.silvan.dk', TRUE,  1),
    ('Therapy Sessions', '2026 years of being everyone''s saviour takes a toll', 800.00,   null, FALSE, 1);

-- MARIAAAAAAAA
INSERT INTO wish (name, description, price, link, reserved, wishlist_id) VALUES
    ('Paternity Test Kit','Just to have it in writing this time',299.00,null,FALSE, 2),
    ('Noise Cancelling Headphones', 'For when the angels won''t stop singing outside',1200.00,  'https://www.pricerunner.dk',   FALSE, 2),
    ('Donkey Upgrade','A car would be nice. Or at least a faster donkey.',null,null,TRUE,  2);

-- Josephain
INSERT INTO wish (name, description, price, link, reserved, wishlist_id) VALUES
    ('Stepfather''s Day Card','Nobody ever remembers. Not even once.',49.00,null,FALSE, 3),
    ('Autobiography Deal','My side of the story. Nobody ever asks.',null,     null,FALSE, 3),
    ('Manger Renovation','It was a one-time emergency, somehow we never moved out',15000.00, 'https://www.boligsiden.dk',FALSE, 3),
    ('DNA Test for Dummies','I already know the answer but I want everyone else to too',  199.00,null,TRUE,  3);
