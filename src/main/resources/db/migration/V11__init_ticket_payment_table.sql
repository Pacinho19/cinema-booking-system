drop table if exists ticket_payment;
create table ticket_payment (
    id int primary key auto_increment,
    uuid varchar(36) not null,
    ticket_id int not null,
    completed_at datetime not null
);

alter table ticket_payment add foreign key (ticket_id) references ticket (id);