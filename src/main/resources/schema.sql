use composite_db;

create table if not exists blocks (
    id integer primary key auto_increment,
    color varchar(50) not null,
    material varchar(50) not null
);

create table if not exists composite_blocks (
    id integer not null,
    color varchar(50) not null,
    material varchar(50) not null,
    block_id integer not null,
    foreign key (block_id) references blocks(id) on delete cascade on update cascade
);