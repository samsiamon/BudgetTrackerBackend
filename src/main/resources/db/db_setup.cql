CREATE KEYSPACE budget_tracker WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

USE budget_tracker;

CREATE TABLE budget_item (
    id uuid PRIMARY KEY,
    user uuid,
    name text,
    item_type text,
    amount double,
    frequency text
);

CREATE TABLE user (
    id uuid PRIMARY KEY,
    email text,
    password text
);