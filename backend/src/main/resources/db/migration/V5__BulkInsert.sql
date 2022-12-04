CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Generating list of movies
INSERT INTO movie (id, name, cover)
VALUES
	(uuid('0df2909f-813c-424f-a774-fa7df72cffed'), 'The Matrix', 'https://image.tmdb.org/t/p/original/vybQQ7w7vGvF53IsGD0y0JSgIsA.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cff55'), 'Black Panther', 'https://media1.fdncms.com/connectsavannah/imager/u/slideshow/7210229/black-panther-poster.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cffe4'), 'Men in Black', 'https://m.media-amazon.com/images/M/MV5BOTlhYTVkMDktYzIyNC00NzlkLTlmN2ItOGEyMWQ4OTA2NDdmXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cffe1'), 'Spider-Man', 'https://flxt.tmsimg.com/assets/p8548776_p_v8_ad.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cffe2'), 'Your Name', 'https://m.media-amazon.com/images/M/MV5BNGYyNmI3M2YtNzYzZS00OTViLTkxYjAtZDIyZmE1Y2U1ZmQ2XkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cffab'), 'Toy Story 3', 'https://lumiere-a.akamaihd.net/v1/images/p_toystory3_19639_3c584e1f.jpeg'),
	(uuid('af808bfd-2c89-4aa2-b75d-269ae5bd7ee4'), 'Akira', 'https://upload.wikimedia.org/wikipedia/en/5/5d/AKIRA_%281988_poster%29.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cff05'), 'Black Widow', 'https://m.media-amazon.com/images/M/MV5BNjRmNDI5MjMtMmFhZi00YzcwLWI4ZGItMGI2MjI0N2Q3YmIwXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cff23'), 'Forrest Gump', 'https://upload.wikimedia.org/wikipedia/en/6/67/Forrest_Gump_poster.jpg'),
	(uuid('0df2909f-813c-424f-a774-fa7df72cff96'), 'Up', 'https://upload.wikimedia.org/wikipedia/en/0/05/Up_%282009_film%29.jpg'),
	(uuid('d355ed09-fb90-42ab-a1ce-5bef46e62d46'), 'The Avengers: Infinity War', 'https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_FMjpg_UX1000_.jpg'),
	(uuid('3c9f2fe8-be69-478f-95a7-6159faa36332'), 'Parasite', 'https://images.squarespace-cdn.com/content/v1/5a4488a6ace864a5558b6738/1575653762434-4HIFKWKBP8W893A50I95/p16965677_v_v8_aa.jpg');

-- Inserting a user with valid account and an expired account
INSERT INTO ru (id, firstname, lastname, email, password, address, creditcardnumber, creditcardexpirationdate, ccv, validUntil)
VALUES
	(uuid_generate_v4(), 'Topan', 'Nguyen', 'topan@gmail.com', 'test', '123 street', '3423458255', '01/08/29', '555', '2023-12-04T17:22:28.814+00:00'),
	(uuid_generate_v4(), 'Topan', 'Nguyen', 'topan@gmail.com', 'test', '123 street', '3423458255', '01/08/29', '555', '2022-12-03T17:22:28.814+00:00');

-- Generating list of showtimes
INSERT INTO showtime (id, movieid, showroom, showtime)
VALUES
	('0df2909f-813c-424f-a774-fa7df72cfd12', '0df2909f-813c-424f-a774-fa7df72cffed', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffed', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffed', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff55', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff55', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff55', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe4', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe4', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe4', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe1', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe1', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe1', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe2', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe2', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffe2', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffab', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffab', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cffab', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), 'af808bfd-2c89-4aa2-b75d-269ae5bd7ee4', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), 'af808bfd-2c89-4aa2-b75d-269ae5bd7ee4', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), 'af808bfd-2c89-4aa2-b75d-269ae5bd7ee4', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff05', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff05', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff05', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff23', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff23', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff23', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff96', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff96', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cff96', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), 'd355ed09-fb90-42ab-a1ce-5bef46e62d46', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), 'd355ed09-fb90-42ab-a1ce-5bef46e62d46', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), 'd355ed09-fb90-42ab-a1ce-5bef46e62d46', 5, '2023-12-04T21:00:00.814+00:00'),
	(uuid_generate_v4(), '3c9f2fe8-be69-478f-95a7-6159faa36332', 5, '2023-12-04T19:00:00.814+00:00'),
	(uuid_generate_v4(), '3c9f2fe8-be69-478f-95a7-6159faa36332', 5, '2023-12-04T20:00:00.814+00:00'),
	(uuid_generate_v4(), '3c9f2fe8-be69-478f-95a7-6159faa36332', 5, '2023-12-04T21:00:00.814+00:00');

INSERT INTO ticket (id, showtimeid, seatno, buyeremail, ruflag)
VALUES	
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cfd12', 2, 'topan@gmail.com', 'true'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cfd12', 3, 'topan@gmail.com', 'true'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cfd12', 4, 'topan@gmail.com', 'true'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cfd12', 21, 'topan@gmail.com', 'true'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cfd12', 22, 'topan@gmail.com', 'true'),
	(uuid_generate_v4(), '0df2909f-813c-424f-a774-fa7df72cfd12', 10, 'topan@gmail.com', 'true');
