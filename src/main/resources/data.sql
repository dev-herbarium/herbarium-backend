/* Roles - Use INSERT IGNORE to avoid duplicates */
INSERT IGNORE INTO roles (id_role, name) VALUES (1, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id_role, name) VALUES (2, 'ROLE_USER');

/* Users with email */
INSERT IGNORE INTO users (id_user, email, password, created_at, updated_at) 
VALUES (1, 'admin@herbarium.com', 'temp123', NOW(), NOW());

INSERT IGNORE INTO users (id_user, email, password, created_at, updated_at) 
VALUES (2, 'user@herbarium.com', 'temp123', NOW(), NOW());

/* Roles_users */
INSERT IGNORE INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT IGNORE INTO roles_users (role_id, user_id) VALUES (2, 2);