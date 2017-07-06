insert into customer (firstname, lastname ) values ('Foo', 'Bar');
insert into customer (firstname, lastname ) values ('Jane', 'Doe');
insert into account( customer_id , expiry_date ) values ( 1, now() );
insert into account( customer_id , expiry_date ) values ( 2, now() );