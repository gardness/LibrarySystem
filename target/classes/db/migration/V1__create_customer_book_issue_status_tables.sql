CREATE TABLE customers(
	id INTEGER PRIMARY KEY,
	name TEXT,
	address TEXT
);

CREATE TABLE books(
	id INTEGER PRIMARY KEY,
	title TEXT,
	category TEXT,
	rental_price NUMERIC,
	status BOOLEAN DEFAULT 't'
);

CREATE TABLE issue_statuses(
	id INTEGER PRIMARY KEY,
	issue_date DATE,
	return_date DATE,
	customer_id INTEGER REFERENCES customers(id),
	book_id INTEGER REFERENCES books(id)
);

