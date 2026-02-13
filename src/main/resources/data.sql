-- SQL generado por Gemini
-- 1. Categorías (mínimo 4) [cite: 167]
INSERT INTO categories (name, description) VALUES ('Electrónica', 'Gadgets y tecnología');
INSERT INTO categories (name, description) VALUES ('Hogar', 'Cosas para la casa');
INSERT INTO categories (name, description) VALUES ('Deportes', 'Equipamiento deportivo');
INSERT INTO categories (name, description) VALUES ('Libros', 'Lectura de todo tipo');

-- 2. Productos (mínimo 20) [cite: 168]
-- Aquí insertarías 20 productos siguiendo el esquema. Ejemplo:
INSERT INTO products (name, description, price, stock, sku) VALUES ('Smartphone X', 'Gama alta', 999.0, 50, 'SKU001');
INSERT INTO products (name, description, price, stock, sku) VALUES ('Balón Fútbol', 'Talla 5', 25.0, 100, 'SKU002');
-- (Deberás completar hasta 20 con datos realistas) [cite: 177]

-- 3. Clientes (mínimo 4) [cite: 171]
INSERT INTO customers (email, password, first_name, last_name, address) VALUES ('juan@email.com', '1234', 'Juan', 'Pérez', 'Calle Falsa 123');
INSERT INTO customers (email, password, first_name, last_name, address) VALUES ('ana@email.com', '1234', 'Ana', 'García', 'Avenida Real 45');

-- 4. Relaciones Producto-Categoría [cite: 169, 170]
INSERT INTO products_categories (product_id, category_id) VALUES (1, 1); -- Producto 1 en Categoría 1