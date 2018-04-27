/* get all users */
select
*
from
pegasus.users;

/* create user */
INSERT INTO `pegasus`.`users`
(`id`,
`username`,
`name`,
`email`,
`created_by`,
`creation_date`,
`status_code`,
`password`,
`password_salt`,
`modified_by`,
`modification_date`)
VALUES
(
'dd7262ad-f713-4d36-bcb3-fe9b5e75a74z',
'mary',
'Mary K.',
'mary@gmail.com',
'SYSTEM',
'2018-04-21 23:26:00',
'ENABLED',
'dummy',
'dummy',
'SYSTEM',
'2018-04-21 23:26:00'
);



/* clean table */
delete from pegasus.users;