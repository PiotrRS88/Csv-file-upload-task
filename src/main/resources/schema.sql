CREATE TABLE IF NOT EXISTS book_details
    (
        id INTEGER GENERATED BY DEFAULT AS IDENTITY,
        name VARCHAR(255),
        description VARCHAR(255),
        updated_timestamp VARCHAR(255),
        PRIMARY KEY (id)
    );