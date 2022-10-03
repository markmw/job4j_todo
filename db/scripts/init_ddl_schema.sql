CREATE TABLE tasks (
   id SERIAL PRIMARY KEY,
   description TEXT,
   created TIMESTAMP,
   done BOOLEAN
);

CREATE TABLE todo_user(
    id SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    login varchar(255) NOT NULL UNIQUE,
    password TEXT NOT NULL
);

ALTER TABLE tasks ADD COLUMN user_id int REFERENCES todo_user(id);

CREATE TABLE priorities (
   id SERIAL PRIMARY KEY,
   name TEXT,
   position int
);

INSERT INTO priorities (name, position) VALUES ('urgently', 1);
INSERT INTO priorities (name, position) VALUES ('normal', 2);
INSERT INTO priorities (name, position) VALUES ('urgently', 3);

ALTER TABLE tasks ADD COLUMN priority_id int
  REFERENCES priorities(id);

UPDATE tasks SET priority_id = (SELECT id FROM priorities WHERE name = 'urgently' LIMIT 1);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE
);

CREATE TABLE categories_and_tasks (
    id SERIAL PRIMARY KEY,
    task_id INT REFERENCES tasks(id),
    category_id INT REFERENCES categories(id)
);