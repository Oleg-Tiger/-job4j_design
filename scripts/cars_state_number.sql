/*Отношение one-to-one: у авто уникальный гос. регистрационный номер, и такой номер 
только у этого авто. Нам нужна возможность получать авто по номеру и наоборот*/

/*создаём таблицу cars*/
create table cars(
	id serial primary key,
	model varchar(255),
	color varchar(255),
	yearOfManufacture smallint
);

/*создаём таблицу state_number*/
create table state_number(
	id serial primary key,
	number varchar(255),
	region smallint
);

/*создаём третью таблицу для связи первых двух между собой*/
create table cars_state_number(
	id serial primary key,
	cars_id int references cars(id) unique,
	state_number_id int references state_number(id) unique
);