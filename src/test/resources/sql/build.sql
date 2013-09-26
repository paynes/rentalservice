DROP TABLE car IF EXISTS
DROP TABLE customer IF EXISTS
DROP TABLE rental IF EXISTS

CREATE TABLE car (
    id_car      integer generated by default as identity primary key,
    model       varchar(50) NOT NULL,
    daily_fee   double precision NOT NULL,
    reg_number  varchar(7) NOT NULL
);

CREATE TABLE customer (
    id_customer     integer generated by default as identity primary key,
    name            varchar(20) NOT NULL,
    surname         varchar(20) NOT NULL,
    born            date NOT NULL
);

CREATE TABLE rental (
    id_rental       integer generated by default as identity primary key,
    date_from       date NOT NULL,
    date_to         date,
    expected_days   smallint,
    payement        boolean,
    car_id          bigint NOT NULL,
    customer_id     bigint NOT NULL,
    CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES car(id_car) ON DELETE CASCADE,
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer(id_customer) ON DELETE CASCADE
);