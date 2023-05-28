--------------ROOM---------------
CREATE TABLE room_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/

CREATE TABLE room_type (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/
CREATE TABLE room (
    id           VARCHAR2(10) PRIMARY KEY,
    name         VARCHAR2(15) UNIQUE,
    capacity     NUMBER(5, 0),
    rental_price NUMBER,
    type_id      NUMBER(1, 0),
    area         NUMBER(5, 0),
    status_id    NUMBER(1, 0) DEFAULT 1,
    CONSTRAINT room_status_id_fk FOREIGN KEY ( status_id )
        REFERENCES room_status ( id ),
    CONSTRAINT room_type_id_fk FOREIGN KEY ( type_id )
        REFERENCES room_type ( id )
);
/

CREATE OR REPLACE TRIGGER roomid_increment BEFORE
    INSERT ON room
    FOR EACH ROW
BEGIN
    SELECT
        concat('P',
               lpad(COUNT(*) + 1,
                    4,
                    0))
    INTO :new.id
    FROM
        room;

END;
/

---------------TENANT---------------
CREATE TABLE tenant_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/
CREATE TABLE tenant (
    id           VARCHAR2(5) PRIMARY KEY,
    name         VARCHAR2(30),
    home_town    VARCHAR2(20),
    phone_number VARCHAR2(10) UNIQUE,
    password     VARCHAR2(255),
    email        VARCHAR2(255) UNIQUE,
    dob date not null,
    id_number    VARCHAR2(12) UNIQUE,
    status_id    NUMBER(1, 0) DEFAULT 1,
    CONSTRAINT tenant_status_id_fk FOREIGN KEY ( status_id )
        REFERENCES tenant_status ( id )
);
/

CREATE OR REPLACE TRIGGER tenantid_increment BEFORE
    INSERT ON tenant
    FOR EACH ROW
BEGIN
    SELECT
        concat('KH',
               lpad(COUNT(*) + 1,
                    3,
                    0))
    INTO :new.id
    FROM
        tenant;

END;
/

CREATE OR REPLACE TRIGGER create_password BEFORE
    INSERT ON tenant
    FOR EACH ROW
BEGIN
    :new.password := concat(concat(TO_CHAR(:NEW.DOB, 'DD'),TO_CHAR(:NEW.DOB, 'MM')),TO_CHAR(:NEW.DOB, 'YYYY'));
END;
/

CREATE TABLE support_ticket_type (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/

CREATE TABLE support_ticket_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/
---------------SUPPORT TICKET---------------
CREATE TABLE support_ticket (
    id            VARCHAR2(5) PRIMARY KEY,
    incident_time DATE,
    receive_time  DATE,
    room_id       VARCHAR2(5) NOT NULL,
    tenant_id     VARCHAR2(5) NOT NULL,
    description   VARCHAR2(50),
    status_id     NUMBER(1, 0) DEFAULT 0,
    CONSTRAINT support_ticket_room_id_fk FOREIGN KEY ( room_id )
        REFERENCES room ( id ),
    CONSTRAINT support_ticket_tenant_id_fk FOREIGN KEY ( tenant_id )
        REFERENCES tenant ( id ),
    CONSTRAINT support_ticket_status_id_fk FOREIGN KEY ( status_id )
        REFERENCES support_ticket_status ( id )
);
/
CREATE OR REPLACE TRIGGER ticketid_increment BEFORE
    INSERT ON support_ticket
    FOR EACH ROW
BEGIN
    SELECT
        concat('HT',
               lpad(COUNT(*) + 1,
                    3,
                    0))
    INTO :new.id
    FROM
        support_ticket;

END;
/

 ---------------INVOICE---------------
CREATE TABLE invoice (
    id          VARCHAR2(5),
    room_id     VARCHAR2(5) ,
    month       NUMBER ,
    year        NUMBER ,
    total_money NUMBER DEFAULT 0,
    status_id   NUMBER(1, 0) DEFAULT 0,
    created_date    DATE DEFAULT SYSDATE,
    modified_date   DATE DEFAULT NULL,
    CONSTRAINT invoice_room_id_fk FOREIGN KEY ( room_id )
        REFERENCES room ( id ),
    CONSTRAINT invoice_status_id_fk FOREIGN KEY ( status_id )
        REFERENCES invoice_status ( id ),
    CONSTRAINT invoice_pk PRIMARY KEY ( id ), 
    CONSTRAINT invoice_unique UNIQUE (room_id, month, year)
);
/

CREATE OR REPLACE TRIGGER invoiceid_increment BEFORE
    INSERT ON invoice
    FOR EACH ROW
BEGIN
    SELECT
        concat('HN',
               lpad(COUNT(*) + 1,
                    3,
                    0))
    INTO :new.id
    FROM
        invoice;

END;
/



CREATE TABLE invoice_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/

 ---------------DETAIL_INVOICE---------------
CREATE TABLE detail_invoice (
    invoice_id VARCHAR2(5),
    type_id    NUMBER(1, 0),
    quantity   NUMBER(3, 0),
    unit_price NUMBER,
    sum_money  NUMBER,
    CONSTRAINT detail_invoice_pk PRIMARY KEY ( invoice_id,
                                               type_id ),
    CONSTRAINT detail_invoice_invoice_id_fk FOREIGN KEY ( invoice_id )
        REFERENCES invoice ( id ),
    CONSTRAINT detail_invoice_type_id_fk FOREIGN KEY ( type_id )
        REFERENCES detail_invoice_type ( id )
);

CREATE TABLE detail_invoice_type (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255),
    unit VARCHAR2(255)
);
/

CREATE OR REPLACE TRIGGER insert_detail_invoice BEFORE
    INSERT ON detail_invoice
    FOR EACH ROW
BEGIN
    :new.sum_money := :new.quantity * :new.unit_price;
END;
/
 ---------------CONTRACT---------------
CREATE TABLE contract (
    id               VARCHAR2(5) PRIMARY KEY,
    start_date       DATE,
    end_date         DATE,
    price_per_period NUMBER,
    deposit          NUMBER,
    tenant_id        VARCHAR2(5),
    room_id          VARCHAR2(5),
    status_id        NUMBER(1, 0) DEFAULT 1,
    CONSTRAINT contract_tenant_id_fk FOREIGN KEY ( tenant_id )
        REFERENCES tenant ( id ),
    CONSTRAINT contract_room_id_fk FOREIGN KEY ( room_id )
        REFERENCES room ( id ),
    CONSTRAINT contract_status_id_fk FOREIGN KEY ( status_id )
        REFERENCES contract_status ( id )
);
/

CREATE TABLE contract_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/

CREATE OR REPLACE TRIGGER contractid_increment BEFORE
    INSERT ON contract
    FOR EACH ROW
BEGIN
    SELECT
        concat('HG',
               lpad(COUNT(*) + 1,
                    3,
                    0))
    INTO :new.id
    FROM
        contract;

END;
/
--DROP TRIGGER contractid_increment;
CREATE TABLE detail_contract_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/
 ---------------DETAIL_CONTRACT---------------
CREATE TABLE detail_contract (
    contract_id VARCHAR2(5),
    tenant_id   VARCHAR2(5),
    status_id   NUMBER(1, 0) DEFAULT 1,
    created_date    DATE DEFAULT SYSDATE,
    modified_date   DATE DEFAULT NULL,
    CONSTRAINT detail_contract_contract_id_fk FOREIGN KEY ( tenant_id )
        REFERENCES tenant ( id ),
    CONSTRAINT detail_contract_tenant_id_fk FOREIGN KEY ( contract_id )
        REFERENCES contract ( id ),
    CONSTRAINT detail_contract_status_id_fk FOREIGN KEY ( status_id )
        REFERENCES detail_contract_status ( id ),
    CONSTRAINT detail_contract_pk PRIMARY KEY ( contract_id,
                                                tenant_id )
);
/

 ---------------TRIGGER DELETE INVOICE---------------
CREATE OR REPLACE TRIGGER delete_detail_invoice BEFORE
    DELETE ON detail_invoice
    FOR EACH ROW
DECLARE
    temp_invoice_status_id invoice.status_id%TYPE;
BEGIN
    SELECT
        status_id
    INTO temp_invoice_status_id
    FROM
        invoice
    WHERE
        id = :old.invoice_id;

    IF temp_invoice_status_id = 1 THEN
        raise_application_error(-20111, 'Can not delete invoice which is paid');
    END IF;
END;
/
 ---------------TRIGGER INSERT REPRESENTATIVE WHO IS REPRESENTATIVE IN CONTRACT---------------
CREATE OR REPLACE TRIGGER insert_detail_contract BEFORE
    INSERT ON detail_contract
    FOR EACH ROW
DECLARE
    temp_contract_status_id contract.status_id%TYPE;
BEGIN
    SELECT
        status_id
    INTO temp_contract_status_id
    FROM
        contract
    WHERE
        id = :new.contract_id;

    IF temp_contract_status_id <> 1 THEN
        raise_application_error(-20111, 'Can not insert representative which conrtact is done');
    END IF;
END;
/
 ---------------TRIGGER INSERT TENANT IN DETAIL_CONTRACT---------------
CREATE OR REPLACE TRIGGER insert_roomates BEFORE
    INSERT ON detail_contract
    FOR EACH ROW
DECLARE
    temp_max_tenant   NUMBER;
    temp_count_tenant NUMBER;
    temp_room_id      room.id%TYPE;
BEGIN
    SELECT
        room_id
    INTO temp_room_id
    FROM
        contract
    WHERE
        id = :new.contract_id;

    SELECT
        capacity
    INTO temp_max_tenant
    FROM
        room
    WHERE
        id = temp_room_id;

    SELECT
        COUNT(*)
    INTO temp_count_tenant
    FROM
        detail_contract
    WHERE
        contract_id = :new.contract_id;

    IF ( ( temp_count_tenant + 1 ) = temp_max_tenant ) THEN
        raise_application_error(-20111, 'Room '
                                        || temp_room_id
                                        || ' is full. Can not insert tenant');
    END IF;

END;
/

 ---------------TRIGGER UPDATE STATUS WHEN ADD NEW CHILD INVOICE---------------
CREATE OR REPLACE TRIGGER update_invoice AFTER
    INSERT ON detail_invoice
    FOR EACH ROW
DECLARE
    temp_total_money NUMBER;
BEGIN
    SELECT
        SUM(sum_money)
    INTO temp_total_money
    FROM
        detail_invoice
    WHERE
        invoice_id = :new.invoice_id;

    UPDATE invoice
    SET
        total_money = temp_total_money,
        status_id = 0
    WHERE
        id = :new.invoice_id;

END;
/

CREATE TABLE account (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);
/
INSERT INTO account (username, password) VALUES('admin', 'admin');
/
commit;
