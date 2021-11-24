CREATE TABLE IF NOT EXISTS users
(
    id
    INT
    NOT
    NULL
    AUTO_INCREMENT
    PRIMARY
    KEY,
    username
    VARCHAR
(
    32
) UNIQUE NOT NULL,
    password VARCHAR
(
    32
) NOT NULL
    );
INSERT INTO users (username, password)
VALUES ('ada@kth.se', 'qwerty');
INSERT INTO users (username, password)
VALUES ('beda@kth.se', '123456');

CREATE TABLE IF NOT EXISTS questions
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    text
    VARCHAR
(
    255
) NOT NULL,
    options VARCHAR
(
    255
) NOT NULL,
    answer VARCHAR
(
    255
) NOT NULL
    );
INSERT INTO questions (text, options, answer)
VALUES ('Which planets are larger than earth?', 'Mercury/Mars/Saturn', '0/0/1');
INSERT INTO questions (text, options, answer)
VALUES ('Which planets are farther away from the sun than earth?', 'Mercury/Mars/Saturn', '0/1/1');
INSERT INTO questions (text, options, answer)
VALUES ('Which planets have rings?', 'Mercury/Mars/Saturn', '0/0/1');

CREATE TABLE IF NOT EXISTS quizzes
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    subject
    VARCHAR
(
    255
) NOT NULL
    );
INSERT INTO quizzes (subject)
VALUES ('Astronomy');


CREATE TABLE IF NOT EXISTS selector
(
    quizId
    INT
    NOT
    NULL,
    questionId
    INT
    NOT
    NULL,
    FOREIGN
    KEY
(
    quizId
) REFERENCES quizzes
(
    id
),
    FOREIGN KEY
(
    questionId
) REFERENCES questions
(
    id
)
    );
INSERT INTO selector (quizId, questionId)
VALUES (1, 1);
INSERT INTO selector (quizId, questionId)
VALUES (1, 2);
INSERT INTO selector (quizId, questionId)
VALUES (1, 3);

CREATE TABLE IF NOT EXISTS results
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    userId
    INT
    NOT
    NULL,
    quizd
    INT
    NOT
    NULL,
    score
    INT
    NOT
    NULL,
    FOREIGN
    KEY
(
    userId
) REFERENCES users
(
    id
),
    FOREIGN KEY
(
    quizId
) REFERENCES quizzes
(
    id
)
    );
INSERT INTO results (userId, quizId, score)
VALUES (1, 1, 0);