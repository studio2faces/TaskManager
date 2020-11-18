DELETE FROM tasks;

INSERT INTO tasks(description, is_done)
VALUES ('To walk with a dog', false),
       ('Buy milk and bread', false),
       ('Plan a trip', true),
       ('Clean the room', false),
       ('Call Nick', true);