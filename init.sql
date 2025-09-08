CREATE DATABASE customers_db;
CREATE DATABASE accounts_db;

\c customers_db;

CREATE TABLE persons (
    id BIGSERIAL PRIMARY KEY,
    identification VARCHAR(100) unique not NULL,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(50),
    age INT,
    address VARCHAR(255),
    phone_number VARCHAR(50)
);

CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    person_id BIGINT NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_customer_person FOREIGN KEY (person_id) REFERENCES persons (id)
);

INSERT INTO persons (identification, name, gender, age, address, phone_number) VALUES
('098254785', 'Jose Lema', 'MALE', 30, 'Otavalo sn y principal', '098254785'),
('097548965', 'Marianela Montalvo', 'FEMALE', 25, 'Amazonas y NNUU', '097548965'),
('098874587', 'Juan Osorio', 'MALE', 40, '13 junio y Equinoccial', '098874587');

INSERT INTO customers (person_id, password, active)
SELECT id, '1234', TRUE FROM persons WHERE identification = '098254785';

INSERT INTO customers (person_id, password, active)
SELECT id, '5678', TRUE FROM persons WHERE identification = '097548965';

INSERT INTO customers (person_id, password, active)
SELECT id, '1245', TRUE FROM persons WHERE identification = '098874587';

\c accounts_db;

CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(50) NOT NULL unique not NULL,
    account_type VARCHAR(30) NOT NULL,
    initial_balance NUMERIC(19,2) NOT NULL,
    active BOOLEAN NOT NULL,
    customer_identification VARCHAR(50) NOT NULL
);

CREATE TABLE movements (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    movement_type VARCHAR(30) NOT NULL,
    initial_balance NUMERIC(19,2) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    issued_at TIMESTAMP NOT NULL
);

INSERT INTO accounts (account_number, account_type, initial_balance, active, customer_identification) VALUES
('478758', 'SAVINGS',   2000.00, TRUE, '098254785'),
('225487', 'CHECKING',  100.00, TRUE, '097548965'),
('495878', 'SAVINGS',      0.00, TRUE, '098874587'),
('496825', 'SAVINGS',    540.00, TRUE, '097548965'),
('585545', 'CHECKING', 1000.00, TRUE, '098254785');

INSERT INTO movements (account_id, movement_type, initial_balance, amount, issued_at)
SELECT id, 'WITHDRAWAL', 2000.00, 575.00, '2025-09-08 10:00:00'
FROM accounts WHERE account_number = '478758';

INSERT INTO movements (account_id, movement_type, initial_balance, amount, issued_at)
SELECT id, 'DEPOSIT', 100.00, 600.00, '2025-09-08 10:05:00'
FROM accounts WHERE account_number = '225487';

INSERT INTO movements (account_id, movement_type, initial_balance, amount, issued_at)
SELECT id, 'DEPOSIT', 0.00, 150.00, '2025-09-08 10:10:00'
FROM accounts WHERE account_number = '495878';

INSERT INTO movements (account_id, movement_type, initial_balance, amount, issued_at)
SELECT id, 'WITHDRAWAL', 540.00, 540.00, '2025-09-08 10:15:00'
FROM accounts WHERE account_number = '496825';