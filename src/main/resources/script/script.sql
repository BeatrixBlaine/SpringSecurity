CREATE DATABASE db_bank;

USE db_bank;

CREATE TABLE customer (
                          customer_id int NOT NULL AUTO_INCREMENT,
                          name varchar(50) NOT NULL,
                          email varchar(50) NOT NULL,
                          mobile_number varchar(20) NOT NULL,
                          pwd varchar(500) NOT NULL,
                          role varchar(100) NOT NULL,
                          create_dt date DEFAULT NULL,
                          PRIMARY KEY (customer_id)
);

INSERT INTO customer (name, email, mobile_number, pwd, role, create_dt)
VALUES (
           'Irsyad',
           'muhammadirsyadibrahim21@gmail.com',
           '082127616536',
           '{bcrypt}$2a$12$bdSchDr1m7/g3HnDLSfH3uW7HKU3vlsvoFdwemdq6IsvAX4ffbgO2',
           'admin',
           CURDATE()
       );

CREATE TABLE accounts (
                          customer_id int NOT NULL,
                          account_number int NOT NULL,
                          account_type varchar(100) NOT NULL,
                          branch_address varchar(200) NOT NULL,
                          create_dt date NOT NULL,
                          PRIMARY KEY (account_number),
                          KEY customer_id (customer_id),
                          CONSTRAINT customer_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

CREATE TABLE account_transactions (
                                      transaction_Id varchar(200) NOT NULL,
                                      account_number int NOT NULL,
                                      customer_id int NOT NULL,
                                      transaction_dt date NOT NULL,
                                      transaction_summary varchar(200) NOT NULL,
                                      transaction_type varchar(100) NOT NULL,
                                      transaction_amt int NOT NULL,
                                      closing_balance int NOT NULL,
                                      create_dt int NOT NULL,
                                      PRIMARY KEY (transaction_id),
                                      KEY customer_id (customer_id),
                                      KEY account_number (account_number),
                                      CONSTRAINT accounts_ibfk_2 FOREIGN KEY (account_number) REFERENCES accounts (account_number) ON DELETE CASCADE,
                                      CONSTRAINT acct_user_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO account_transactions (transaction_id, account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt)
VALUES(
          UUID(),
          0860781263,
          1,
          DATE_SUB(CURDATE(), INTERVAL 7 DAY),
          'Coffee shop',
          'Withdrawal',
          30,
          34500,
          DATE_SUB(CURDATE(), INTERVAL 7 DAY)
      );


INSERT INTO account_transactions (transaction_id, account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt)
VALUES(
          UUID(),
          0860781263,
          1,
          DATE_SUB(CURDATE(), INTERVAL 6 DAY),
          'Uber',
          'Withdrawal',
          100,
          34400,
          DATE_SUB(CURDATE(), INTERVAL 6 DAY)
      );


INSERT INTO account_transactions (transaction_id, account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt)
VALUES(
          UUID(),
          0860781263,
          1,
          DATE_SUB(CURDATE(), INTERVAL 5 DAY),
          'Self Deposit',
          'Deposit',
          600,
          35000,
          DATE_SUB(CURDATE(), INTERVAL 5 DAY)
      );

INSERT INTO account_transactions (transaction_id, account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt)
VALUES(
          UUID(),
          0860781263,
          1,
          DATE_SUB(CURDATE(), INTERVAL 4 DAY),
          'Ebay',
          'Withdrawal',
          1000,
          34000,
          DATE_SUB(CURDATE(), INTERVAL 4 DAY)
      );

CREATE TABLE loans (
                       loan_number int NOT NULL AUTO_INCREMENT,
                       customer_id int NOT NULL,
                       start_dt date NOT NULL,
                       loan_type varchar(100) NOT NULL,
                       total_loan int NOT NULL,
                       amount_paid int NOT NULL,
                       outstanding_amount int NOT NULL,
                       create_dt date DEFAULT NULL,
                       PRIMARY KEY (loan_number),
                       KEY customer_id (customer_id),
                       CONSTRAINT loan_customer_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
VALUES (1, '2026-10-13', 'Home', 200000, 50000, 150000, '2026-10-13');

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
VALUES (1, '2026-09-13', 'Vehicle', 40000, 20000, 20000, '2026-09-13');

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
VALUES (1, '2026-08-11', 'Home', 200000, 50000, 150000, '2026-08-11');

CREATE TABLE cards (
                       card_id int NOT NULL AUTO_INCREMENT,
                       card_number varchar(100) NOT NULL,
                       customer_id int NOT NULL,
                       card_type varchar(100) NOT NULL,
                       total_limit int NOT NULL,
                       amount_used int NOT NULL,
                       available_amount int NOT NULL,
                       create_dt date DEFAULT NULL,
                       PRIMARY KEY (card_id),
                       KEY customer_id (customer_id),
                       CONSTRAINT card_customer_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
VALUES (
           '111122223333',
           1,
           'Credit',
           10000,
           500,
           9500,
           CURDATE()
       );

INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
VALUES (
           '333344445555',
           1,
           'Debit',
           50000,
           45000,
           5000,
           CURDATE()
       );

CREATE TABLE notice_details (
                                notice_id int NOT NULL AUTO_INCREMENT,
                                notice_summary varchar(200) NOT NULL,
                                notice_details varchar(500) NOT NULL,
                                notic_beg_dt date NOT NULL,
                                notic_end_dt date DEFAULT NULL,
                                create_dt date DEFAULT NULL,
                                update_dt date DEFAULT NULL,
                                PRIMARY KEY (notice_id)
);

INSERT INTO notice_details (notice_summary, notice_details, notic_beg_dt, notic_end_dt, create_dt, update_dt)
VALUES (
           'Home Loan Interest rates reduced',
           'Home loan interest rates are reduced as per the government guidelines',
           CURDATE() - INTERVAL 30 DAY,
           CURDATE() + INTERVAL 30 DAY,
           CURDATE(),
           null
       );

INSERT INTO notice_details (notice_summary, notice_details, notic_beg_dt, notic_end_dt, create_dt, update_dt)
VALUES (
           'COVID-19 Insurance',
           'Launched an insurance policy which will cover COVID-19 expenses. Please reach out the closest branch for registration',
           CURDATE() - INTERVAL 30 DAY,
           CURDATE() + INTERVAL 30 DAY,
           CURDATE(),
           null
       );

CREATE TABLE contact_messages (
                                  contact_id varchar(50) NOT NULL,
                                  contact_name varchar(50) NOT NULL,
                                  contact_email varchar(50) NOT NULL,
                                  subject varchar(500) NOT NULL,
                                  message varchar(2000) NOT NULL,
                                  create_dt date DEFAULT NULL,
                                  PRIMARY KEY (contact_id)
);







