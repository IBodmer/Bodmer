CREATE TABLE person
(
    "id"              int8 PRIMARY KEY not null ,
    "user_name"       varchar(50)not null,
    "name"            varchar(50),
    "last_name"       varchar(50),
    "email"           varchar(50)not null,
    "password"        varchar(255)not null,
    "role"            varchar(30),
    "role_in_project" varchar(30),
    "registered_at"   timestamp
);