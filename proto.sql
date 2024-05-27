use diagnomind;

# insert into role values (1, 1), (2, 2), (3, 0);
#create table user (
#	userid 					bigint auto_increment,
#    izena 					varchar(20),
#    abizena 				varchar(20),
#    email 					varchar(255),
#    password 				varchar(255),	
#    jaiotze_data			timestamp,
#    role_roleid				bigint,
#    constraint USER_PK primary key (userid),
#    constraint USER_ROLE_PK foreign key (role_roleid) references role (roleid));

# delete from user where izena='admin';
# delete from user where izena='user';
# insert into enpresa value (3, 'GUGUL');
ALTER TABLE  user
DROP COLUMN izena;
DROP COLUMN abizena;
insert into enpresa value (1, 'admin@osb.com', '$2a$12$/B1jwyTObAv4aBRKLhCVvevvWpE819gwrzjxkuk8uvyLcp2PJjQVO', 1);
insert into enpresa value (2, 'user@osb.com', '$2a$12$KiMkin9zRzt83Y4AHTxiVOxVQu5PHPOVBKdfUaNBdVcD6hF8TQx6K', 2);
# insert into user value (2, 'admin', 'admin', 'admin@osb.com', '$2a$12$/B1jwyTObAv4aBRKLhCVvevvWpE819gwrzjxkuk8uvyLcp2PJjQVO', "2023-05-10 15:30:45", 1);
# insert into user value (4, 'user', 'user', 'user@osb.com', '$2a$12$KiMkin9zRzt83Y4AHTxiVOxVQu5PHPOVBKdfUaNBdVcD6hF8TQx6K', "2023-05-10 15:30:45", 2);
# insert into role values (0, 1), (1, 2);
# SET foreign_key_checks = 0;
# delete from role where roleid=2;

select * from user;
select * from enpresa;
select * from role;
#SET foreign_key_checks = 0;
# drop table erabiltzaile;