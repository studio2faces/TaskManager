DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
    id          SERIAL PRIMARY KEY ,
    description VARCHAR            NOT NULL,
    is_done     BOOL DEFAULT FALSE NOT NULL
);