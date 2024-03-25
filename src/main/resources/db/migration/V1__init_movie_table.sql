drop table if exists movie;
create table movie(
    id int primary key auto_increment,
    title varchar(50) not null,
    alias varchar(255) not null,
    brief_description varchar(150) not null,
    description varchar(500) not null,
    release_date date not null,
    duration int not null,
    img_url varchar(255)
);
