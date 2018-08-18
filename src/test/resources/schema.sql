CREATE TABLE IF NOT EXISTS STATUS (
    id INT NOT NULL,
    label VARCHAR(50) NOT NULL,
    create_date DATE,
    update_date DATE,
    UNIQUE(label),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PERSON (
    id LONG NOT NULL,
    email VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    create_date DATE,
    update_date DATE,
    UNIQUE(email),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS FRIENDSHIP (
    friend_one LONG NOT NULL,
    friend_two LONG NOT NULL,
    status INT NOT NULL,
    create_date DATE,
    update_date DATE,
    FOREIGN KEY (friend_one, friend_two) REFERENCES PERSON(id, id),
    FOREIGN KEY (status) REFERENCES STATUS(id)
);

CREATE TABLE IF NOT EXISTS SUBSCRIPTION (
    publisher LONG NOT NULL,
    subscriber LONG NOT NULL,
    status INT NOT NULL,
    create_date DATE,
    update_date DATE,
    FOREIGN KEY (publisher, subscriber) REFERENCES PERSON(id, id),
    FOREIGN KEY (status) REFERENCES STATUS(id)
);


DELETE FROM STATUS;
INSERT INTO STATUS VALUES(1, 'ACTIVE', NOW(), NOW());
INSERT INTO STATUS VALUES(2, 'INACTIVE', NOW(), NOW());
INSERT INTO STATUS VALUES(3, 'BLOCKED', NOW(), NOW());

DELETE FROM PERSON;
INSERT INTO PERSON VALUES(1, 'andy@example.com', 'Andy', 'A', NOW(), NOW());
INSERT INTO PERSON VALUES(2, 'john@example.com', 'John', 'J', NOW(), NOW());
INSERT INTO PERSON VALUES(3, 'user1@example.com', 'User', '1', NOW(), NOW());
INSERT INTO PERSON VALUES(4, 'lisa@example.com', 'Lisa', 'L', NOW(), NOW());
INSERT INTO PERSON VALUES(5, 'kate@example.com', 'Kate', 'K', NOW(), NOW());
INSERT INTO PERSON VALUES(6, 'user2@example.com', 'User', '2', NOW(), NOW());
INSERT INTO PERSON VALUES(7, 'user3@example.com', 'User', '3', NOW(), NOW());
