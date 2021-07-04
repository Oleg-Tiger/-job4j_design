/*Отношение many-to-one: студент в университете учится в одной группе,
но в этой группе учится много студентов*/

/*создаём таблицу grouping*/
create table grouping(
	id serial primary key,
	faculty varchar(255),
	number smallint
);

/*создаём таблицу students и добавляем атрибут grouping_id*/
create table students(
	id serial primary key,
	name varchar(255),
	grouping_id int references grouping(id)
);