create table if not exists dyshamusic (
                        id serial primary key,
                        title varchar(255) not null,
                        description text not null,
                        music_file_name varchar(255) not null,
                        thumbnail_file_name varchar(255),
                        posted_on timestamp default now(),
                        user_id integer references users (id)

);

create table if not exists comment (
                           id serial primary key,
                           content text not null ,
                           music_id integer references dyshamusic (id),
                           user_id integer references users (id),
                           created_at timestamp default now()
);

create table if not exists likes (
                        id serial primary key ,
                        music_id integer references dyshamusic (id),
                        user_id integer references  users (id),
                        type varchar(10) check(type in ('like', 'dislike')),
                        created_at timestamp default now()
);

create table if not exists playlist (
                            id serial primary key,
                            name varchar(255) not null ,
                            user_id integer references users (id),
                            created_at timestamp default now()
);

create table if not exists playlistmusic (
                                 playlist_id integer references playlist (id),
                                 music_id integer references dyshamusic (id),
                                 primary key (playlist_id, music_id)
);

create table if not exists subscription (
                                subscriber_id integer references users (id),
                                channel_id integer references users (id),
                                primary key (subscriber_id, channel_id)
);

create table if not exists ecoute (
                        id serial primary key ,
                        music_id integer references dyshamusic (id),
                        user_id integer references users (id),
                        viewed_at timestamp default now()
);




create table if not exists dysha_files (
                       id serial primary key,
                       name varchar(255) not null,
                       data bytea not null
);