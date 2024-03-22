drop table if exists category;
create table category(
    id int primary key auto_increment,
    name varchar(50) not null
);

alter table movie add column category_id int null AFTER title;
alter table movie add foreign key (category_id) references category (id)


