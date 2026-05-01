-- This is your EXISTING schema (already in library_db).
-- No changes needed to the tables themselves.
-- This file is for reference only.

USE library_db;

-- books table (already exists):
-- CREATE TABLE books (
--     books_id   INT PRIMARY KEY,
--     title      VARCHAR(100),
--     category   VARCHAR(50),
--     authors_id INT,
--     library_id INT,
--     FOREIGN KEY (authors_id) REFERENCES authors(authors_id),
--     FOREIGN KEY (library_id) REFERENCES library(library_id)
-- );

-- Sample INSERT (already in your DB):
-- INSERT INTO books VALUES
-- (101, 'The Cairo Trilogy',            'Drama',     1, 1),
-- (102, 'Utopia',                        'Sci-Fi',    2, 1),
-- (103, 'Murder on the Orient Express',  'Mystery',   3, 2),
-- (104, '1984',                          'Dystopian', 4, 2),
-- (105, 'The Shining',                   'Horror',    5, 1);
