--1
-- create extension if not exists pg_trgm; ????

--2
create index resource_data_email_index on data.resource_data (email);
create index resource_data_description_index on data.resource_data (description);
create index resource_data_login_index on data.resource_data (login);
create index resource_data_name_index on data.resource_data (name);

--3
SELECT *
FROM data.resource_data
WHERE email like '%gmail%'
   OR description like '%gmail%'
   OR login like '%gmail%'
   OR name like '%gmail%';
