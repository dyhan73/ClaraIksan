PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;

CREATE TABLE bank_account
(
    `bnk_id`      INTEGER         PRIMARY KEY    AUTOINCREMENT,
    `balance`     INTEGER         NOT NULL, 
    `last_date`   DATE            NOT NULL, 
    `id_name`     VARCHAR(45)     NOT NULL, 
    `act_no`      INTEGER         NULL, 
    `open_date`   DATE            NULL, 
    `what_id`     INTEGER         NULL, 
    `bank_type`   VARCHAR(45)     NULL, 
    `addr`        VARCHAR(255)    NULL, 
    `tel`         VARCHAR(45)     NULL, 
    `cat_name`    VARCHAR(45)     NULL, 
    `sort`        VARCHAR(45)     NULL, 
    `kind`        VARCHAR(45)     NULL, 
    `damdang`     VARCHAR(255)    NULL, 
    `prank`       INTEGER         NULL, 
    `pay_method`  INTEGER         NULL, 
    `pay_id_no`   INTEGER         NULL, 
    `tax_method`  INTEGER         NULL, 
    `norpt`       INTEGER         NULL, 
    `add1`        VARCHAR(255)    NULL, 
    `add2`        VARCHAR(255)    NULL, 
    `zip`         VARCHAR(45)     NULL, 
    `tel2`        VARCHAR(45)     NULL, 
    `fax`         VARCHAR(45)     NULL, 
    `item_use`    INTEGER         NULL
);


CREATE TABLE member
(
    `mbr_id`   INTEGER         PRIMARY KEY    AUTOINCREMENT,
    `cus_no`   VARCHAR(45)     NULL, 
    `name`     VARCHAR(255)    NOT NULL, 
    `company`  VARCHAR(255)    NULL, 
    `addr`     VARCHAR(255)    NULL, 
    `zip`      VARCHAR(45)     NULL, 
    `kind`     INTEGER         NULL, 
    `fdate`    DATE            NOT NULL, 
    `mark`     INTEGER         NULL, 
    `note`     VARCHAR(255)    NULL, 
    `remark`   VARCHAR(255)    NULL, 
    `etc_n1`   INTEGER         NULL, 
    `etc_n2`   INTEGER         NULL, 
    `etc_s1`   VARCHAR(45)     NULL, 
    `etc_s2`   VARCHAR(45)     NULL
);


CREATE TABLE category
(
    `cat_no`    INTEGER        PRIMARY KEY    AUTOINCREMENT,
    `cat_name`  VARCHAR(40)    NOT NULL
);


CREATE TABLE group
(
    `cls_no`    INTEGER        PRIMARY KEY    AUTOINCREMENT,
    `cat_no`    INTEGER        NOT NULL, 
    `cla_name`  VARCHAR(45)    NOT NULL, 
    `short`     VARCHAR(45)    NULL, 
    `easy`      INTEGER        NULL, 
    `cl_use`    INTEGER        NULL, 
    `tmp_str`   VARCHAR(45)    NULL, 
    `tmp_num`   INTEGER        NULL
);


CREATE TABLE member_detail
(
    `detail_id`  INTEGER         PRIMARY KEY    AUTOINCREMENT, 
    `mbr_id`     INTEGER         NOT NULL, 
    `info`       VARCHAR(255)    NOT NULL, 
    `type`       VARCHAR(45)     NOT NULL
);


CREATE TABLE account_book
(
    `ord_no`      INTEGER         PRIMARY KEY    AUTOINCREMENT,
    `bnk_id`      INTEGER         NOT NULL, 
    `what_id`     INTEGER         NOT NULL,
    `entry_date`  DATE            NOT NULL,
    `io`          VARCHAR(45)     NOT NULL, 
    `cat_no`      INTEGER         NOT NULL, 
    `cls_no`      INTEGER         NOT NULL, 
    `remark`      VARCHAR(255)    NOT NULL, 
    `item_price`  INTEGER         NOT NULL, 
    `price`       INTEGER         NOT NULL, 
    `tax_rate`    INTEGER         NOT NULL, 
    `tax_cost`    INTEGER         NOT NULL, 
    `balance`     INTEGER         NOT NULL, 
    `chk_no`      INTEGER         NOT NULL, 
    `relation`    INTEGER         NOT NULL, 
    `bnk_rid`     INTEGER         NOT NULL, 
    `what_rid`    INTEGER         NOT NULL, 
    `slip`        VARCHAR(45)     NULL, 
    `note`        VARCHAR(255)     NULL,
    `mark`        VARCHAR(45)     NULL, 
    `bank_no`     INTEGER         NULL, 
    `tmno`        INTEGER         NULL, 
    `pay_method`  INTEGER         NULL, 
    `tax_mark`    VARCHAR(45)     NULL, 
    `mbr_id`      INTEGER         NULL
);
CREATE INDEX ab_idx ON account_book (`entry_date`);


CREATE TABLE donation_receipt
(
    `rec_no`      INTEGER PRIMARY KEY    AUTOINCREMENT, 
    `mbr_id`      INTEGER NOT NULL, 
    `issue_date`  DATE    NOT NULL, 
    `detail_id`   INTEGER NOT NULL
);


CREATE TABLE group_detail
(
    `cls_no`  INTEGER        NOT NULL,
    `no`      INTEGER        NOT NULL, 
    `detail`  VARCHAR(45)    NOT NULL,
    PRIMARY KEY (`cls_no`, `no`)
);


CREATE TABLE donation_book
(
    `rec_no`      INTEGER         PRIMARY KEY    AUTOINCREMENT, 
    `mbr_id`      INTEGER         NOT NULL, 
    `entry_date`  DATE            NOT NULL,
    `io`          INTEGER         NULL,
    `part_no`     INTEGER         NULL, 
    `qty`         INTEGER         NULL, 
    `unit_price`  INTEGER         NULL, 
    `price`       INTEGER         NOT NULL, 
    `item_name`   VARCHAR(255)    NULL, 
    `ipkum`       INTEGER         NULL, 
    `subject`     VARCHAR(255)    NOT NULL
);


CREATE TABLE receipt_map
(
    `rec_no`           INTEGER    PRIMARY KEY    AUTOINCREMENT, 
    `receipt_id`       INTEGER    NOT NULL, 
    `account_book_id`  INTEGER    NOT NULL
);

COMMIT;
