drop table if exists movie;
create table movie(
    id int primary key auto_increment,
    name varchar(50) not null,
    description varchar(500) not null,
    release_date date not null,
    duration int not null,
    img_url varchar(255)
);
