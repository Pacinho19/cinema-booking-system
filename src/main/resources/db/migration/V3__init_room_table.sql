drop table if exists room;
create table room(
    id int primary key auto_increment,
    number int not null,
    rows_count int not null,
    seats_count int not null,
    constraint room_number_UNIQUE unique (number)
);



