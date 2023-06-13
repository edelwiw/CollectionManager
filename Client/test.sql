CREATE TABLE orders (
       id SERIAL PRIMARY KEY,
       description VARCHAR(512),
       customer_id INTEGER REFERENCES customer(id) NOT NULL,
       order_status_id INTEGER REFERENCES order_status(id) NOT NULL
);