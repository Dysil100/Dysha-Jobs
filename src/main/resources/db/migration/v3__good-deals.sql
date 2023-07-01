create table if not exists product
(
    id serial primary key,
    user_id integer,
    user_email varchar(255),
    title varchar(255),
    description text,
    price double precision,
    vente boolean,
    active boolean,
    cathegorie varchar(255),
    region varchar(255) not null,
    ville varchar(255),
    quartier varchar(255),
    images text[],
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table if not exists chatmessage (
                                           id serial primary key,
                                           sender varchar(255),
                                           receiver varchar(255),
                                           sujet varchar(255),
                                           message text,
                                           discussion_hash text,
                                           created_at timestamp default current_timestamp
);

create  table if not exists discussion (
                                           id serial primary key,
                                           discussion_hash text,
                                           created_at timestamp default now()
);