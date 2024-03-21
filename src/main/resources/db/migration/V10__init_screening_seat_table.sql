drop table if exists screening_seat;
create table screening_seat (
    id int primary key auto_increment,
    uuid varchar(36) not null,
    screening_id int not null,
    row_number int not null,
    seat_number int not null,
    state varchar(20) not null,
    created_at datetime not null,
    updated_at datetime
);

alter table screening_seat add foreign key (screening_id) references screening (id);