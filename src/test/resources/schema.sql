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