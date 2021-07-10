create type type_of_body AS ENUM ('седан', 'хэтчбэк', 'универсал');

create table car_body(
	id serial primary key,
	body type_of_body
);

create table engine(
	id serial primary key,
	capacity float
);

create table transmission(
	id serial primary key,
	automatic_transmission boolean
);

create table car(
	id serial primary key,
	name varchar(255),
	car_body_id int references car_body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into car_body(body) values('седан');
insert into car_body(body) values('хэтчбэк');
insert into car_body(body) values('универсал');

insert into engine(capacity) values(1.6);
insert into engine(capacity) values(2.0);
insert into engine(capacity) values(3.0);

insert into transmission(automatic_transmission) values('true');
insert into transmission(automatic_transmission) values('false');

insert into car(name, car_body_id, engine_id, transmission_id) values('Toyota_Camry', 1, 3, 1);
insert into car(name, car_body_id, engine_id, transmission_id) values('Suzuki SX4', 2, 2, 1);
insert into car(name, car_body_id, engine_id, transmission_id) values('Honda Accord', 1, 2, 2);

/* Вывести список всех машин и все привязанные к ним детали. */
select c.name as Модель, cb.body as Тип_кузова, e.capacity as Объем_двигателя_в_литрах,
t.automatic_transmission as Наличие_АКПП
from car c join car_body cb on c.car_body_id = cb.id
join engine e on c.engine_id = e.id
join transmission t on c.transmission_id = t.id;

/* Вывести отдельно детали (1 деталь - 1 запрос), которые не используются
НИ в одной машине, кузова, двигатели, коробки передач. */
select cb.body as Тип_кузова from car_body cb left join car c on c.car_body_id = cb.id where c.id is null;
select e.capacity as Объем_двигателя from engine e left join car c on c.engine_id = e.id where c.id is null;
select t.automatic_transmission as Наличие_АКПП from transmission t left join car c on c.transmission_id = t.id where c.id is null;

