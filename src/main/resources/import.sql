

insert into superuser VALUES (1, "harsh", "harsh", "abc", "s", "ROLE_SUPERUSER");
insert into superuser VALUES (2, "nishi", "nishi", "abc", "s", "ROLE_SUEPRUSER");


insert into location(id, city, street, town, landmark, district, pin, state) values (1, "manberia", "sdhadhahsd", "akjdhadkhsad", "asdhjgsadhgskj", "dslhsalkhdjj", "123453", "asdsa");
insert into location(id, city, street, town, landmark, district, pin, state) values (2, "kulti", "sdhadhahsd", "akjdhadkhsad", "asdhjgsadhgskj", "dslhsalkhdjj", "121212", "asdsa");
insert into location(id, city, street, town, landmark, district, pin, state) values (3, "asnasol", "sdhadhahsd", "akjdhadkhsad", "asdhjgsadhgskj", "dslhsalkhdjj", "1234567", "asdsa");

insert into branch(id, active, contact_no, date, name, address_id) values(1, true, "8918930230", "2012:12:12", "itrd", 2);

insert into employee(name, email, password, joining_date, personal_contact, role, branch_id) VALUES("harsh", "harsh", "abc", "2020-10-10", "983743987", "ROLE_EMPLOYEE", "1");
insert into employee(name, email, password, joining_date, personal_contact, role, branch_id) VALUES("harsh", "prince", "abc", "2020-10-10", "243", "ROLE_EMPLOYEE", "1");
insert into employee(name, email, password, joining_date, personal_contact, role, branch_id) VALUES("harsh", "nishi", "abc", "2020-10-10", "98372342387", "ROLE_EMPLOYEE", "1");