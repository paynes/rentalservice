DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS "rental" CASCADE;

CREATE TABLE car (
    id_car          serial CONSTRAINT pk_car PRIMARY KEY,
    model       varchar(50) NOT NULL,
    daily_fee   double precision NOT NULL,
    reg_number  varchar(7) NOT NULL
);

CREATE TABLE customer (
    id_customer     serial CONSTRAINT pk_customer PRIMARY KEY,
    name            varchar(20) NOT NULL,
    surname         varchar(20) NOT NULL,
    born            date NOT NULL
);

CREATE TABLE "rental" (
    id_rental       bigserial CONSTRAINT pk_rental PRIMARY KEY,
    date_from       date NOT NULL,
    date_to         date NOT NULL,
    expected_days   smallint,
    payement        boolean,
    car_id          bigint NOT NULL,
    customer_id     bigint NOT NULL,
    CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES car(id_car) ON DELETE CASCADE,
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer(id_customer) ON DELETE CASCADE
);