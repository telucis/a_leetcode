package complete.stack.hard;

import java.util.*;

/**
 * Created by telucis on 2019/6/12.
 *
 * 原子的数量
 *
     给定一个化学式formula（作为字符串），返回每种原子的数量。

     原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。

     如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。

     两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。

     一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。

     给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。

     示例 1:

     输入:
     formula = "H2O"
     输出: "H2O"
     解释:
     原子的数量是 {'H': 2, 'O': 1}。
     示例 2:

     输入:
     formula = "Mg(OH)2"
     输出: "H2MgO2"
     解释:
     原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
     示例 3:

     输入:
     formula = "K4(ON(SO3)2)2"
     输出: "K4N2O14S4"
     解释:
     原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
     注意:

     所有原子的第一个字母为大写，剩余字母都是小写。
     formula的长度在[1, 1000]之间。
     formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。

 */
public class number_of_atoms_726 {

    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>();
        int i=0, n=formula.length();
        while (i<n) {
            char c = formula.charAt(i); i++;
            if (c=='(') {
                stack.push(map);
                map = new HashMap<>();
            } else if (c==')') {
                int val = 0;
                while (i<n && Character.isDigit(formula.charAt(i))) {
                    val=val*10 + formula.charAt(i++)-'0';
                }
                if (val==0) val=1;
                if (!stack.isEmpty()) {
                    Map<String, Integer> tmp = map;
                    map = stack.pop();
                    for (String key : tmp.keySet()) {
                        map.put(key, map.getOrDefault(key, 0)+tmp.get(key)*val);
                    }
                }
            } else {
                int start = i-1;
                while (i<n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                String s = formula.substring(start, i);
                int val = 0;
                while (i<n && Character.isDigit(formula.charAt(i))) {
                    val=val*10+formula.charAt(i++)-'0';
                }
                if (val==0) val=1;
                map.put(s, map.getOrDefault(s, 0)+val);
            }
        }
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (String key : list) {
            sb.append(key);
            if (map.get(key)>1) sb.append(map.get(key));
        }
        return sb.toString();
    }
}
