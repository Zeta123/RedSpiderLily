create table book(
bookName varchar(100),
bookAuthor varchar(100),
mainClassification varchar(100),
subClassification varchar(100),
readTimes int,
storePath varchar(500),
recentDate datetime,
primary key(storePath)
)