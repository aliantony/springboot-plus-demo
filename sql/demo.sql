DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');


DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	gender INT(2) NULL DEFAULT NULL COMMENT '性别,0:MALE, 1:FEMALE',
	grade INT(3) NULL DEFAULT NULL COMMENT '年级',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DELETE FROM user;

INSERT INTO user (id, name, age, email, grade, gender) VALUES
(2, 'Jack', 3, 'test2@baomidou.com', 1, 0),
(3, 'Tom', 1, 'test3@baomidou.com', 2, 1),
(1, 'Billie', 2, 'test5@baomidou.com', 3, null);

ALTER table user add other_info VARCHAR(3000) NULL DEFAULT NULL COMMENT '其他信息'
alter table user add wallet VARCHAR(3000) NULL DEFAULT NULL COMMENT '钱包' AFTER email

INSERT INTO user (id, name, age, email, wallet, other_info) VALUES
(1, 'Jone', 18, 'test1@baomidou.com', '{
    "name": "支付宝钱包",
    "currencyList": [{
        "type": "USD",
        "amount": 999.19
    },{
        "type": "RMB",
        "amount": 1000.19
    }]
}', '{
        "sex": "男",
        "city": "南昌"
}'),
(2, 'Jack', 20, 'test2@baomidou.com', '{
    "name": "微信钱包",
    "currencyList": [{
        "type": "USD",
        "amount": 888.18
    },{
        "type": "RMB",
        "amount": 1000.18
    }]
}', '{
        "sex": "男",
        "city": "青岛"
}');

DROP TABLE IF EXISTS children;

CREATE TABLE children
(
    id      BIGINT (20) NOT NULL COMMENT '主键ID',
    name    VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    user_id BIGINT (20) NULL DEFAULT NULL COMMENT '上级ID',
    PRIMARY KEY (id)
);

INSERT INTO children (id, name, user_id)
VALUES (1, 'Jone', 1),
       (2, 'Jack', 1),
       (3, 'Jack2', 1),
       (4, 'Jack', 15),
       (5, 'Billie', 15);

INSERT INTO user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Jack', 20, 'test2@baomidou.com'),
       (4, 'Jack', 20, 'test2@baomidou.com'),
       (5, 'Jack', 20, 'test2@baomidou.com'),
       (6, 'Jack', 20, 'test2@baomidou.com'),
       (7, 'Jack', 20, 'test2@baomidou.com'),
       (8, 'Jack', 20, 'test2@baomidou.com'),
       (9, 'Jack', 20, 'test2@baomidou.com'),
       (10, 'Jack', 20, 'test2@baomidou.com'),
       (11, 'Jack', 20, 'test2@baomidou.com'),
       (12, 'Jack', 20, 'test2@baomidou.com'),
       (13, 'Jack', 20, 'test2@baomidou.com'),
       (14, 'Jack', 20, 'test2@baomidou.com'),
       (15, 'Tom', 28, 'test3@baomidou.com'),
       (16, 'Sandy', 21, 'test4@baomidou.com'),
       (17, 'Billie', 24, 'test5@baomidou.com');
