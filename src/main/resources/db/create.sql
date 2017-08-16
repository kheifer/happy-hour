SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS happyhour(
    id int PRIMARY KEY auto_increment,
    startTime VARCHAR,
    endTime VARCHAR,
    rating INTEGER,
    restaurantName VARCHAR,
    address VARCHAR
);
CREATE TABLE IF NOT EXISTS neighborhood(
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR
);