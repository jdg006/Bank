truncate account,user_id_account_id,user_info;

ALTER SEQUENCE account_id_seq RESTART WITH 1;
ALTER SEQUENCE user_id_seq RESTART WITH 1;

insert into account (type_of, balance) values ('savings', 20);
insert into account (type_of, balance) values ('checking', 100);
insert into account (type_of, balance) values ('savings', 200);
insert into account (type_of, balance) values ('checking', 100);


insert into user_info (first_name, last_name, username, password) values ('joe','joeson','joeu','joepass');
insert into user_info (first_name, last_name, username, password) values ('jim','jimson','jimu','jimpass');
insert into user_info (first_name, last_name, username, password) values ('jil','jilson','jilu','jilpass');

insert into user_id_account_id values (1,1),(1,2),(2,3),(3,4);
