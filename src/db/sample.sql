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
`status_code`)
VALUES
(
'dd7262ad-f713-4d36-bcb3-fe9b5e75a74d',
'mary',
'Mary K.',
'mary@gmail.com',
'SYSTEM',
'2018-04-21 23:26:00',
'ENABLED'
);
