package easy.sql;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 从不订购的客户
 *
    某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。

    Customers 表：

    +----+-------+
    | Id | Name  |
    +----+-------+
    | 1  | Joe   |
    | 2  | Henry |
    | 3  | Sam   |
    | 4  | Max   |
    +----+-------+
    Orders 表：

    +----+------------+
    | Id | CustomerId |
    +----+------------+
    | 1  | 3          |
    | 2  | 1          |
    +----+------------+
    例如给定上述表格，你的查询应返回：

    +-----------+
    | Customers |
    +-----------+
    | Henry     |
    | Max       |
    +-----------+

 */
public class customers_who_never_order_183 {

    /**
     * select Name as Customers from Customers where Id not in (select distinct CustomerId from Orders);
     */
}
