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

CREATE OR REPLACE TRIGGER TG1_roomid_increment BEFORE    
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

CREATE OR REPLACE TRIGGER TG2_tenantid_increment BEFORE
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


---------------SUPPORT TICKET---------------
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
CREATE OR REPLACE TRIGGER TG3_ticketid_increment BEFORE
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
CREATE TABLE invoice_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/

CREATE TABLE invoice (
    id          VARCHAR2(5),
    room_id     VARCHAR2(5) ,
    month       NUMBER ,
    year        NUMBER ,
    total_money NUMBER DEFAULT 0,
    status_id   NUMBER(1, 0) DEFAULT 0,
    created_date    DATE DEFAULT SYSDATE,
    modified_date   DATE DEFAULT NULL,
    money_paid  NUMBER DEFAULT 0,
    CONSTRAINT invoice_room_id_fk FOREIGN KEY ( room_id )
        REFERENCES room ( id ),
    CONSTRAINT invoice_status_id_fk FOREIGN KEY ( status_id )
        REFERENCES invoice_status ( id ),
    CONSTRAINT invoice_pk PRIMARY KEY ( id ), 
    CONSTRAINT invoice_unique UNIQUE (room_id, month, year)
);
/

CREATE OR REPLACE TRIGGER TG4_invoiceid_increment BEFORE
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




 ---------------DETAIL_INVOICE---------------
 
CREATE TABLE detail_invoice_type (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255),
    unit VARCHAR2(255)
);
/

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


 ---------------CONTRACT---------------
CREATE TABLE contract_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/
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



CREATE OR REPLACE TRIGGER TG5_contractid_increment BEFORE
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

---------------DETAIL_CONTRACT---------------
CREATE TABLE detail_contract_status (
    id   NUMBER(1, 0) PRIMARY KEY,
    name VARCHAR2(255)
);
/
 
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
-----------------------------ACCOUNT------------------------------
CREATE TABLE account (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);
/
INSERT INTO account (username, password) VALUES('admin', 'admin');
/
---------------------------------------------TRIGGER-----------------------------------------------
--#######-- Trigger auto create password for a tenant
CREATE OR REPLACE TRIGGER Ten_create_password_Tenant BEFORE
    INSERT ON tenant
    FOR EACH ROW
BEGIN
    :new.password := concat(concat(TO_CHAR(:NEW.DOB, 'DD'),TO_CHAR(:NEW.DOB, 'MM')),TO_CHAR(:NEW.DOB, 'YYYY'));
END;
/
--######-- Trigger update total money when insert a new invoice
create or replace trigger inv_update_total BEFORE   
insert on invoice
for each row
declare
    price number;
begin
    select price_per_period into price
    from contract
    where room_id = :new.room_id and status_id = 1;
    :new.total_money := price;
end;
/
--######-- Trigger insert a detail invoice with type is rent house when insert invoice
create or replace trigger inv_insert_detail AFTER  
insert on invoice
for each row
declare
    price number;
    id varchar2(5);
    r_id varchar2(5);
begin
    r_id := :new.room_id;
    select price_per_period into price
    from contract
    where room_id = r_id and status_id = 1;
    id := :NEW.id;
    insert into DETAIL_INVOICE(INVOICE_ID, type_id,quantity,unit_price) VALUES(id, 0, 1, price);
end;
/
--######-- Trigger update money_paid when update status of invoice 
CREATE OR REPLACE TRIGGER inv_UPDATE_MONEY_PAID BEFORE 
UPDATE ON INVOICE
FOR EACH ROW
BEGIN
    if :new.Status_id = 1 then
        :new.money_paid := :new.total_money;
    end if;
END;
/
--######-- Trigger calculate sum_money base on quantity and unit_price when insert a detail invoice
CREATE OR REPLACE TRIGGER dinv_cal_sum BEFORE
    INSERT ON detail_invoice
    FOR EACH ROW
BEGIN
    :new.sum_money := :new.quantity * :new.unit_price;
END;
/
--#####-- Update total money of invoice when insert a detail invoice
create or replace TRIGGER dinv_update_total_ins after  
INSERT ON detail_invoice   
FOR EACH ROW
BEGIN
    IF :new.type_id <> 0 then
        update invoice
        set total_money = total_money + :new.sum_money,
            Status_id = 0
        where id = :new.invoice_id;
    end if;
END;
/
--#####-- Update total money of invoice when update a detail invoice
CREATE OR REPLACE TRIGGER dinv_update_total_up after 
UPDATE ON detail_invoice   
FOR EACH ROW    
DECLARE
    moneyy number;
BEGIN
    moneyy := :new.sum_money - :old.sum_money;
    
    update invoice
    set total_money = total_money + moneyy
    where id = :new.invoice_id;
END;
/
--#####-- Update total money of invoice when delete a detail invoice
CREATE OR REPLACE TRIGGER dinv_update_detail_del after   
DELETE ON detail_invoice   
FOR EACH ROW    
BEGIN    
    update invoice
    set total_money = total_money - :old.sum_money
    where id = :old.invoice_id;
END;
/
--#####-- Trigger guarantee: detail invoice deleted or updated when invoice in status unpaid
CREATE OR REPLACE TRIGGER dinv_check_delete_update_detail BEFORE
    DELETE OR UPDATE ON detail_invoice
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
--#####-- Trigger guarantee: insert detail contract when contract's status is can use 
CREATE OR REPLACE TRIGGER dcon_check_status_contract BEFORE
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
--#####-- Trigger guarantee: Capacity of a room when insert a tenant into contract(insert detail contract)
CREATE OR REPLACE TRIGGER dcon_check_capacity BEFORE
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
commit;
