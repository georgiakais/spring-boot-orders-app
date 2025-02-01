INSERT INTO users (username, password, role) VALUES
('admin', '$2a$10$iawRIG8kOksY6bK2GCcEXeaSwgYZxMot9TtvsX4v2xd2DOEudnBl6', 'ADMIN'),  -- password: admin123
('user1', '$2a$10$8osNaPvO7O/XuxCOTT8Ac.05xrRXIkm7iqoXDkRHEdpqN37G/NLZm', 'USER'),  -- password: user123
('user2', '$2a$10$8osNaPvO7O/XuxCOTT8Ac.05xrRXIkm7iqoXDkRHEdpqN37G/NLZm', 'USER');  -- password: user123

-- Orders for user1
INSERT INTO orders (user_id, product_name, amount, quantity, description, created_at)
SELECT id, 'Product 1', 50.00, 2, 'This is product 1', current_timestamp
FROM users WHERE username = 'user1' LIMIT 10;

-- Orders for user2
INSERT INTO orders (user_id, product_name, amount, quantity, description, created_at)
SELECT id, 'Product 2', 30.00, 3, 'This is product 2', current_timestamp
FROM users WHERE username = 'user2' LIMIT 10;
