create sequence if not exists battery_id_seq;

alter sequence if exists battery_id_seq owner to ${user};

create table if not exists battery
(
    id       bigint      not null
        primary key,
    capacity integer     not null,
    name     varchar(85) not null
        constraint battery_name_uk
            unique,
    postcode varchar(10) not null
);

alter table if exists battery
    owner to ${user};
