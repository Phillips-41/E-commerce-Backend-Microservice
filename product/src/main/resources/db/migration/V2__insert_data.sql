-- Insert into the category table
INSERT INTO category (id, name, description)
VALUES
    (nextval('category_seq'), 'Electronics', 'Devices and gadgets'),
    (nextval('category_seq'), 'Books', 'Literature and educational material'),
    (nextval('category_seq'), 'Clothing', 'Apparel and fashion wear'),
    (nextval('category_seq'), 'Home Appliances', 'Household electrical devices'),
    (nextval('category_seq'), 'Sports', 'Sports equipment and accessories');

-- Get the IDs of the inserted categories
WITH category_ids AS (
    SELECT id, name
    FROM category
    WHERE name IN ('Electronics', 'Books', 'Clothing', 'Home Appliances', 'Sports')
)
-- Insert products using the category IDs
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES
    -- Products for Electronics
    (nextval('product_seq'), 'Smartphone', 'Latest model with 128GB storage', 100, 699.99, (SELECT id FROM category_ids WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Laptop', 'High-performance laptop with 16GB RAM', 50, 1299.99, (SELECT id FROM category_ids WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Headphones', 'Noise-cancelling over-ear headphones', 150, 199.99, (SELECT id FROM category_ids WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Smartwatch', 'Fitness tracker and smartwatch', 80, 249.99, (SELECT id FROM category_ids WHERE name = 'Electronics')),

    -- Products for Books
    (nextval('product_seq'), 'Science Fiction Book', 'A thrilling space adventure', 200, 19.99, (SELECT id FROM category_ids WHERE name = 'Books')),
    (nextval('product_seq'), 'History Book', 'Exploring the ancient civilizations', 120, 24.99, (SELECT id FROM category_ids WHERE name = 'Books')),
    (nextval('product_seq'), 'Mystery Novel', 'A gripping detective story', 180, 14.99, (SELECT id FROM category_ids WHERE name = 'Books')),
    (nextval('product_seq'), 'Self-Help Book', 'Guidance for personal development', 150, 29.99, (SELECT id FROM category_ids WHERE name = 'Books')),

    -- Products for Clothing
    (nextval('product_seq'), 'T-Shirt', 'Cotton T-shirt with cool design', 300, 15.99, (SELECT id FROM category_ids WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Jeans', 'Stylish blue jeans', 200, 49.99, (SELECT id FROM category_ids WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Jacket', 'Warm winter jacket', 75, 89.99, (SELECT id FROM category_ids WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Sneakers', 'Casual sneakers for everyday wear', 150, 59.99, (SELECT id FROM category_ids WHERE name = 'Clothing')),

    -- Products for Home Appliances
    (nextval('product_seq'), 'Washing Machine', 'Energy-efficient washing machine', 30, 499.99, (SELECT id FROM category_ids WHERE name = 'Home Appliances')),
    (nextval('product_seq'), 'Refrigerator', 'Double-door refrigerator with freezer', 20, 899.99, (SELECT id FROM category_ids WHERE name = 'Home Appliances')),
    (nextval('product_seq'), 'Microwave Oven', 'Quick and efficient microwave oven', 50, 199.99, (SELECT id FROM category_ids WHERE name = 'Home Appliances')),
    (nextval('product_seq'), 'Air Conditioner', 'Energy-efficient split AC', 15, 499.99, (SELECT id FROM category_ids WHERE name = 'Home Appliances')),

    -- Products for Sports
    (nextval('product_seq'), 'Soccer Ball', 'Professional soccer ball', 150, 25.99, (SELECT id FROM category_ids WHERE name = 'Sports')),
    (nextval('product_seq'), 'Tennis Racket', 'High-quality tennis racket', 60, 79.99, (SELECT id FROM category_ids WHERE name = 'Sports')),
    (nextval('product_seq'), 'Yoga Mat', 'Non-slip yoga mat for workouts', 200, 29.99, (SELECT id FROM category_ids WHERE name = 'Sports')),
    (nextval('product_seq'), 'Dumbbells', 'Adjustable dumbbells for home gym', 100, 59.99, (SELECT id FROM category_ids WHERE name = 'Sports'));
