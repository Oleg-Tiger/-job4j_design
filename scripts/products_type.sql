create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	expired date,
	price float,
	type_id int references type(id)
);

insert into type(name) values ('Сыр'), ('Мороженое'), ('Молоко');
insert into product(name, expired, price, type_id) values ('Сыр плавленный', '2021-07-08', 60, 1 ), 
('Сыр фетакса','2022-07-08', 100, 1), ('Мороженое шоколадное','2020-07-08', 55, 2),
('Мороженое сливочное','2021-10-08', 50, 2), ('Молоко пастеризованное','2021-12-10', 45, 3),
('Молоко топлёное','2023-01-02', 70, 3);

/* Написать запрос получение всех продуктов с типом "Сыр" */
select p.name as Продукт, p.expired as Срок_годности, p.price as Цена
from product as p join type as t on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'Сыр';

/* Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое" */
select * from product where name like ('%мороженое%') or name like('%Мороженое%');

/* Написать запрос, который выводит все продукты, срок годности которых уже истек */
select name, expired from product where expired < current_date;

/* Написать запрос, который выводит самый дорогой продукт. */
select name, price from product where price = (select max(price) from product);

/* Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
В виде имя_типа, количество */
select t.name as Тип, count(*) as Количество from type as t join product as p on t.id = p.type_id
group by t.name;

/* Написать запрос на получение всех продуктов с типом "СЫР" и "МОЛОКО" */
select p.name as Продукт, p.expired as Срок_годности, p.price as Цена
from product as p join type as t on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'Сыр' or t.name = 'МОЛОКО' or t.name = 'Молоко' ;

/*  Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
 Под количеством подразумевается количество продуктов определенного типа. */
select t.name as Тип, count(*) as Количество from type as t join product as p on t.id = p.type_id
group by t.name
having count(*) < 10;

/* Вывести все продукты и их тип. */
select p.name as Продукт, p.expired as Срок_годности, p.price as Цена, t.name as Тип
from product as p join type as t on p.type_id = t.id;