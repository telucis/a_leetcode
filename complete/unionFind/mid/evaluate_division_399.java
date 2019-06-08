package complete.unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 除法求值
 *
    给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。

    示例 :
    给定 a / b = 2.0, b / c = 3.0
    问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
    返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]

    输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。

    基于上述例子，输入如下：

    equations(方程式) = [ ["a", "b"], ["b", "c"] ],
    values(方程式结果) = [2.0, 3.0],
    queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
    输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。


 */
public class evaluate_division_399 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        Map<String, String> root = new HashMap<>();
        Map<String, Double> dist = new HashMap<>();
        for (int i=0; i<equations.size(); i++) {
            String r1 = find(root, dist, equations.get(i).get(0));
            String r2 = find(root, dist, equations.get(i).get(1));
            root.put(r1, r2);
            dist.put(r1, dist.get(equations.get(i).get(1)) * values[i] / dist.get(equations.get(i).get(0)));
        }
        for (int i=0; i<queries.size(); i++) {
            if (!root.containsKey(queries.get(i).get(0)) || !root.containsKey(queries.get(i).get(1))) {
                res[i] = -1.0;
                continue;
            }
            String r1 = find(root, dist, queries.get(i).get(0));
            String r2 = find(root, dist, queries.get(i).get(1));
            if (!r1.equals(r2)) {
                res[i] = -1.0;
                continue;
            }
            res[i] = (double)dist.get(queries.get(i).get(0))/dist.get(queries.get(i).get(1));
        }
        return res;
    }
    private String find(Map<String, String> root, Map<String, Double> dist, String s) {
        if (!root.containsKey(s)) {
            root.put(s, s);
            dist.put(s, 1.0);
            return s;
        }
        if (root.get(s).equals(s)) {
            return s;
        }
        String lastP = root.get(s);
        String p = find(root, dist, lastP);
        root.put(s, p);
        dist.put(s, dist.get(s)*dist.get(lastP));
        return p;
    }
}
