
-- 1、 基于MySql的LIMIT和Oracle的ROWNUM，可以直接限制返回区间（以MySql为例，注意使用Oracle的ROWNUM时要应用子查询）：
-- 优点：写法简单。
-- 缺点：当页码和页大小过大时，性能明显下降。
-- 适用：数据量不大。

SELECT * FROM tableName WHERE 查询条件 ORDER BY 排序条件 LIMIT (页码-1)*页大小, 页大小


-- 2、基于LIMIT（MySql）、ROWNUM（Oracle）和TOP（SqlServer），他们可以限制返回的行数，因此可以得到以下两套通用的方法（以SqlServer为例）：
-- 优点：通用性强。
-- 缺点：当数据量较大时向后翻页，NOT IN中的数据过大会影响性能。
-- 适用：数据量不大。

SELECT TOP 页大小 * FROM tableName WHERE 主键 NOT IN 
(
    SELECT TOP (页码-1)*页大小 主键 FROM tableName WHERE 
)
ORDER BY 排序条件