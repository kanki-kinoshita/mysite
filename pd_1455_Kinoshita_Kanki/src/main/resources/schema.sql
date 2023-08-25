drop table if exists members cascade;
drop table if exists histories cascade;
drop table if exists categories cascade;
drop table if exists budgets cascade;

create table members(
id serial primary key,
name text not null,
email text not null unique,
password text not null,
balance INTEGER not null
);

create table categories(
id serial primary key,
name text not null unique 
);

create table histories(
id serial primary key,
member_id Integer not null references members,
category_id Integer not null references categories,
price Integer not null,
date_at date not null,
memo text
);

create table budgets(
id serial primary key,
member_id Integer not null references members,
category_id Integer not null references categories,
price Integer not null,
unique(member_id,category_id)
);