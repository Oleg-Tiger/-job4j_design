/*отношение many-to-many: один человек изучает несколько языков, несколько человек могут изучать один язык*/
/*создаём таблицу persons*/
create table persons(
	id serial primary key,
    name varchar(255)
);

/*создаём таблицу languages*/
create table languages(
	id serial primary key,
	name varchar(255)
);

/*создаём третью таблицу для связи первых двух между собой*/
create table languages_persons(
	id serial primary key,
	persons_id int references persons(id),
	languages_id int references languages(id)
);