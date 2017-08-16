SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS happyhour(
    id int PRIMARY KEY auto_increment,
    startTime VARCHAR,
    endTime VARCHAR,
    rating INTEGER,
    restaurantName VARCHAR,
    address VARCHAR
);