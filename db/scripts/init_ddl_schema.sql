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

ALTER TABLE tasks ADD COLUMN priority_id int
  REFERENCES priorities(id);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE
);

CREATE TABLE categories_and_tasks (
    id SERIAL PRIMARY KEY,
    task_id INT REFERENCES tasks(id),
    category_id INT REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS todo_time_zones
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    utc_offset CHAR(3) NOT NULL
);

ALTER TABLE todo_user ADD COLUMN time_zone_id INT REFERENCES todo_time_zones(id);