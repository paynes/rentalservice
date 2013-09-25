INSERT INTO car(id_car,model,daily_fee,reg_number) VALUES (1,'renault',1.5,'123456');
INSERT INTO car(id_car,model,daily_fee,reg_number) VALUES (2,'ford',1.5,'234567');

INSERT INTO customer(id_customer,name,surname,born) VALUES (1,'Peter', 'Marcin','2008-08-08');
INSERT INTO customer(id_customer,name,surname,born) VALUES (2,'Honza', 'Novak','2008-08-08');

INSERT INTO rental(id_rental,date_from,date_to,expected_days,payement,car_id,customer_id) VALUES (1,'1991-09-23','1991-09-25',2,'true',1,1);
INSERT INTO rental(id_rental,date_from,date_to,expected_days,payement,car_id,customer_id) VALUES (2,'2007-08-08','2008-08-12',2,'true',2,2);
