drop table if exists cinema_user;
create table cinema_user (
    id int primary key auto_increment,
    name varchar(50) not null,
    login varchar(50) not null,
    role varchar(50) not null,
    password varchar(255) not null,
    created_on timestamp,
    updated_on timestamp
);

alter table ticket add foreign key (user_id) references cinema_user (id);