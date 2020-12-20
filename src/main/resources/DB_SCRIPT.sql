CREATE TABLE webservice.user_table (
	username text NOT NULL,
	"password" text NOT NULL,
	first_name text NULL,
	last_name text NULL,
	account_created_date date NULL,
	email text NOT NULL,
	user_uuid text NOT NULL
);

select * from webservice.user_table;