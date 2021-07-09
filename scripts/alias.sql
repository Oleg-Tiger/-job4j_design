create table persons(
	id serial primary key,
    name varchar(255)
);

create table languages(
	id serial primary key,
	name varchar(255)
);

create table languages_persons(
	id serial primary key,
	persons_id int references persons(id),
	languages_id int references languages(id),
	level varchar(255)
);

insert into persons(name) values('Джон');
insert into persons(name) values('Иван');
insert into persons(name) values('Андрей');

insert into languages(name) values('russian');
insert into languages(name) values('english');

insert into languages_persons(persons_id, languages_id, level) values(1, 2, 'Носитель языка');
insert into languages_persons(persons_id, languages_id, level) values(2, 1, 'Носитель языка');
insert into languages_persons(persons_id, languages_id, level) values(3, 1, 'Носитель языка');
insert into languages_persons(persons_id, languages_id, level) values(3, 2, 'Средний');

/* Получить таблицу людей и языков, которые они знают без уровня владения */
select p.name, l.name
from persons as p join languages_persons as lp on lp.persons_id = p.id
join languages as l on lp.languages_id = l.id;

/* Получить таблицу людей и языков, которые они знают с уровнем владения */
select p.name as Имя, l.name as Язык, lp.level as Уровень_владения
from persons as p join languages_persons as lp on lp.persons_id = p.id join
languages as l on lp.languages_id = l.id;

/* Получить таблицу людей и языков, носителем которого он являются */
select p.name as Имя, l.name as Язык, lp.level as Уровень_владения
from persons as p join languages_persons as lp on lp.persons_id = p.id
join languages as l on (lp.languages_id = l.id AND lp.level = 'Носитель языка');