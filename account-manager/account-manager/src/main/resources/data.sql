INSERT INTO acb_user(first_name, last_name, user_name) values ('Prateek', 'Jain', 'pjtesting');
INSERT INTO acb_user(first_name, last_name, user_name) values ('Prateek', 'Jain', 'pjtesting1');

INSERT INTO acb_account(account_id, account_type, account_balance, user_id)
values(12345678, 'SAVING', 1000000, (select user_id from acb_user where user_name = 'pjtesting'));

INSERT INTO acb_account(account_id, account_type, account_balance, user_id)
values(88888888, 'SAVING', 1000000, (select user_id from acb_user where user_name = 'pjtesting1'));

commit;