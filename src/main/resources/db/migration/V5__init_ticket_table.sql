drop table if exists ticket;
create table ticket (
    id int primary key auto_increment,
    uuid varchar(36) not null,
    screening_seat_id int not null,
    row_number int not null,
    seat_number int not null,
    date datetime not null default current_timestamp
);

alter table ticket add foreign key (screening_seat_id) references screening_seat (id);