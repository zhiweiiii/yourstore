
create table `key_info`  (
  `id` bigint(20) primary key not null,
  `prefix_key` int(8) null default null,
  `key_word` int(16) null default null,
  `content` text ,
  `is_using` tinyint(1) null default null,
  `flag` int(8) null default null,

);

create table `note`  (
  `id` bigint(16) primary key  not null,
  `title` varchar(255) ,
  `content` text ,
  `time` datetime(0),
  `format` varchar(20),

) ;
