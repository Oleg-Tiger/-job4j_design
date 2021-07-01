/* создаём таблицу */
create table cars(
id serial primary key,
	model varchar(255),
	yearOfManufacture smallint,
	mileage float
);

/* добавляем авто */
insert into cars(model, yearOfManufacture, mileage) values('Toyota Camry', 2020, 20.250);

/* обновляем данные */
update cars set mileage = 20250.500;

/* удаляем */
delete from cars;



