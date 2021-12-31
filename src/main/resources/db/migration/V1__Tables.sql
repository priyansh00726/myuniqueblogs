CREATE TABLE person(
    id VARCHAR(256) NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL,
    username VARCHAR(30) NOT NULL,
    password BINARY(16) NOT NULL,
    sex ENUM('M', 'F') NOT NULL,
    description VARCHAR(100)
);

CREATE TABLE blog(
    id VARCHAR(256) NOT NULL PRIMARY KEY,
    author_id VARCHAR(256),
    description VARCHAR(200),
    tags VARCHAR(100),
    type VARCHAR(15) NOT NULL,
    _time TIMESTAMP NOT NULL,
    content VARCHAR(1000),
    likes INT,
    FOREIGN KEY (author_id) REFERENCES person(id) ON DELETE SET NULL
);