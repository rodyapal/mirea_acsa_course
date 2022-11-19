create table if not exists messages
(
    id       serial primary key,
    username varchar not null,
    content  varchar not null
);