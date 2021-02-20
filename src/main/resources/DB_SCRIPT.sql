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

CREATE TABLE webservice.session_table (
	id int primary key,
	user_uuid text NOT null,
	session_uuid text not null,
	session_create_date text not null,
	foreign key (user_uuid)
		references webservice.user_table(user_uuid)
);
