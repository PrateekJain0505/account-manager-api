DROP TABLE IF EXISTS acb_user;

CREATE TABLE acb_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    user_name VARCHAR(100) NOT NULL,
    address VARCHAR(250)
);

DROP TABLE IF EXISTS acb_account;

CREATE TABLE acb_account (
    account_id INT PRIMARY KEY,
    account_type VARCHAR(50) NOT NULL,
    account_balance DECIMAL(20,5),
    account_currency VARCHAR(3) NOT NULL DEFAULT 'HKD',
    user_id INT NOT NULL
);

DROP TABLE IF EXISTS acb_transaction_history;

CREATE TABLE acb_transaction_history (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_type VARCHAR(50) NOT NULL,
    account_id_from INT,
    account_id_to INT,
    user_id_from INT,
    user_id_to INT,
    transaction_date TIMESTAMP,
    transaction_amount DECIMAL(20,5)
);