insert into tasks(description, created, done) VALUES ('Очень длинное описание Очень длинное описание Очень длинное описание Очень длинное описание Очень длинное описание Очень длинное описание',
                                                     '2022-09-14 14:00', false);
insert into tasks(description, created, done) VALUES ('Другое описание немного поменьше',
                                                     '2022-07-14 15:30', true);

insert into todo_user(name, login, password) VALUES ('addy', 'add', 'password');
insert into todo_user(name, login, password) VALUES ('baddy', 'bad', 'password');

INSERT INTO priorities (name, position) VALUES ('urgently', 1);
INSERT INTO priorities (name, position) VALUES ('normal', 2);
INSERT INTO priorities (name, position) VALUES ('low', 3);

UPDATE tasks SET priority_id = (SELECT id FROM priorities WHERE name = 'urgently' LIMIT 1);

INSERT INTO categories (name)
VALUES ('Работа'), ('Семья'), ('Покупки'), ('Саморазвитие'), ('Позвонить'),
       ('Написать письмо')
;

INSERT INTO categories_and_tasks (task_id, category_id)
VALUES (54, 7), (55, 8), (42, 10)
;

INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Europe/Kaliningrad', '+2');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Europe/Moscow', '+3');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Europe/Samara', '+4');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Yekaterinburg', '+5');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Omsk', '+6');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Novosibirsk', '+7');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Irkutsk', '+8');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Chita', '+9');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Vladivostok', '+10');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Sakhalin', '+11');
INSERT INTO todo_time_zones (name,utc_offset) VALUES ('Asia/Kamchatka', '+12');