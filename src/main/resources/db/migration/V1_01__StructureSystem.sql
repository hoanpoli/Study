DROP TABLE IF EXISTS PUBLIC."sample_table";
CREATE TABLE "sample_table"
(
	"id"					SERIAL PRIMARY KEY,
	"last_name"				VARCHAR(64),
	"first_name"			VARCHAR(64)	
);

DROP TABLE IF EXISTS PUBLIC."user";
CREATE TABLE PUBLIC."user"
(
	"id" 					SERIAL PRIMARY KEY,
	"user_name"				VARCHAR(64),
	"email"					VARCHAR(128),
	"account_no"			VARCHAR(64),
	"first_name"			VARCHAR(32),
	"last_name"				VARCHAR(32),
	"contact_no"			VARCHAR(16),
	"remarks"				VARCHAR(128),
	"status"				CHARACTER(3) NOT NULL DEFAULT 'ACT',
	"uuid"					UUID,
	"password_hash"			VARCHAR(256),
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."auth_token";
CREATE TABLE PUBLIC."auth_token"
(
	"id"					SERIAL PRIMARY KEY,
	"client_key"			VARCHAR(64),
	"module"				VARCHAR(32),
	"token"					VARCHAR(8),
	"expire_on"				TIMESTAMP,
	"is_verified"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."setting";
CREATE TABLE PUBLIC."setting"
(
	"id"					SERIAL PRIMARY KEY,
	"code"					VARCHAR(64),
	"value"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"status"				CHARACTER(3) NOT NULL DEFAULT 'INA',
	"sequence"				INT4,
	"user_id"				INT4,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);