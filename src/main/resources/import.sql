INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (1, 'kkukkumania', '321321', 'kkukku', 'kkukku@gmail.com');
INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (2, 'ssoonmania', '1', 'ssoon', 'ssoon@gmail.com');

INSERT INTO QUESTION (ID, WRITER_ID, TITLE, CONTENTS, CREATE_DATE) VALUES  (1, 1, '국내에서 Ruby on Rails와 Play가', 'Ruby on Rails(이하 ROR)는', CURRENT_TIMESTAMP());
INSERT INTO QUESTION (ID, WRITER_ID, TITLE, CONTENTS, CREATE_DATE) VALUES  (2, 2, 'SpringFramework', 'Spring Boot', CURRENT_TIMESTAMP());