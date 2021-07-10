create table departmens(
	id serial primary key,
	name varchar(255)
);
create table employees(
	id serial primary key,
	name varchar(255),
	departmens_id int references departmens(id)
);

insert into departmens(name) values('отдел кадров');
insert into departmens(name) values('отдел продаж');
insert into departmens(name) values('отдел по работе с клиентами');
insert into departmens(name) values('бухгалтерия');

insert into employees(name, departmens_id) values('Саша', 1);
insert into employees(name, departmens_id) values('Антон', 2);
insert into employees(name, departmens_id) values('Лена', 2);
insert into employees(name, departmens_id) values('Игорь', 3);

/* left и right соединения */
/* В таком варианте у нас количество строк в результирующей таблице совпадает с количеством работников,
 потому что присоединяем one к many. ДАННЫЕ ЗАПРОСЫ ДАДУТ ОДИНАКОВЫЙ РЕЗУЛЬТАТ за исключением порядка столбцов*/
select * from employees e left join departmens d on e.departmens_id = d.id;
select * from departmens d right join employees e on e.departmens_id = d.id;

/* в этом случае присоединям many к one, количество строк будет больше, чем количество департаментов,
 т к в некоторых департаментах более одного работника.
 ДАННЫЕ ЗАПРОСЫ ДАДУТ ОДИНАКОВЫЙ РЕЗУЛЬТАТ за исключением порядка столбцов*/
select * from departmens d left join employees e on e.departmens_id = d.id;
select * from employees e right join departmens d on e.departmens_id = d.id;

/* full соединения. Данные скрипты дадут один резальтат, только разный порядок столбцов, потому что
в обоих случая выполняются и left и right соединения*/
select * from employees e full join departmens d on e.departmens_id = d.id;
select * from departmens d full join employees e on e.departmens_id = d.id;

/* cross join. Две команды дадут одинаковый результат за исключением порядка столбцов */
select * from departmens d cross join  employees e;
select * from employees e cross join  departmens d;

/* Используя left join найти департаменты, у которых нет работников */
select * from departmens d left join employees e on d.id = e.departmens_id where e.id is null;


/*  Создать таблицу teens с атрибутами name, gender и заполнить ее.
 Используя cross join составить все возможные разнополые пары */

create type gender AS ENUM ( 'М', 'Ж' );

create table teens(
	id serial primary key,
	name varchar(255),
	gender gender
);

insert into teens(name, gender) values('Оля', 'Ж');
insert into teens(name, gender) values('Света', 'Ж');
insert into teens(name, gender) values('Ваня', 'М');
insert into teens(name, gender) values('Саша', 'М');

/* создали в конце дополнительное условие t1.gender = 'М', чтобы каждая пара была выведена 1 раз,
без "зеркального" отображения */
select t1.name as Имя1, t2.name as Имя2 from teens t1 cross join teens t2
where t1.gender = 'М' and t1.gender != t2.gender ;





