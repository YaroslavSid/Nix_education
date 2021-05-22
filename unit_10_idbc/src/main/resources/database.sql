USE unit_10_idbc;

CREATE TABLE location
(
    id   INT(6) UNSIGNED UNIQUE PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO location
VALUES (1, 'gdansk'),
       (2, 'bydgoszcz'),
       (3, 'torun'),
       (4, 'warszawa');

CREATE TABLE route
(
    id      INT(6) UNSIGNED UNIQUE PRIMARY KEY,
    from_id INT NOT NULL REFERENCES location,
    to_id   INT NOT NULL REFERENCES location,
    cost    INT NOT NULL
);

INSERT INTO route
VALUES (1, 1, 2, 1),
       (2, 1, 3, 3),
       (3, 2, 1, 1),
       (4, 2, 3, 1),
       (5, 2, 4, 4),
       (6, 3, 1, 3),
       (7, 3, 2, 1),
       (8, 3, 4, 1),
       (9, 4, 2, 4),
       (10, 4, 3, 1);

CREATE TABLE problem
(
    id      INT(6) UNSIGNED UNIQUE PRIMARY KEY,
    from_id INT NOT NULL REFERENCES location,
    to_id   INT NOT NULL REFERENCES location
);

INSERT INTO problem
VALUES (1, 1, 4),
       (2, 2, 4);

CREATE TABLE solution
(
    problem_id INT PRIMARY KEY REFERENCES problem,
    cost       INT
);
