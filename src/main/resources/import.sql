

INSERT INTO superuser VALUES(1, "harsh", "harsh", "123123123", "s", "ROLE_SUPERUSER");

INSERT INTO superuser VALUES(2, "nishi", "nishi", "123123123", "s", "ROLE_SUEPRUSER");

INSERT INTO address(name, contact_no, city, landmark, line1, district, pincode, state) values ("harsh", 8918989090, "manberia", "akjdhadkhsad", "asdhjgsadhgskj", "dslhsalkhdjj", "123453", "asdsa");


insert into association(id, active, contact1, contact2, email, name, password, address_id) values(1, true, "8918918991", "9821380120", "harsh@gmail.com", "harsh", "123123123", 1);


insert into branch(id, code, active, contact_no, date, name, address_id) values(1, "BR_ITRD", true, "8918930230", "2012:12:12", "itrd", 1);

insert into employee(name, email, password, joining_date, personal_contact, role, branch_id) VALUES("harsh", "harsh@gmail.com", "123123123", "2020-10-10", "243", "ROLE_CUSTOMER_CARE", "1");
insert into employee(name, email, password, joining_date, personal_contact, role, branch_id) VALUES("prince", "prince@gmail.com", "123123123", "2020-10-10", "98372342387", "ROLE_EMPLOYEE", "1");
insert into employee(name, email, password, joining_date, personal_contact, role, branch_id) VALUES("moon", "princewillz2013@gmail.com", "123123123", "2020-10-10", "98372342380", "ROLE_ADMIN", "1");
insert into employee(name, email, password, joining_date, personal_contact, role, branch_id) VALUES("moon", "nishigupta726@gmail.com", "123123123", "2020-10-10", "9837242380", "ROLE_DELIVERY", "1");


insert into cover(cover_name, cover_size, cover_price, association_id) values("digital_cover", "12*24", 450, 1);
insert into cover(cover_name, cover_size, cover_price, association_id) values("digital_cover", "12*30", 450, 1);
insert into cover(cover_name, cover_size, cover_price, association_id) values("digital_cover", "6*8", 450, 1);
insert into cover(cover_name, cover_size, cover_price, association_id) values("digital_cover", "9*12", 450, 1);
insert into cover(cover_name, cover_size, cover_price, association_id) values("trans_lite", "12*24", 250, 1);
insert into cover(cover_name, cover_size, cover_price, association_id) values("acreylic", "12*24", 900, 1);
insert into cover(cover_name, cover_size, cover_price, association_id) values("wall_calender", "12*24", 350, 1);


insert into paper(paper_quality, paper_size, paper_price, association_id) values("medium_glossy", "12*30", 38, 1);
insert into paper(paper_quality, paper_size, paper_price, association_id) values("medium_glossy", "12*36", 38, 1);
insert into paper(paper_quality, paper_size, paper_price, association_id) values("silkymatt", "12*24", 40, 1);
insert into paper(paper_quality, paper_size, paper_price, association_id) values("mediumhighshine", "12*24", 50, 1);
insert into paper(paper_quality, paper_size, paper_price, association_id) values("heavysuperglossy", "12*24", 50, 1);
insert into paper(paper_quality, paper_size, paper_price, association_id) values("glasshd", "12*24", 90, 1);


insert into product_category values(1, true, "category1", 1);
insert into product_category values(2, true, "category2", 1);
insert into product_category values(3, true, "category3", 1);
insert into product_category values(4, true, "category4", 1);

insert into customer (id, active, contact_no, email, password) values(1, 1, 8918930270, "nishigupta726@gmail.com", "123123123");






