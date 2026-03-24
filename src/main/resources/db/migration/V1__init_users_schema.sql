-- Create schema
CREATE SCHEMA IF NOT EXISTS users;

-- User roles table
CREATE TABLE users.roles (
	id SMALLINT PRIMARY KEY,
	role VARCHAR(50) NOT NULL UNIQUE
);

COMMENT ON COLUMN users.roles.role 
	IS 'Predefined user role; e.g. ROLE_ADMIN, ROLE_USER';

-- initializing predefined roles
INSERT INTO users.roles (id, role) VALUES
	(0, 'ROLE_ADMIN'),
	(1, 'ROLE_USER');

-- User status table
CREATE TABLE users.status (
	id SMALLINT PRIMARY KEY,
	status VARCHAR(50) NOT NULL UNIQUE
);

COMMENT ON COLUMN users.status.status 
	IS 'Predefined user status; e.g. ACTIVE, LOCKED, ARCHIVED';

-- predefined status values
INSERT INTO users.status (id, status) VALUES
	(0, 'ACTIVE'), 
	(1, 'LOCKED'), 
	(2, 'ARCHIVED');

-- CustomerUser table
CREATE TABLE users.customer_user (
    id BIGSERIAL PRIMARY KEY,
    external_id UUID NOT NULL UNIQUE,
    username VARCHAR(254) NOT NULL UNIQUE,
    "password" VARCHAR(254) NOT NULL,
    email VARCHAR(254) NOT NULL UNIQUE,
    role_id SMALLINT NOT NULL,
    status_id SMALLINT NOT NULL,
    created TIMESTAMPTZ DEFAULT NOW(),
    address_line1 TEXT NOT NULL,
    address_line2 TEXT
);

-- Indexes for faster lookup
DROP INDEX IF EXISTS idx_customer_user_name;
CREATE INDEX idx_customer_user_name ON users.customer_user(username);
DROP INDEX IF EXISTS idx_customer_user_email;
CREATE INDEX idx_customer_user_email ON users.customer_user(email);
DROP INDEX IF EXISTS idx_customer_user_external_id;
CREATE INDEX idx_customer_user_external_id ON users.customer_user(external_id);

-- comments
COMMENT ON COLUMN users.customer_user.external_id 
	IS 'External, db independent, unique user identifier (UUID)';
COMMENT ON COLUMN users.customer_user.username 
	IS 'Customer username (unique)';
COMMENT ON COLUMN users.customer_user.email 
	IS 'Customer''s email (unique)';
COMMENT ON COLUMN users.customer_user.address_line1 
	IS 'Shipping address';
COMMENT ON COLUMN users.customer_user.address_line2 
	IS 'Shipping address, part 2, if necessary';

-- predefined ADMIN user
INSERT INTO users.customer_user
(	id, 
	external_id, 
	username,
	password, 
	email, 
	role_id, 
	status_id, 
	created, 
	address_line1)
VALUES
(	0, --reserved, outside sequence 
	'00000000-0000-0000-0000-000000000000', 
	'ADMIN',
	'$2a$10$YyyI0uCSyQij19uKzs9.yOzbMZxZVld2VGv2/VPYsNerWvqUKrBvm',
	'admin@order.processing.com', 
	0, -- admin role 
	0, -- active status
	now(), 
	'3401 Hillview Avenue, Palo Alto, CA 94304, USA'
);

COMMIT;
