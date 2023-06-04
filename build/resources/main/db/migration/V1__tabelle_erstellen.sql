create table if not exists users
(
    id         serial primary key,
    first_name varchar(60)         not null,
    last_name  varchar(60)         not null,
    email      varchar(100) unique not null,
    telephone      varchar(15) unique not null,
    password   varchar(200)        not null,
    locked     boolean,
    enabled    boolean,
    role       varchar(40)         not null
);

create table if not exists archiv
(
    id         serial primary key,
    first_name varchar(60)         not null,
    last_name  varchar(60)         not null,
    email      varchar(100) unique not null,
    telephone      varchar(15) unique not null,
    password   varchar(200)        not null
);

create table if not exists confirmation_token
(
    id           serial primary key,
    token        varchar(200) unique not null,
    created_at   varchar(20)         not null,
    expired_at   varchar(20)         not null,
    confirmed_at varchar(20),
    username     varchar(100) unique not null
);

create table if not exists dyshajob (
    id serial primary key,
    title varchar(255),
    description text,
    posted_date timestamp default current_timestamp,
    employeur varchar(255),
    location varchar(500),
    user_id integer

);

create table if not exists dyshaworker (
   id serial primary key,
   name varchar(255),
   description text,
   location varchar (500),
   started_on timestamp default current_timestamp,
   user_id integer
);

create table if not exists workerjobrelation (
     id serial primary key,
     job_id INTEGER ,
     worker_id INTEGER ,
     validation bool ,
     started_on timestamp default current_timestamp
);

create table if not exists dysha_file (
    id serial primary key,
    user_id integer,
    entity_id integer,
    table_name varchar (500),
    file_type varchar,
    file bytea,
    foreign key (user_id) references users(id)
);

