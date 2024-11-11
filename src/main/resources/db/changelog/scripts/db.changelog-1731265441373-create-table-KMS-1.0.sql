CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE key_management
(
  ID UUID NOT NULL DEFAULT UUID_GENERATE_V4(),
  merchant_id CHARACTER(255) NOT NULL,
  key_type CHARACTER(10)NOT NULL,
  encrypted_mek CHARACTER(1000),
  encrypted_kek CHARACTER(1000),
  expiry_time TIMESTAMP,
  key_encryption_algo CHARACTER(100) NOT NULL,
  status CHARACTER(20) NOT NULL,
  created_at NUMERIC(18) NOT NULL,
  updated_at NUMERIC(18) NOT NULL,
  created_by CHARACTER(200),
  updated_by CHARACTER(200)
);

