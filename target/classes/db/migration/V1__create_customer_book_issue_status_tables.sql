CREATE TABLE customer(
	id INTEGER PRIMARY KEY,
	name TEXT,
	address TEXT
);

CREATE TABLE book(
	id INTEGER PRIMARY KEY,
	title TEXT,
	category TEXT,
	rental_price NUMERIC,
	status BOOLEAN DEFAULT 't'
);

CREATE TABLE issue_status(
	id INTEGER PRIMARY KEY,
	issue_date DATE,
	return_date DATE,
	customer_id INTEGER REFERENCES customer(id),
	book_id INTEGER REFERENCES book(id)
);

