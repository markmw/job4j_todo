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