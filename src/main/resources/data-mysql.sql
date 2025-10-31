/* MySQL - SPECIFIC DATA */
-- Herbarium Database Initialization - MySQL Compatible using conditional execution

-- Remove existing data to avoid duplicates
DELETE FROM roles_users;
DELETE FROM users;
DELETE FROM roles;

-- Insert roles (MySQL uses INSERT IGNORE, H2 uses different approach)
INSERT IGNORE INTO roles (id_role, name) VALUES (1, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id_role, name) VALUES (2, 'ROLE_USER');

-- Insert sample users  
INSERT IGNORE INTO users (id_user, email, password, created_at, updated_at) 
VALUES (1, 'admin@herbarium.com', 'temp123', NOW(), NOW());

INSERT IGNORE INTO users (id_user, email, password, created_at, updated_at) 
VALUES (2, 'user@herbarium.com', 'temp123', NOW(), NOW());

-- Assign roles to users
INSERT IGNORE INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT IGNORE INTO roles_users (role_id, user_id) VALUES (2, 2);