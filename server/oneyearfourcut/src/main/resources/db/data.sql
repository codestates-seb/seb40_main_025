INSERT INTO MEMBER (nickname, email, created_at, last_modified_at) VALUES
('galleryPerson', 'test1@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644'),
('artworkPerson1', 'artworkPerson1@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644'),
('commentPerson', 'test3@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644'),
('replyPerson', 'test4@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644'),
('votePerson', 'test5@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644'),
('artworkPerson2', 'artworkPerson2@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644'),
('artworkPerson3', 'artworkPerson3@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644'),
('artworkPerson4', 'artworkPerson4@gmail.com', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644');

INSERT INTO GALLERY (title, content ,created_at, last_modified_at, member_id) values
('galleryPerson님의 전시관', '설명글', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644', 1);


INSERT INTO ARTWORK (title, content, image_path, created_at, last_modified_at ,gallery_id, member_id) values
('프로젝트', '다들 화이팅입니다!', '/1.jpg', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644', 1, 2),
('프로젝트', '다들 화이팅입니다!', '/2.jpg', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644', 1, 6),
('프로젝트', '다들 화이팅입니다!', '/3.jpg', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644', 1, 7),
('프로젝트', '다들 화이팅입니다!', '/4.jpg', '2022-11-16T23:41:57.764644', '2022-11-16T23:41:57.764644', 1, 8);