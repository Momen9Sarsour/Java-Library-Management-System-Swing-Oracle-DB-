CREATE TABLE users (
    u_id            VARCHAR2(15) NOT NULL,
    u_first_name    VARCHAR2(25) NOT NULL,
    u_last_name     VARCHAR2(25) NOT NULL,
    u_password      VARCHAR2(30) NOT NULL,
    u_phone_number  VARCHAR2(10) NOT NULL,
    u_regis_date    DATE NOT NULL,
    CONSTRAINT u_id_pk PRIMARY KEY ( u_id ),
    CONSTRAINT u_phone_number_uq UNIQUE ( u_phone_number )
);

CREATE TABLE students (
    s_id            VARCHAR2(15) NOT NULL,
    s_first_name    VARCHAR2(25) NOT NULL,
    s_last_name     VARCHAR2(25) NOT NULL,
    s_phone_number  VARCHAR2(10) NOT NULL,
    s_major         VARCHAR2(50) NOT NULL,
    CONSTRAINT s_id_pk PRIMARY KEY ( s_id ),
    CONSTRAINT s_phone_number_uq UNIQUE ( s_phone_number )
);

CREATE TABLE staffs (
    st_id            VARCHAR2(15) NOT NULL,
    st_first_name    VARCHAR2(25) NOT NULL,
    st_last_name     VARCHAR2(25) NOT NULL,
    st_phone_number  VARCHAR2(10) NOT NULL,
    st_department    VARCHAR2(50) NOT NULL,
    CONSTRAINT st_id_pk PRIMARY KEY ( st_id ),
    CONSTRAINT st_phone_number_uq UNIQUE ( st_phone_number )
);

CREATE TABLE books (
    b_id                   VARCHAR2(15) NOT NULL,
    b_title                VARCHAR2(100) NOT NULL,
    b_publisher            VARCHAR2(50) NOT NULL,
    b_author               VARCHAR2(50) NOT NULL,
    b_year_of_publication  DATE NOT NULL,
    b_number_of_copies     NUMBER(5) NOT NULL,
    b_financil_value       NUMBER(6, 5) NOT NULL,
    CONSTRAINT b_id_pk PRIMARY KEY ( b_id ),
    CONSTRAINT b_title_uq UNIQUE ( b_title )
);

CREATE TABLE borrow_stdudent (
    u_id                VARCHAR2(15) NOT NULL,
    b_id                VARCHAR2(15) NOT NULL,
    s_id                VARCHAR2(15) NOT NULL,
    brrrowing_date      DATE NOT NULL,
    brrrowing_end_date  DATE NOT NULL,
    CONSTRAINT u_id_fk FOREIGN KEY ( u_id )
        REFERENCES users ( u_id ),
    CONSTRAINT b_id_fk FOREIGN KEY ( b_id )
        REFERENCES books ( b_id ),
    CONSTRAINT s_id_fk FOREIGN KEY ( s_id )
        REFERENCES students ( s_id )
);

CREATE TABLE borrow_staff (
    u_id                VARCHAR2(15) NOT NULL,
    b_id                VARCHAR2(15) NOT NULL,
    st_id               VARCHAR2(15) NOT NULL,
    brrrowing_date      DATE NOT NULL,
    brrrowing_end_date  DATE NOT NULL,
    CONSTRAINT ust_id_fk FOREIGN KEY ( u_id )
        REFERENCES users ( u_id ),
    CONSTRAINT bst_id_fk FOREIGN KEY ( b_id )
        REFERENCES books ( b_id ),
    CONSTRAINT st_id_fk FOREIGN KEY ( st_id )
        REFERENCES staffs ( st_id )
);

CREATE TABLE s_returned_books (
    u_id         VARCHAR2(15) NOT NULL,
    b_id         VARCHAR2(15) NOT NULL,
    s_id         VARCHAR2(15) NOT NULL,
    financil     NUMBER(6, 5) NOT NULL,
    late_days    NUMBER(5) NOT NULL,
    return_date  DATE NOT NULL,
    CONSTRAINT usr_id_fk FOREIGN KEY ( u_id )
        REFERENCES users ( u_id ),
    CONSTRAINT bsr_id_fk FOREIGN KEY ( b_id )
        REFERENCES books ( b_id ),
    CONSTRAINT sr_id_fk FOREIGN KEY ( s_id )
        REFERENCES students ( s_id )
);

CREATE TABLE st_returned_books (
    u_id         VARCHAR2(15) NOT NULL,
    b_id         VARCHAR2(15) NOT NULL,
    st_id        VARCHAR2(15) NOT NULL,
    financil     NUMBER(6, 5) NOT NULL,
    late_days    NUMBER(5) NOT NULL,
    return_date  DATE NOT NULL,
    CONSTRAINT ustr_id_fk FOREIGN KEY ( u_id )
        REFERENCES users ( u_id ),
    CONSTRAINT bstr_id_fk FOREIGN KEY ( b_id )
        REFERENCES books ( b_id ),
    CONSTRAINT str_id_fk FOREIGN KEY ( st_id )
        REFERENCES staffs ( st_id )
);

INSERT INTO users (
    u_id,
    u_first_name,
    u_last_name,
    u_password,
    u_phone_number
) VALUES (
    '1949011040',
    'Ameer',
    'Alhopi',
    'admin25',
    '0597865754'
);

INSERT INTO books (
    b_id,
    b_title,
    b_publisher,
    b_author,
    b_year_of_publication,
    b_number_of_copies,
    b_financil_value
) VALUES (
    2957762,
    'Tokyo',
    'King',
    'Jehad Ayman',
    TO_DATE('2000', 'yyyy'),
    6,
    3.5
);

ALTER TABLE users MODIFY
    u_regis_date DEFAULT sysdate;

ALTER TABLE st_returned_books MODIFY
    return_date DEFAULT sysdate;
    
ALTER TABLE borrow_staff MODIFY
    BRRROWING_END_DATE DEFAULT sysdate+3;
    
ALTER TABLE borrow_stdudent MODIFY
    BRRROWING_END_DATE DEFAULT sysdate+3;
    
ALTER TABLE s_returned_books MODIFY
    return_date DEFAULT sysdate;
    
ALTER TABLE s_returned_books MODIFY
    return_date DEFAULT sysdate;




create or replace NONEDITIONABLE FUNCTION get_val_s (
    sd_id    VARCHAR2,
    bo_id    VARCHAR2,
    user_id  VARCHAR2
) RETURN NUMBER IS
    date_one  DATE;
    date_tow  DATE := sysdate;
    date_val  NUMBER;
    financil  NUMBER;
    latedate  NUMBER;
BEGIN
    SELECT
        brrrowing_end_date
    INTO date_one
    FROM
        borrow_stdudent
    WHERE
            s_id = sd_id
        AND b_id = bo_id;

    SELECT
        b_financil_value
    INTO date_val
    FROM
        books
    WHERE
        b_id = bo_id;

    latedate := trunc(date_tow - date_one);
    financil := latedate * date_val;
    IF financil < 0 THEN
        financil := 0.0;
        latedate := 0;
    END IF;

    INSERT INTO s_returned_books (
        u_id,
        b_id,
        s_id,
        financil,
        late_days
    ) VALUES (
        user_id,
        bo_id,
        sd_id,
        financil,
        latedate
    );
    DELETE FROM borrow_stdudent
    WHERE
            s_id = sd_id
        AND b_id = bo_id;

    UPDATE books
    SET
        b_number_of_copies = ( b_number_of_copies + 1 )
    WHERE
        b_id = bo_id;

    RETURN financil;
END;
    
/*fun */

create or replace NONEDITIONABLE FUNCTION get_val_st (
    stf_id   VARCHAR2,
    bo_id    VARCHAR2,
    user_id  VARCHAR2
) RETURN NUMBER IS
    date_one  DATE;
    date_tow  DATE := sysdate;
    date_val  NUMBER;
    financil  NUMBER;
    latedate  NUMBER;
BEGIN
    SELECT
        brrrowing_end_date
    INTO date_one
    FROM
        borrow_staff
    WHERE
            st_id = stf_id
        AND b_id = bo_id;

    SELECT
        b_financil_value
    INTO date_val
    FROM
        books
    WHERE
        b_id = bo_id;

    latedate := trunc(date_tow - date_one);
    financil := latedate * date_val;
    IF financil < 0 THEN
        financil := 0.0;
        latedate := 0;
    END IF;

    INSERT INTO st_returned_books (
        u_id,
        b_id,
        st_id,
        financil,
        late_days
    ) VALUES (
        user_id,
        bo_id,
        stf_id,
        financil,
        latedate
    );

    DELETE FROM borrow_staff
    WHERE
            st_id = stf_id
        AND b_id = bo_id;

    UPDATE books
    SET
        b_number_of_copies = ( b_number_of_copies + 1 )
    WHERE
        b_id = bo_id;

    RETURN financil;
END;  
    
    set serveroutput on;
execute dbms_output.put_line(get_val_st('1020','2031'));

    select * from s_returned_books union all select * from st_returned_books;
    
    select * from s_returned_books where s_id = '1949011007' union all select * from st_returned_books where st_id = '1949011007';
    
    
    delete ST_RETURNED_BOOKS;
    delete S_RETURNED_BOOKS;
    delete BORROW_STDUDENT;
    delete BORROW_STAFF;
    delete staffs;
    delete students;
    delete books;
    delete users;
    

    
    