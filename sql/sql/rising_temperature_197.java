package easy.sql;

/**
 * @author karl.wy
 * @date 2019/04/27
 *
 * 上升的温度
 *
    给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。

    +---------+------------------+------------------+
    | Id(INT) | RecordDate(DATE) | Temperature(INT) |
    +---------+------------------+------------------+
    |       1 |       2015-01-01 |               10 |
    |       2 |       2015-01-02 |               25 |
    |       3 |       2015-01-03 |               20 |
    |       4 |       2015-01-04 |               30 |
    +---------+------------------+------------------+
    例如，根据上述给定的 Weather 表格，返回如下 Id:

    +----+
    | Id |
    +----+
    |  2 |
    |  4 |
    +----+

 */
public class rising_temperature_197 {

    /**
     * select w2.Id from Weather w1,Weather w2 where w1.Temperature<w2.Temperature and datediff(w2.RecordDate,w1.RecordDate)=1;
     */
}
