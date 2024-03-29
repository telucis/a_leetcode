package easy.sql;

/**
 * @author karl.wy
 * @date 2019/04/27
 *
 * 删除重复的电子邮箱
 *
    编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。

    +----+------------------+
    | Id | Email            |
    +----+------------------+
    | 1  | john@example.com |
    | 2  | bob@example.com  |
    | 3  | john@example.com |
    +----+------------------+
    Id 是这个表的主键。
    例如，在运行你的查询语句之后，上面的 Person 表应返回以下几行:

    +----+------------------+
    | Id | Email            |
    +----+------------------+
    | 1  | john@example.com |
    | 2  | bob@example.com  |
    +----+------------------+

 */
public class delete_duplicate_emails_196 {

    /**
     * delete p1 from Person p1 ,Person p2 where p1.Email =p2.Email and p1.Id > p2.Id;
     *
     * delete p1 from Person p1 left join Person p2 on p1.Email =p2.Email where p1.Id > p2.Id;
     */
}
