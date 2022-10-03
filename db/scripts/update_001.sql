insert into tasks(description, created, done) VALUES ('Очень длинное описание Очень длинное описание Очень длинное описание Очень длинное описание Очень длинное описание Очень длинное описание',
                                                     '2022-09-14 14:00', false);
insert into tasks(description, created, done) VALUES ('Другое описание немного поменьше',
                                                     '2022-07-14 15:30', true);

insert into todo_user(name, login, password) VALUES ('addy', 'add', 'password');
insert into todo_user(name, login, password) VALUES ('baddy', 'bad', 'password');

INSERT INTO categories (name)
VALUES ('Работа'), ('Семья'), ('Покупки'), ('Саморазвитие'), ('Позвонить'),
       ('Написать письмо')
;

INSERT INTO categories_and_tasks (task_id, category_id)
VALUES (54, 7), (55, 8), (42, 10)
;