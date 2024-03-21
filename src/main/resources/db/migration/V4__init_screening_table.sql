drop table if exists screening;
create table screening (
    id int primary key auto_increment,
    date date not null,
    time time not null,
    movie_id int not null,
    room_id int not null
);

alter table screening add foreign key (movie_id) references movie (id);
alter table screening add foreign key (room_id) references room (id);