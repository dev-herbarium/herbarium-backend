/* H2 - SPECIFIC DATA */

-- Insert roles
MERGE INTO roles (id_role, name) KEY(id_role) VALUES (1, 'ROLE_ADMIN');
MERGE INTO roles (id_role, name) KEY(id_role) VALUES (2, 'ROLE_USER');

-- Insert sample users 
MERGE INTO users (id_user, email, password, created_at, updated_at) KEY(id_user) VALUES (1, 'admin@herbarium.com', 'temp123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
MERGE INTO users (id_user, email, password, created_at, updated_at) KEY(id_user) VALUES (2, 'user@herbarium.com', 'temp123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Assign roles to users
MERGE INTO roles_users (role_id, user_id) KEY(role_id, user_id) VALUES (1, 1);
MERGE INTO roles_users (role_id, user_id) KEY(role_id, user_id) VALUES (2, 2);