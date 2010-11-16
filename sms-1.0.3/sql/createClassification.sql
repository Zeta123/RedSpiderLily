create table classification(
mainClassification varchar(100) not null,
subClassification varchar(100) not null,
primary key(mainClassification,subClassification))