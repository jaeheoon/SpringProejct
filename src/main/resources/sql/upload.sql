#MySQL
create table upload (
	seq INTEGER primary key auto_increment,
	imageName varchar(50),
	imageContent varchar(4000),
	imageFileName varchar(100) not null,
	imageOriginalFileName varchar(100) not null
);