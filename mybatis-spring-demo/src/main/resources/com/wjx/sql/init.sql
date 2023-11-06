DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` varchar(40) DEFAULT NULL COMMENT '姓名',
    `address` varchar(40) NOT NULL COMMENT '地址',
    `age` int(2) DEFAULT NULL COMMENT '身份证号',
    `testTuoFeng` varchar(40) DEFAULT NULL COMMENT '测试驼峰',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表'


DROP TABLE IF EXISTS course;
CREATE TABLE course
(
    `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `course_name` varchar(40) DEFAULT NULL COMMENT '课程名称',
    `test_tuo_feng` varchar(40) DEFAULT NULL COMMENT '测试驼峰',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表'

ALTER TABLE `mybatis_plus`.`student`
    ADD COLUMN `test_tuo_feng2` varchar(40) NULL COMMENT '测试驼峰' AFTER `testTuoFeng`;