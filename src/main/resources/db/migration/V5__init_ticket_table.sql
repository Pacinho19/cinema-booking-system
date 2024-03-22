drop table if exists ticket;
create table ticket (
    id int primary key auto_increment,
    uuid varchar(36) not null,
    state varchar(20) not null,
    date datetime not null,
    price NUMERIC(5,2) not null
);