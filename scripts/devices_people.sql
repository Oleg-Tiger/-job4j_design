create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('IPhone', 50000), ('Phone_Samsung', 40000), ('Phone_Honor', 20000);
insert into devices(name, price) values ('TV_Samsung', 55000), ('TV_Rubin', 18000);
insert into devices(name, price) values ('laptop', 80000), ('PC', 100000), ('tablet', 25000);
insert into people(name) values ('Женя'), ('Витя'), ('Наташа');
insert into devices_people(people_id, device_id) values (1, 3), (1, 5), (1, 8);
insert into devices_people(people_id, device_id) values (2, 2), (2, 4), (2, 7);
insert into devices_people(people_id, device_id) values (3,1), (3, 4), (3, 6), (3, 8);

/* получаем среднюю цену всех устройств */
select avg(price) from devices;

/* средняя цена устройств у каждого человека */
select p.name, avg(d.price) from devices_people as dp join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name;

/* средняя цена устройств у каждого человека, при этом она больше 5000 */
select p.name, avg(d.price) from devices_people as dp join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;

/* средняя цена устройств у каждого человека, при этом она больше 25000 */
select p.name, avg(d.price) from devices_people as dp join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name
having avg(d.price) > 25000;


