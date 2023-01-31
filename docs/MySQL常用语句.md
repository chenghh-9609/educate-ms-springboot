## sql基础语句

### 表操作
```sql
-- 创建表
CREATE TABLE students (
    id BIGINT NOT NULL AUTO_INCREMENT,
    class_id BIGINT NOT NULL,
    average DOUBLE NOT NULL,
    PRIMARY KEY (id)
);
-- 修改表名
ALTER TABLE old_table_name RENAME TO new_table_name;
-- 删除表
DROP TABLE new_table_name;
```



### 修改表字段
```sql
-- 新增字段
ALTER TABLE students ADD name VARCHAR(40);
ALTER TABLE students ADD gender VARCHAR(2);
ALTER TABLE students ADD score INT;
-- 删除字段
ALTER TABLE students DROP average;
-- 修改字段
ALTER TABLE students MODIFY gender char(2) NOT NULL DEFAULT 'M';
ALTER TABLE students CHANGE gender sgender VARCHAR(20) NOT NULL;
```



### 增删改
```sql
-- 插入数据
INSERT INTO students(class_id, name, gender, score) VALUES (1, '小明','F',88);
INSERT INTO students(class_id, name, gender, score) VALUES (2, '小军','M',83), (1, '小红','F',79), (3, '小白','M',98);
-- 删除数据
DELETE FROM students WHERE id>=3;
-- 修改数据
UPDATE students SET gender='F', class_id=3 WHERE id=1;
UPDATE students SET name='小牛', score=77 WHERE id>=5 AND id<=7;
UPDATE students SET score=score+10 WHERE score<80;
-- 插入或替换（记录存在则替换，不存在则插入）
REPLACE INTO students (id, class_id, name, gender, score) VALUES (1, 1, '小明', 'F', 99);
-- 插入或更新（记录存在则更新，不存在则插入）
INSERT INTO students (id, class_id, name, gender, score) VALUES (1, 1, '小明', 'F', 99) ON DUPLICATE KEY UPDATE name='小明', gender='F', score=99;
-- 插入或忽略（记录存在则什么也不做，不存在则插入）
INSERT IGNORE INTO students (id, class_id, name, gender, score) VALUES (1, 1, '小明', 'F', 99);
```



### 基础查询
```sql
-- 基础查询
SELECT * FROM students;

-- 条件查询
SELECT * FROM students WHERE score > 85;
SELECT * FROM students WHERE score > 85 AND gender = 'F';
SELECT * FROM students WHERE score > 85 OR gender = 'F';
SELECT * FROM students WHERE (score < 80 OR score > 90) AND gender = 'M';
-- 不符合条件
SELECT * FROM students WHERE NOT class_id = 3;
-- 和上面等价
SELECT * FROM students WHERE class_id <> 3;
-- 区间
SELECT * FROM students WHERE score BETWEEN 60 AND 90;
-- 端点
SELECT * FROM students WHERE score IN (60, 90);
-- null查询
SELECT * FROM students where score is null;
-- 投影查询（指定字段）
SELECT id, name, score FROM students;
SELECT id, name, score FROM students WHERE score BETWEEN 60 AND 90;
SELECT id, name, score FROM students WHERE name LIKE '张%';
```

### 排序(ORDER BY, DESC)
```sql
-- 正序
SELECT * FROM students 
ORDER BY score ;
-- 倒序
SELECT * FROM students 
ORDER BY score DESC;
-- 先按score倒序，score相同的则按gender排
SELECT * FROM students 
ORDER BY score DESC, gender;
-- 条件排序放在where后
SELECT id, name, score 
FROM students
WHERE score BETWEEN 80 and 90
ORDER BY score desc;
```

### 分页(LIMIT)
```sql
-- LIMIT N OFFSET M 等价于 LIMIT N,M
-- 每页3条记录，第1页
SELECT id, name, gender, score
FROM students
ORDER BY score DESC
LIMIT 3 OFFSET 0;
-- 每页3条记录，第2页
SELECT id, name, gender, score
FROM students
ORDER BY score DESC
LIMIT 3 OFFSET 3;
-- 每页3条记录，第3页
SELECT id, name, gender, score
FROM students
ORDER BY score DESC
LIMIT 3 OFFSET 6
-- 筛选订单最多的用户
SELECT customer_number FROM orders 
GROUP BY customer_number 
ORDER BY count(customer_number) DESC
LIMIT 1;
```

### 聚合(COUNT|AVG|SUM|...)
```sql
-- 总记录数
SELECT COUNT(*) FROM students;
SELECT COUNT(id) boys FROM students WHERE gender= 'M';
-- 平均数
SELECT AVG(score) FROM students WHERE gender = 'F';
-- 合计
SELECT SUM(score) FROM students WHERE gender='F';
-- 最大值
SELECT MAX(score) FROM students WHERE gender='M';
-- 最小值
SELECT MIN(score) FROM students WHERE gender='F';
-- 日期差，查询生日在1999年1月31之前30天的
SELECT birthday FROM students WHERE datediff('1999-01-31', birthday) < 30
```
### 分组(GROUP BY)
```sql
-- 各个班级的平均分
SELECT class_id, AVG(score) avg 
FROM students 
GROUP BY class_id;
-- 各个班级的男、女人数
SELECT class_id, count(gender) num 
FROM students 
GROUP BY class_id, gender;
-- 查找大于2人的班级id
SELECT class_id 
FROM students
GROUP BY class_id 
HAVING count(id) > 2;
```
### 多表查询
```sql
SELECT * FROM students, classes;
-- 投影
SELECT 
	s.id sid,
	s.name,
	s.gender,
	s.score,
	c.id cid,
	c.name cname
FROM students s, classes c;
-- 条件
SELECT 
	s.id sid,
	s.name,
	s.gender,
	s.score,
	c.id cid,
	c.name cname
FROM students s, classes c
WHERE s.gender = 'M' AND c.id = 1;
```
### 连接(JOIN)
```sql
-- 内连接，查询交集s.class_id和c.id都不为null且相等
SELECT 
	s.id,
	s.name,
	s.gender,
	s.score,
	s.class_id
FROM students s
INNER JOIN classes c
ON s.class_id = c.id;

-- 条件、排序
SELECT 
	s.id,
	s.name,
	s.gender,
	s.score,
	c.name class_name
FROM students s
INNER JOIN classes c
ON s.class_id = c.id
WHERE score > 80
ORDER BY score;

-- 右外连接，join右边的表(classes)的id都查询出来，其他字段没有则填null
SELECT 
	s.id,
	s.name,
	s.gender,
	s.score,
	c.name class_name
FROM students s
RIGHT OUTER JOIN classes c
ON s.class_id = c.id
-- 左外连接，join左边的表(classes)的class_id都查询出来，其他字段没有则填null
SELECT 
	s.id,
	s.name,
	s.gender,
	s.score,
	c.name class_name
FROM students s
LEFT OUTER JOIN classes c
ON s.class_id = c.id
-- 全外连接，join左右边的表的class_id,id都查询出来，其他字段没有则填null
SELECT 
	s.id,
	s.name,
	s.gender,
	s.score,
	c.name class_name
FROM students s
FULL OUTER JOIN classes c
ON s.class_id = c.id
-- 自连接 (查询收入超过经理的员工)
SELECT e1.name AS employee
FROM employee e1, employee e2
WHERE e1.managerId = e2.id
AND e1.salary > e2.salary
```