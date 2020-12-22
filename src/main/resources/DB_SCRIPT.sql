CREATE TABLE webservice.user_table (
	id int primary key,
	username text NOT null UNIQUE,
	"password" text NOT NULL,
	salt text not null,
	first_name text NULL,
	last_name text NULL,
	account_created_date text NULL,
	email text NOT null UNIQUE,
	user_uuid text not null UNIQUE
);

select * from webservice.user_table;

delete from webservice.user_table where id >= 0;