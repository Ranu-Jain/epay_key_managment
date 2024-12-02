-- liquibase formatted sql

-- changeset bhushan:0
CREATE TABLE key_management
(
  id RAW(16) PRIMARY KEY NOT NULL,
  merchant_id varchar2(16),
  key_type varchar2(10)NOT NULL,
  encrypted_mek VARCHAR2(100),
  encrypted_kek VARCHAR2(100),
  expiry_time NUMBER,
  key_encryption_algo VARCHAR2(20) NOT NULL,
  status VARCHAR2(20) NOT NULL,
  created_date NUMBER NOT NULL,
  updated_date NUMBER NOT NULL,
  created_by varchar2(20),
  updated_by varchar2(20)
  CONSTRAINT unique_encrypted_keys UNIQUE(encrypted_mek,encrypted_kek)
);



