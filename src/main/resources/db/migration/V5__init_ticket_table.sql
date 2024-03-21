drop table if exists ticket;
create table ticket (
    id int primary key auto_increment,
    uuid varchar(36) not null,
    screening_id int not null,
    state varchar(15) not null
);

alter table ticket add foreign key (screening_id) references screening (id);