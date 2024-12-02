-- liquibase formatted sql
-- changeset bhushan:1
CREATE TABLE key_expiry
(
    id RAW(16) PRIMARY KEY NOT NULL,
    key_id RAW(16) NOT NULL,
    expired_at number(16),
    expiry_action VARCHAR2(20),
    status VARCHAR2(20),
    created_date number(16),
    created_by VARCHAR2(20)
);


CREATE TABLE key_audit
(
    id RAW(16) PRIMARY KEY NOT NULL,
    key_id RAW(16) NOT NULL,
    action_type VARCHAR2(20),
    action_details VARCHAR2(20)
);