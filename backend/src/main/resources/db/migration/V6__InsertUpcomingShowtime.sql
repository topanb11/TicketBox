CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Generating upcoming showtime
INSERT INTO showtime (id, movieid, showroom, showtime, presale)
VALUES
	('72999b42-c8f5-4b25-8e2c-4ddfbb895b9a', '0df2909f-813c-424f-a774-fa7df72cffed', 5, '2022-12-06T19:00:00.814+00:00', true);

INSERT INTO ticket (id, showtimeid, seatno, buyeremail, ruflag)
VALUES	
	(uuid_generate_v4(), '72999b42-c8f5-4b25-8e2c-4ddfbb895b9a', 12, 'topanb@gmail.com', 'false'),
	(uuid_generate_v4(), '72999b42-c8f5-4b25-8e2c-4ddfbb895b9a', 31, 'topanb@gmail.com', 'false'),
	(uuid_generate_v4(), '72999b42-c8f5-4b25-8e2c-4ddfbb895b9a', 4, 'topanb@gmail.com', 'false'),
	(uuid_generate_v4(), '72999b42-c8f5-4b25-8e2c-4ddfbb895b9a', 29, 'topanb@gmail.com', 'false'),
	(uuid_generate_v4(), '72999b42-c8f5-4b25-8e2c-4ddfbb895b9a', 22, 'topanb@gmail.com', 'false'),
	(uuid_generate_v4(), '72999b42-c8f5-4b25-8e2c-4ddfbb895b9a', 19, 'topanb@gmail.com', 'false');
