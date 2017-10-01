Database Schema

phase I user tables, only for twitter users
create table users (
    id integer not null,
    user_name varchar(100) not null,
    password varchar(100) not null,
    email varchar(100) not null,
    status integer not null,
    marketing_id integer,
    country_id integer,
    create_time timestamp not null,
    update_time timestamp not null,
    ip_address varchar(15),
    num_categories integer,
    first_name varchar(100),
    last_name varchar(100)
);

create sequence users_seq;

alter table users add column profile_photo varchar(2048);

CREATE INDEX users_id_index ON users(id);
CREATE INDEX users_user_name_index ON users(user_name);


//Everytime a user imports address book, fill this in.
create table users_viral (
    user_id integer not null,
    create_time timestamp not null,
    update_time timestamp not null,
    last_time_address_imported integer,
    total_address_imported integer,
    last_invites_sent integer,
    total_invites_sent integer,
    total_invites_opened integer,
    total_invites_clicked integer,
    total_invites_accepted integer
);

//use two letter ISO
http://www.iso.org/iso/english_country_names_and_code_elements
create table country (
    id integer not null,
    code varchar(2) not null,
    name varchar(100) not null,
    status integer not null,
    create_time timestamp not null,
    order_by integer not null
);


create table marketing (
    id integer not null,
    name varchar(100) not null,
    status integer not null,
    marketing_type integer not null,
    create_time timestamp not null
);


create table categories (
    id integer not null,
    name varchar(100) not null,
    status integer not null,
    create_time timestamp not null,
    order_by integer not null
);

create table mytweetmark (
    id integer not null,
    category_id integer not null,
    user_id integer not null,
    url varchar(2056) not null,
    status integer not null,
    create_time timestamp not null,
    update_time timestamp not null,
    description varchar(512)              
);

create sequence mytweetmark_seq;

CREATE INDEX mytweetmark_id_index ON mytweetmark(id);
CREATE INDEX mytweetmark_user_id_index ON mytweetmark(user_id);

create table myCategory (
    user_id integer not null,
    Category_id integer not null,
    status integer not null,
    create_time timestamp not null,
    update_time timestamp not null,
    num_my_tweets integer
);


CREATE INDEX myCategory_user_id_index ON myCategory(user_id);

alter table Categories add primary key (id); //Categories_pkey
alter table country add primary key (id); //country_pkey
alter table marketing add primary key (id); //marketing_pkey
alter table mytweetmark add primary key (id); //mytweetmark_pkey
alter table users_viral add primary key (user_id); //users_viral_pkey

//should calculate needed averages and percentages on the coding level, rather putting in the db.  the app is for admin purposes anyway.
create table viral_summary (
    id integer not null,
    create_time timestamp not null,
    total_registrations integer,
    total_address_book_scraped_size integer,
    total_send_size integer,
    total_opened integer,
    total_clicked integer,
    total_registered integer,
    is_self_reg varchar(1),
    is_day_total varchar(1),
    marketing_id integer,
    country_id integer,
    Category_id integer
);
create sequence viral_summary_seq;

create table retention_summary (
    id integer not null,
    create_time timestamp not null,
    total_registrations integer,
    total_logins integer,
    total_logins_5_or_less integer,
    total_logins_6_to_10 integer,
    total_logins_11_to_15 integer,
    total_logins_16_to_20 integer,
    total_logins_21_to_25 integer,
    total_logins_26_to_30 integer
);
create sequence retention_summary_seq;

create table post (
    id integer not null,
    create_time timestamp not null,
    user_id integer not null,
    description varchar(500) not null,
    status integer not null
);
create sequence post_seq;
alter table post add primary key (id); //post_pkey


CREATE INDEX post_id_index ON post(id);

CREATE INDEX post_user_id_index ON post(user_id);


CREATE INDEX post_create_time_index ON post(create_time);

phase II
//alter statements for any extra columns dealing with more user info
alter table users add column first_name varchar(100);
alter table users add column last_name varchar(100);
alter table users add column dob date;
alter table users add column gender varchar(1);

//access table, that contains more username and password for twitter, facebook, etc.
create table access (
    user_id integer not null,
    user_name varchar(100) not null,
    password varchar(100) not null,
    user_type integer,
    status integer not null,
    email varchar(100),
    create_time timestamp,
    update_time timestamp
);

//reorder mytweetmark and categories
alter table mytweetmark add column orderby integer;
alter table myCategory add column orderby integer;

//add description to mycategories?

Categories setup


insert into Categories (id, name, status, create_time, order_by) values (1, 'Airplanes', 1, now(), 1);
insert into Categories (id, name, status, create_time, order_by) values (2, 'Animals', 1, now(), 2);
insert into Categories (id, name, status, create_time, order_by) values (3, 'Books', 1, now(), 3);
insert into Categories (id, name, status, create_time, order_by) values (4, 'Children', 1, now(), 4);
insert into Categories (id, name, status, create_time, order_by) values (5, 'Cool things', 1, now(), 5);
insert into Categories (id, name, status, create_time, order_by) values (6, 'Cooking', 1, now(), 6);
insert into Categories (id, name, status, create_time, order_by) values (7, 'Drinks', 1, now(), 7);
insert into Categories (id, name, status, create_time, order_by) values (8, 'Earth', 1, now(), 8);
insert into Categories (id, name, status, create_time, order_by) values (9, 'Exercise', 1, now(), 9);
insert into Categories (id, name, status, create_time, order_by) values (10, 'Family', 1, now(), 10);
insert into Categories (id, name, status, create_time, order_by) values (11, 'Fashion', 1, now(), 11);
insert into Categories (id, name, status, create_time, order_by) values (12, 'Film', 1, now(), 12);
insert into Categories (id, name, status, create_time, order_by) values (13, 'Food', 1, now(), 13);
insert into Categories (id, name, status, create_time, order_by) values (14, 'Friends', 1, now(), 14);
insert into Categories (id, name, status, create_time, order_by) values (15, 'Gadgets', 1, now(), 15);
insert into Categories (id, name, status, create_time, order_by) values (16, 'Games', 1, now(), 16);
insert into Categories (id, name, status, create_time, order_by) values (17, 'Heroes', 1, now(), 17);
insert into Categories (id, name, status, create_time, order_by) values (18, 'Home', 1, now(), 18);
insert into Categories (id, name, status, create_time, order_by) values (19, 'Illness', 1, now(), 19);
insert into Categories (id, name, status, create_time, order_by) values (20, 'Important', 1, now(), 20);
insert into Categories (id, name, status, create_time, order_by) values (21, 'Internet', 1, now(), 21);
insert into Categories (id, name, status, create_time, order_by) values (22, 'Links', 1, now(), 22);
insert into Categories (id, name, status, create_time, order_by) values (23, 'Love', 1, now(), 23);
insert into Categories (id, name, status, create_time, order_by) values (24, 'Music', 1, now(), 24);
insert into Categories (id, name, status, create_time, order_by) values (25, 'Nature', 1, now(), 25);
insert into Categories (id, name, status, create_time, order_by) values (0, 'Other', 1, now(), 26);
insert into Categories (id, name, status, create_time, order_by) values (26, 'Ocean', 1, now(), 27);
insert into Categories (id, name, status, create_time, order_by) values (27, 'People', 1, now(), 28);
insert into Categories (id, name, status, create_time, order_by) values (28, 'Pictures', 1, now(), 29);
insert into Categories (id, name, status, create_time, order_by) values (29, 'Places', 1, now(), 30);
insert into Categories (id, name, status, create_time, order_by) values (30, 'Plants', 1, now(), 31);
insert into Categories (id, name, status, create_time, order_by) values (31, 'Politics', 1, now(), 32);
insert into Categories (id, name, status, create_time, order_by) values (32, 'Projects', 1, now(), 33);
insert into Categories (id, name, status, create_time, order_by) values (33, 'Restaurants', 1, now(), 34);
insert into Categories (id, name, status, create_time, order_by) values (34, 'Shopping', 1, now(), 35);
insert into Categories (id, name, status, create_time, order_by) values (35, 'Ships', 1, now(), 36);
insert into Categories (id, name, status, create_time, order_by) values (36, 'Social networking', 1, now(), 37);
insert into Categories (id, name, status, create_time, order_by) values (37, 'Space', 1, now(), 38);
insert into Categories (id, name, status, create_time, order_by) values (38, 'Spirituality', 1, now(), 39);
insert into Categories (id, name, status, create_time, order_by) values (39, 'Sports', 1, now(), 40);
insert into Categories (id, name, status, create_time, order_by) values (40, 'Technology', 1, now(), 41);
insert into Categories (id, name, status, create_time, order_by) values (41, 'Toys', 1, now(), 42);
insert into Categories (id, name, status, create_time, order_by) values (42, 'Trains', 1, now(), 43);
insert into Categories (id, name, status, create_time, order_by) values (43, 'Transportation', 1, now(), 44);
insert into Categories (id, name, status, create_time, order_by) values (44, 'Travel', 1, now(), 45);
insert into Categories (id, name, status, create_time, order_by) values (45, 'Vehicle', 1, now(), 46);
insert into Categories (id, name, status, create_time, order_by) values (46, 'Videos', 1, now(), 47);

Country Setup

insert into country (id, code, name, status, create_time, order_by) values (1, 'US', 'UNITED STATES', 1, now(), 1);
insert into country (id, code, name, status, create_time, order_by) values (2, 'UK', 'UNITED KINGDOM', 1, now(), 2);
insert into country (id, name, code, status, create_time, order_by) values (3, 'AFGHANISTAN','AF', 1, now(), 3);
insert into country (id, name, code, status, create_time, order_by) values (4, 'ÅLAND ISLANDS','AX', 1, now(), 4);
insert into country (id, name, code, status, create_time, order_by) values (5, 'ALBANIA','AL', 1, now(), 5);
insert into country (id, name, code, status, create_time, order_by) values (6, 'ALGERIA','DZ', 1, now(), 6);
insert into country (id, name, code, status, create_time, order_by) values (7, 'AMERICAN SAMOA','AS', 1, now(), 7);
insert into country (id, name, code, status, create_time, order_by) values (8, 'ANDORRA','AD', 1, now(), 8);
insert into country (id, name, code, status, create_time, order_by) values (9, 'ANGOLA','AO', 1, now(), 9);
insert into country (id, name, code, status, create_time, order_by) values (10, 'ANGUILLA','AI', 1, now(), 10);
insert into country (id, name, code, status, create_time, order_by) values (11, 'ANTARCTICA','AQ', 1, now(), 11);
insert into country (id, name, code, status, create_time, order_by) values (12, 'ANTIGUA AND BARBUDA','AG', 1, now(), 12);
insert into country (id, name, code, status, create_time, order_by) values (13, 'ARGENTINA','AR', 1, now(), 13);
insert into country (id, name, code, status, create_time, order_by) values (14, 'ARMENIA','AM', 1, now(), 14);
insert into country (id, name, code, status, create_time, order_by) values (15, 'ARUBA','AW', 1, now(), 15);
insert into country (id, name, code, status, create_time, order_by) values (16, 'AUSTRALIA','AU', 1, now(), 16);
insert into country (id, name, code, status, create_time, order_by) values (17, 'AUSTRIA','AT', 1, now(), 17);
insert into country (id, name, code, status, create_time, order_by) values (18, 'AZERBAIJAN','AZ', 1, now(), 18);
insert into country (id, name, code, status, create_time, order_by) values (19, 'BAHAMAS','BS', 1, now(), 19);
insert into country (id, name, code, status, create_time, order_by) values (20, 'BAHRAIN','BH', 1, now(), 20);
insert into country (id, name, code, status, create_time, order_by) values (21, 'BANGLADESH','BD', 1, now(), 21);
insert into country (id, name, code, status, create_time, order_by) values (22, 'BARBADOS','BB', 1, now(), 22);
insert into country (id, name, code, status, create_time, order_by) values (23, 'BELARUS','BY', 1, now(), 23);
insert into country (id, name, code, status, create_time, order_by) values (24, 'BELGIUM','BE', 1, now(), 24);
insert into country (id, name, code, status, create_time, order_by) values (25, 'BELIZE','BZ', 1, now(), 25);
insert into country (id, name, code, status, create_time, order_by) values (26, 'BENIN','BJ', 1, now(), 26);
insert into country (id, name, code, status, create_time, order_by) values (27, 'BERMUDA','BM', 1, now(), 27);
insert into country (id, name, code, status, create_time, order_by) values (28, 'BHUTAN','BT', 1, now(), 28);
insert into country (id, name, code, status, create_time, order_by) values (29, 'BOLIVIA, PLURINATIONAL STATE OF','BO', 1, now(), 29);
insert into country (id, name, code, status, create_time, order_by) values (30, 'BOSNIA AND HERZEGOVINA','BA', 1, now(), 30);
insert into country (id, name, code, status, create_time, order_by) values (31, 'BOTSWANA','BW', 1, now(), 31);
insert into country (id, name, code, status, create_time, order_by) values (32, 'BOUVET ISLAND','BV', 1, now(), 32);
insert into country (id, name, code, status, create_time, order_by) values (33, 'BRAZIL','BR', 1, now(), 33);
insert into country (id, name, code, status, create_time, order_by) values (34, 'BRITISH INDIAN OCEAN TERRITORY','IO', 1, now(), 34);
insert into country (id, name, code, status, create_time, order_by) values (35, 'BRUNEI DARUSSALAM','BN', 1, now(), 35);
insert into country (id, name, code, status, create_time, order_by) values (36, 'BULGARIA','BG', 1, now(), 36);
insert into country (id, name, code, status, create_time, order_by) values (37, 'BURKINA FASO','BF', 1, now(), 37);
insert into country (id, name, code, status, create_time, order_by) values (38, 'BURUNDI','BI', 1, now(), 38);
insert into country (id, name, code, status, create_time, order_by) values (39, 'CAMBODIA','KH', 1, now(), 39);
insert into country (id, name, code, status, create_time, order_by) values (40, 'CAMEROON','CM', 1, now(), 40);
insert into country (id, name, code, status, create_time, order_by) values (41, 'CANADA','CA', 1, now(), 41);
insert into country (id, name, code, status, create_time, order_by) values (42, 'CAPE VERDE','CV', 1, now(), 42);
insert into country (id, name, code, status, create_time, order_by) values (43, 'CAYMAN ISLANDS','KY', 1, now(), 43);
insert into country (id, name, code, status, create_time, order_by) values (44, 'CENTRAL AFRICAN REPUBLIC','CF', 1, now(), 44);
insert into country (id, name, code, status, create_time, order_by) values (45, 'CHAD','TD', 1, now(), 45);
insert into country (id, name, code, status, create_time, order_by) values (46, 'CHILE','CL', 1, now(), 46);
insert into country (id, name, code, status, create_time, order_by) values (47, 'CHINA','CN', 1, now(), 47);
insert into country (id, name, code, status, create_time, order_by) values (48, 'CHRISTMAS ISLAND','CX', 1, now(), 48);
insert into country (id, name, code, status, create_time, order_by) values (49, 'COCOS (KEELING) ISLANDS','CC', 1, now(), 49);
insert into country (id, name, code, status, create_time, order_by) values (50, 'COLOMBIA','CO', 1, now(), 50);
insert into country (id, name, code, status, create_time, order_by) values (51, 'COMOROS','KM', 1, now(), 51);
insert into country (id, name, code, status, create_time, order_by) values (52, 'CONGO','CG', 1, now(), 52);
insert into country (id, name, code, status, create_time, order_by) values (53, 'CONGO, THE DEMOCRATIC REPUBLIC OF THE','CD', 1, now(), 53);
insert into country (id, name, code, status, create_time, order_by) values (54, 'COOK ISLANDS','CK', 1, now(), 54);
insert into country (id, name, code, status, create_time, order_by) values (55, 'COSTA RICA','CR', 1, now(), 55);
insert into country (id, name, code, status, create_time, order_by) values (56, 'CÔTE DIVOIRE','CI', 1, now(), 56);
insert into country (id, name, code, status, create_time, order_by) values (57, 'CROATIA','HR', 1, now(), 57);
insert into country (id, name, code, status, create_time, order_by) values (58, 'CUBA','CU', 1, now(), 58);
insert into country (id, name, code, status, create_time, order_by) values (59, 'CYPRUS','CY', 1, now(), 59);
insert into country (id, name, code, status, create_time, order_by) values (60, 'CZECH REPUBLIC','CZ', 1, now(), 60);
insert into country (id, name, code, status, create_time, order_by) values (61, 'DENMARK','DK', 1, now(), 61);
insert into country (id, name, code, status, create_time, order_by) values (62, 'DJIBOUTI','DJ', 1, now(), 62);
insert into country (id, name, code, status, create_time, order_by) values (63, 'DOMINICA','DM', 1, now(), 63);
insert into country (id, name, code, status, create_time, order_by) values (64, 'DOMINICAN REPUBLIC','DO', 1, now(), 64);
insert into country (id, name, code, status, create_time, order_by) values (65, 'ECUADOR','EC', 1, now(), 65);
insert into country (id, name, code, status, create_time, order_by) values (66, 'EGYPT','EG', 1, now(), 66);
insert into country (id, name, code, status, create_time, order_by) values (67, 'EL SALVADOR','SV', 1, now(), 67);
insert into country (id, name, code, status, create_time, order_by) values (68, 'EQUATORIAL GUINEA','GQ', 1, now(), 68);
insert into country (id, name, code, status, create_time, order_by) values (69, 'ERITREA','ER', 1, now(), 69);
insert into country (id, name, code, status, create_time, order_by) values (70, 'ESTONIA','EE', 1, now(), 70);
insert into country (id, name, code, status, create_time, order_by) values (71, 'ETHIOPIA','ET', 1, now(), 71);
insert into country (id, name, code, status, create_time, order_by) values (72, 'FALKLAND ISLANDS (MALVINAS)','FK', 1, now(), 72);
insert into country (id, name, code, status, create_time, order_by) values (73, 'FAROE ISLANDS','FO', 1, now(), 73);
insert into country (id, name, code, status, create_time, order_by) values (74, 'FIJI','FJ', 1, now(), 74);
insert into country (id, name, code, status, create_time, order_by) values (75, 'FINLAND','FI', 1, now(), 75);
insert into country (id, name, code, status, create_time, order_by) values (76, 'FRANCE','FR', 1, now(), 76);
insert into country (id, name, code, status, create_time, order_by) values (77, 'FRENCH GUIANA','GF', 1, now(), 77);
insert into country (id, name, code, status, create_time, order_by) values (78, 'FRENCH POLYNESIA','PF', 1, now(), 78);
insert into country (id, name, code, status, create_time, order_by) values (79, 'FRENCH SOUTHERN TERRITORIES','TF', 1, now(), 79);
insert into country (id, name, code, status, create_time, order_by) values (80, 'GABON','GA', 1, now(), 80);
insert into country (id, name, code, status, create_time, order_by) values (81, 'GAMBIA','GM', 1, now(), 81);
insert into country (id, name, code, status, create_time, order_by) values (82, 'GEORGIA','GE', 1, now(), 82);
insert into country (id, name, code, status, create_time, order_by) values (83, 'GERMANY','DE', 1, now(), 83);
insert into country (id, name, code, status, create_time, order_by) values (84, 'GHANA','GH', 1, now(), 84);
insert into country (id, name, code, status, create_time, order_by) values (85, 'GIBRALTAR','GI', 1, now(), 85);
insert into country (id, name, code, status, create_time, order_by) values (86, 'GREECE','GR', 1, now(), 86);
insert into country (id, name, code, status, create_time, order_by) values (87, 'GREENLAND','GL', 1, now(), 87);
insert into country (id, name, code, status, create_time, order_by) values (88, 'GRENADA','GD', 1, now(), 88);
insert into country (id, name, code, status, create_time, order_by) values (89, 'GUADELOUPE','GP', 1, now(), 89);
insert into country (id, name, code, status, create_time, order_by) values (90, 'GUAM','GU', 1, now(), 90);
insert into country (id, name, code, status, create_time, order_by) values (91, 'GUATEMALA','GT', 1, now(), 91);
insert into country (id, name, code, status, create_time, order_by) values (92, 'GUERNSEY','GG', 1, now(), 92);
insert into country (id, name, code, status, create_time, order_by) values (93, 'GUINEA','GN', 1, now(), 93);
insert into country (id, name, code, status, create_time, order_by) values (94, 'GUINEA-BISSAU','GW', 1, now(), 94);
insert into country (id, name, code, status, create_time, order_by) values (95, 'GUYANA','GY', 1, now(), 95);
insert into country (id, name, code, status, create_time, order_by) values (96, 'HAITI','HT', 1, now(), 96);
insert into country (id, name, code, status, create_time, order_by) values (97, 'HEARD ISLAND AND MCDONALD ISLANDS','HM', 1, now(), 97);
insert into country (id, name, code, status, create_time, order_by) values (98, 'HOLY SEE (VATICAN CITY STATE)','VA', 1, now(), 98);
insert into country (id, name, code, status, create_time, order_by) values (99, 'HONDURAS','HN', 1, now(), 99);
insert into country (id, name, code, status, create_time, order_by) values (100, 'HONG KONG','HK', 1, now(), 100);
insert into country (id, name, code, status, create_time, order_by) values (101, 'HUNGARY','HU', 1, now(), 101);
insert into country (id, name, code, status, create_time, order_by) values (102, 'ICELAND','IS', 1, now(), 102);
insert into country (id, name, code, status, create_time, order_by) values (103, 'INDIA','IN', 1, now(), 103);
insert into country (id, name, code, status, create_time, order_by) values (104, 'INDONESIA','ID', 1, now(), 104);
insert into country (id, name, code, status, create_time, order_by) values (105, 'IRAN, ISLAMIC REPUBLIC OF','IR', 1, now(), 105);
insert into country (id, name, code, status, create_time, order_by) values (106, 'IRAQ','IQ', 1, now(), 106);
insert into country (id, name, code, status, create_time, order_by) values (107, 'IRELAND','IE', 1, now(), 107);
insert into country (id, name, code, status, create_time, order_by) values (108, 'ISLE OF MAN','IM', 1, now(), 108);
insert into country (id, name, code, status, create_time, order_by) values (109, 'ISRAEL','IL', 1, now(), 109);
insert into country (id, name, code, status, create_time, order_by) values (110, 'ITALY','IT', 1, now(), 110);
insert into country (id, name, code, status, create_time, order_by) values (111, 'JAMAICA','JM', 1, now(), 111);
insert into country (id, name, code, status, create_time, order_by) values (112, 'JAPAN','JP', 1, now(), 112);
insert into country (id, name, code, status, create_time, order_by) values (113, 'JERSEY','JE', 1, now(), 113);
insert into country (id, name, code, status, create_time, order_by) values (114, 'JORDAN','JO', 1, now(), 114);
insert into country (id, name, code, status, create_time, order_by) values (115, 'KAZAKHSTAN','KZ', 1, now(), 115);
insert into country (id, name, code, status, create_time, order_by) values (116, 'KENYA','KE', 1, now(), 116);
insert into country (id, name, code, status, create_time, order_by) values (117, 'KIRIBATI','KI', 1, now(), 117);
insert into country (id, name, code, status, create_time, order_by) values (118, 'KOREA, DEMOCRATIC PEOPLES REPUBLIC OF','KP', 1, now(), 118);
insert into country (id, name, code, status, create_time, order_by) values (119, 'KOREA, REPUBLIC OF','KR', 1, now(), 119);
insert into country (id, name, code, status, create_time, order_by) values (120, 'KUWAIT','KW', 1, now(), 120);
insert into country (id, name, code, status, create_time, order_by) values (121, 'KYRGYZSTAN','KG', 1, now(), 121);
insert into country (id, name, code, status, create_time, order_by) values (122, 'LAO PEOPLES DEMOCRATIC REPUBLIC','LA', 1, now(), 122);
insert into country (id, name, code, status, create_time, order_by) values (123, 'LATVIA','LV', 1, now(), 123);
insert into country (id, name, code, status, create_time, order_by) values (124, 'LEBANON','LB', 1, now(), 124);
insert into country (id, name, code, status, create_time, order_by) values (125, 'LESOTHO','LS', 1, now(), 125);
insert into country (id, name, code, status, create_time, order_by) values (126, 'LIBERIA','LR', 1, now(), 126);
insert into country (id, name, code, status, create_time, order_by) values (127, 'LIBYAN ARAB JAMAHIRIYA','LY', 1, now(), 127);
insert into country (id, name, code, status, create_time, order_by) values (128, 'LIECHTENSTEIN','LI', 1, now(), 128);
insert into country (id, name, code, status, create_time, order_by) values (129, 'LITHUANIA','LT', 1, now(), 129);
insert into country (id, name, code, status, create_time, order_by) values (130, 'LUXEMBOURG','LU', 1, now(), 130);
insert into country (id, name, code, status, create_time, order_by) values (131, 'MACAO','MO', 1, now(), 131);
insert into country (id, name, code, status, create_time, order_by) values (132, 'MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF','MK', 1, now(), 132);
insert into country (id, name, code, status, create_time, order_by) values (133, 'MADAGASCAR','MG', 1, now(), 133);
insert into country (id, name, code, status, create_time, order_by) values (134, 'MALAWI','MW', 1, now(), 134);
insert into country (id, name, code, status, create_time, order_by) values (135, 'MALAYSIA','MY', 1, now(), 135);
insert into country (id, name, code, status, create_time, order_by) values (136, 'MALDIVES','MV', 1, now(), 136);
insert into country (id, name, code, status, create_time, order_by) values (137, 'MALI','ML', 1, now(), 137);
insert into country (id, name, code, status, create_time, order_by) values (138, 'MALTA','MT', 1, now(), 138);
insert into country (id, name, code, status, create_time, order_by) values (139, 'MARSHALL ISLANDS','MH', 1, now(), 139);
insert into country (id, name, code, status, create_time, order_by) values (140, 'MARTINIQUE','MQ', 1, now(), 140);
insert into country (id, name, code, status, create_time, order_by) values (141, 'MAURITANIA','MR', 1, now(), 141);
insert into country (id, name, code, status, create_time, order_by) values (142, 'MAURITIUS','MU', 1, now(), 142);
insert into country (id, name, code, status, create_time, order_by) values (143, 'MAYOTTE','YT', 1, now(), 143);
insert into country (id, name, code, status, create_time, order_by) values (144, 'MEXICO','MX', 1, now(), 144);
insert into country (id, name, code, status, create_time, order_by) values (145, 'MICRONESIA, FEDERATED STATES OF','FM', 1, now(), 145);
insert into country (id, name, code, status, create_time, order_by) values (146, 'MOLDOVA, REPUBLIC OF','MD', 1, now(), 146);
insert into country (id, name, code, status, create_time, order_by) values (147, 'MONACO','MC', 1, now(), 147);
insert into country (id, name, code, status, create_time, order_by) values (148, 'MONGOLIA','MN', 1, now(), 148);
insert into country (id, name, code, status, create_time, order_by) values (149, 'MONTENEGRO','ME', 1, now(), 149);
insert into country (id, name, code, status, create_time, order_by) values (150, 'MONTSERRAT','MS', 1, now(), 150);
insert into country (id, name, code, status, create_time, order_by) values (151, 'MOROCCO','MA', 1, now(), 151);
insert into country (id, name, code, status, create_time, order_by) values (152, 'MOZAMBIQUE','MZ', 1, now(), 152);
insert into country (id, name, code, status, create_time, order_by) values (153, 'MYANMAR','MM', 1, now(), 153);
insert into country (id, name, code, status, create_time, order_by) values (154, 'NAMIBIA','NA', 1, now(), 154);
insert into country (id, name, code, status, create_time, order_by) values (155, 'NAURU','NR', 1, now(), 155);
insert into country (id, name, code, status, create_time, order_by) values (156, 'NEPAL','NP', 1, now(), 156);
insert into country (id, name, code, status, create_time, order_by) values (157, 'NETHERLANDS','NL', 1, now(), 157);
insert into country (id, name, code, status, create_time, order_by) values (158, 'NETHERLANDS ANTILLES','AN', 1, now(), 158);
insert into country (id, name, code, status, create_time, order_by) values (159, 'NEW CALEDONIA','NC', 1, now(), 159);
insert into country (id, name, code, status, create_time, order_by) values (160, 'NEW ZEALAND','NZ', 1, now(), 160);
insert into country (id, name, code, status, create_time, order_by) values (161, 'NICARAGUA','NI', 1, now(), 161);
insert into country (id, name, code, status, create_time, order_by) values (162, 'NIGER','NE', 1, now(), 162);
insert into country (id, name, code, status, create_time, order_by) values (163, 'NIGERIA','NG', 1, now(), 163);
insert into country (id, name, code, status, create_time, order_by) values (164, 'NIUE','NU', 1, now(), 164);
insert into country (id, name, code, status, create_time, order_by) values (165, 'NORFOLK ISLAND','NF', 1, now(), 165);
insert into country (id, name, code, status, create_time, order_by) values (166, 'NORTHERN MARIANA ISLANDS','MP', 1, now(), 166);
insert into country (id, name, code, status, create_time, order_by) values (167, 'NORWAY','NO', 1, now(), 167);
insert into country (id, name, code, status, create_time, order_by) values (168, 'OMAN','OM', 1, now(), 168);
insert into country (id, name, code, status, create_time, order_by) values (169, 'PAKISTAN','PK', 1, now(), 169);
insert into country (id, name, code, status, create_time, order_by) values (170, 'PALAU','PW', 1, now(), 170);
insert into country (id, name, code, status, create_time, order_by) values (171, 'PALESTINIAN TERRITORY, OCCUPIED','PS', 1, now(), 171);
insert into country (id, name, code, status, create_time, order_by) values (172, 'PANAMA','PA', 1, now(), 172);
insert into country (id, name, code, status, create_time, order_by) values (173, 'PAPUA NEW GUINEA','PG', 1, now(), 173);
insert into country (id, name, code, status, create_time, order_by) values (174, 'PARAGUAY','PY', 1, now(), 174);
insert into country (id, name, code, status, create_time, order_by) values (175, 'PERU','PE', 1, now(), 175);
insert into country (id, name, code, status, create_time, order_by) values (176, 'PHILIPPINES','PH', 1, now(), 176);
insert into country (id, name, code, status, create_time, order_by) values (177, 'PITCAIRN','PN', 1, now(), 177);
insert into country (id, name, code, status, create_time, order_by) values (178, 'POLAND','PL', 1, now(), 178);
insert into country (id, name, code, status, create_time, order_by) values (179, 'PORTUGAL','PT', 1, now(), 179);
insert into country (id, name, code, status, create_time, order_by) values (180, 'PUERTO RICO','PR', 1, now(), 180);
insert into country (id, name, code, status, create_time, order_by) values (181, 'QATAR','QA', 1, now(), 181);
insert into country (id, name, code, status, create_time, order_by) values (182, 'RÉUNION','RE', 1, now(), 182);
insert into country (id, name, code, status, create_time, order_by) values (183, 'ROMANIA','RO', 1, now(), 183);
insert into country (id, name, code, status, create_time, order_by) values (184, 'RUSSIAN FEDERATION','RU', 1, now(), 184);
insert into country (id, name, code, status, create_time, order_by) values (185, 'RWANDA','RW', 1, now(), 185);
insert into country (id, name, code, status, create_time, order_by) values (186, 'SAINT BARTHÉLEMY','BL', 1, now(), 186);
insert into country (id, name, code, status, create_time, order_by) values (187, 'SAINT HELENA','SH', 1, now(), 187);
insert into country (id, name, code, status, create_time, order_by) values (188, 'SAINT KITTS AND NEVIS','KN', 1, now(), 188);
insert into country (id, name, code, status, create_time, order_by) values (189, 'SAINT LUCIA','LC', 1, now(), 189);
insert into country (id, name, code, status, create_time, order_by) values (190, 'SAINT MARTIN','MF', 1, now(), 190);
insert into country (id, name, code, status, create_time, order_by) values (191, 'SAINT PIERRE AND MIQUELON','PM', 1, now(), 191);
insert into country (id, name, code, status, create_time, order_by) values (192, 'SAINT VINCENT AND THE GRENADINES','VC', 1, now(), 192);
insert into country (id, name, code, status, create_time, order_by) values (193, 'SAMOA','WS', 1, now(), 193);
insert into country (id, name, code, status, create_time, order_by) values (194, 'SAN MARINO','SM', 1, now(), 194);
insert into country (id, name, code, status, create_time, order_by) values (195, 'SAO TOME AND PRINCIPE','ST', 1, now(), 195);
insert into country (id, name, code, status, create_time, order_by) values (196, 'SAUDI ARABIA','SA', 1, now(), 196);
insert into country (id, name, code, status, create_time, order_by) values (197, 'SENEGAL','SN', 1, now(), 197);
insert into country (id, name, code, status, create_time, order_by) values (198, 'SERBIA','RS', 1, now(), 198);
insert into country (id, name, code, status, create_time, order_by) values (199, 'SEYCHELLES','SC', 1, now(), 199);
insert into country (id, name, code, status, create_time, order_by) values (200, 'SIERRA LEONE','SL', 1, now(), 200);
insert into country (id, name, code, status, create_time, order_by) values (201, 'SINGAPORE','SG', 1, now(), 201);
insert into country (id, name, code, status, create_time, order_by) values (202, 'SLOVAKIA','SK', 1, now(), 202);
insert into country (id, name, code, status, create_time, order_by) values (203, 'SLOVENIA','SI', 1, now(), 203);
insert into country (id, name, code, status, create_time, order_by) values (204, 'SOLOMON ISLANDS','SB', 1, now(), 204);
insert into country (id, name, code, status, create_time, order_by) values (205, 'SOMALIA','SO', 1, now(), 205);
insert into country (id, name, code, status, create_time, order_by) values (206, 'SOUTH AFRICA','ZA', 1, now(), 206);
insert into country (id, name, code, status, create_time, order_by) values (207, 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS','GS', 1, now(), 207);
insert into country (id, name, code, status, create_time, order_by) values (208, 'SPAIN','ES', 1, now(), 208);
insert into country (id, name, code, status, create_time, order_by) values (209, 'SRI LANKA','LK', 1, now(), 209);
insert into country (id, name, code, status, create_time, order_by) values (210, 'SUDAN','SD', 1, now(), 210);
insert into country (id, name, code, status, create_time, order_by) values (211, 'SURINAME','SR', 1, now(), 211);
insert into country (id, name, code, status, create_time, order_by) values (212, 'SVALBARD AND JAN MAYEN','SJ', 1, now(), 212);
insert into country (id, name, code, status, create_time, order_by) values (213, 'SWAZILAND','SZ', 1, now(), 213);
insert into country (id, name, code, status, create_time, order_by) values (214, 'SWEDEN','SE', 1, now(), 214);
insert into country (id, name, code, status, create_time, order_by) values (215, 'SWITZERLAND','CH', 1, now(), 215);
insert into country (id, name, code, status, create_time, order_by) values (216, 'SYRIAN ARAB REPUBLIC','SY', 1, now(), 216);
insert into country (id, name, code, status, create_time, order_by) values (217, 'TAIWAN, PROVINCE OF CHINA','TW', 1, now(), 217);
insert into country (id, name, code, status, create_time, order_by) values (218, 'TAJIKISTAN','TJ', 1, now(), 218);
insert into country (id, name, code, status, create_time, order_by) values (219, 'TANZANIA, UNITED REPUBLIC OF','TZ', 1, now(), 219);
insert into country (id, name, code, status, create_time, order_by) values (220, 'THAILAND','TH', 1, now(), 220);
insert into country (id, name, code, status, create_time, order_by) values (221, 'TIMOR-LESTE','TL', 1, now(), 221);
insert into country (id, name, code, status, create_time, order_by) values (222, 'TOGO','TG', 1, now(), 222);
insert into country (id, name, code, status, create_time, order_by) values (223, 'TOKELAU','TK', 1, now(), 223);
insert into country (id, name, code, status, create_time, order_by) values (224, 'TONGA','TO', 1, now(), 224);
insert into country (id, name, code, status, create_time, order_by) values (225, 'TRINIDAD AND TOBAGO','TT', 1, now(), 225);
insert into country (id, name, code, status, create_time, order_by) values (226, 'TUNISIA','TN', 1, now(), 226);
insert into country (id, name, code, status, create_time, order_by) values (227, 'TURKEY','TR', 1, now(), 227);
insert into country (id, name, code, status, create_time, order_by) values (228, 'TURKMENISTAN','TM', 1, now(), 228);
insert into country (id, name, code, status, create_time, order_by) values (229, 'TURKS AND CAICOS ISLANDS','TC', 1, now(), 229);
insert into country (id, name, code, status, create_time, order_by) values (230, 'TUVALU','TV', 1, now(), 230);
insert into country (id, name, code, status, create_time, order_by) values (231, 'UGANDA','UG', 1, now(), 231);
insert into country (id, name, code, status, create_time, order_by) values (232, 'UKRAINE','UA', 1, now(), 232);
insert into country (id, name, code, status, create_time, order_by) values (233, 'UNITED ARAB EMIRATES','AE', 1, now(), 233);
insert into country (id, name, code, status, create_time, order_by) values (234, 'UNITED STATES MINOR OUTLYING ISLANDS','UM', 1, now(), 234);
insert into country (id, name, code, status, create_time, order_by) values (235, 'URUGUAY','UY', 1, now(), 235);
insert into country (id, name, code, status, create_time, order_by) values (236, 'UZBEKISTAN','UZ', 1, now(), 236);
insert into country (id, name, code, status, create_time, order_by) values (237, 'VANUATU','VU', 1, now(), 237);
insert into country (id, name, code, status, create_time, order_by) values (238, 'VENEZUELA, BOLIVARIAN REPUBLIC OF','VE', 1, now(), 238);
insert into country (id, name, code, status, create_time, order_by) values (239, 'VIET NAM','VN', 1, now(), 239);
insert into country (id, name, code, status, create_time, order_by) values (240, 'VIRGIN ISLANDS, BRITISH','VG', 1, now(), 240);
insert into country (id, name, code, status, create_time, order_by) values (241, 'VIRGIN ISLANDS, U.S.','VI', 1, now(), 241);
insert into country (id, name, code, status, create_time, order_by) values (242, 'WALLIS AND FUTUNA','WF', 1, now(), 242);
insert into country (id, name, code, status, create_time, order_by) values (243, 'WESTERN SAHARA','EH', 1, now(), 243);
insert into country (id, name, code, status, create_time, order_by) values (244, 'YEMEN','YE', 1, now(), 244);
insert into country (id, name, code, status, create_time, order_by) values (245, 'ZAMBIA','ZM', 1, now(), 245);
insert into country (id, name, code, status, create_time, order_by) values (246, 'ZIMBABWE','ZW' , 1, now(), 246);

Marketing Setup
insert into marketing (id, name, status, marketing_type, create_time) values (0, 'Default', 1, 0, now());

Internal Tracking for External Referers
create table internal_referer (
    id integer not null,
    create_time timestamp not null,
    url varchar(2048) not null
);

create sequence internal_referer_seq;

create table external_ip (
    id integer not null,
    create_time timestamp not null,
    ip varchar(15) not null
);

create sequence external_ip_seq;

create table feature_toggle (
    id integer not null,
    create_time timestamp not null,
    status integer not null,
    name varchar(64) not null
);

create sequence feature_toggle_seq;

Need entries for Invite, Login, Users and MyTweetMark so far for features toggles.

Comment

create table comment (
    id integer not null,
    create_time timestamp not null,
    user_id integer not null,
    content_type varchar(50) not null,
    content_id integer not null,
    status integer not null,
    update_time timestamp not null
);


create sequence comment_seq;

alter table comment add primary key (id);

CREATE INDEX comment_id_index ON comment(id);
CREATE INDEX comment_content_type_index ON comment(content_type);
CREATE INDEX comment_content_id_index ON comment(content_id);


