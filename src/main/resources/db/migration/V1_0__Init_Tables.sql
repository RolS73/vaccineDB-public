

drop table if exists patient CASCADE;

drop table if exists vaccination_point CASCADE;

drop table if exists vaccination_point_vaccine_stock CASCADE;

drop table if exists vaccine CASCADE;

create table patient (
                         id bigint generated by default as identity,
                         age integer not null check (age>=0),
                         full_name varchar(255) not null,
                         gender varchar(255),
                         is_vaccinated boolean not null,
                         vaccination_date varchar(255),
                         vaccine_id bigint,
                         primary key (id)
);

create table vaccination_point (
                                   id bigint generated by default as identity,
                                   address varchar(255),
                                   city varchar(255),
                                   name varchar(255),
                                   region varchar(255),
                                   primary key (id)
);

create table vaccination_point_vaccine_stock (
                                                 vaccination_point_id bigint not null,
                                                 vaccine_stock_id bigint not null
);

create table vaccine (
                         id bigint generated by default as identity,
                         doses_needed integer not null check (doses_needed>=1),
                         min_age integer not null check (min_age>=0),
                         name varchar(255),
                         patient_id bigint,
                         vaccination_point_id bigint,
                         primary key (id)
);

alter table vaccination_point_vaccine_stock
    add constraint UK_k34snc5mx75gvdhqmqtlhpfsx unique (vaccine_stock_id);

alter table patient
    add constraint FKoijm5ch74h9lcur4afcusp9w7
        foreign key (vaccine_id)
            references vaccine;

alter table vaccination_point_vaccine_stock
    add constraint FKc8jl5mg13nyavaw3rvtn57om5
        foreign key (vaccine_stock_id)
            references vaccine;

alter table vaccination_point_vaccine_stock
    add constraint FK6udyt071vxxtme3fykdr3of1r
        foreign key (vaccination_point_id)
            references vaccination_point;

alter table vaccine
    add constraint FK3ohj1jsg3t2jsa75cckl2gydw
        foreign key (patient_id)
            references patient;

alter table vaccine
    add constraint FKq3ndlxqk6ud4j9s5j09n6ht0k
        foreign key (vaccination_point_id)
            references vaccination_point