package complete.dfs.mid.traversal;

import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 迷你语法分析器
 *
    给定一个用字符串表示的整数的嵌套列表，实现一个解析它的语法分析器。

    列表中的每个元素只可能是整数或整数嵌套列表

    提示：你可以假定这些字符串都是格式良好的：

    字符串非空
    字符串不包含空格
    字符串只包含数字0-9, [, - ,, ]


    示例 1：

    给定 s = "324",

    你应该返回一个 NestedInteger 对象，其中只包含整数值 324。


    示例 2：

    给定 s = "[123,[456,[789]]]",

    返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：

    1. 一个 integer 包含值 123
    2. 一个包含两个元素的嵌套列表：
    i.  一个 integer 包含值 456
    ii. 一个包含一个元素的嵌套列表
    a. 一个 integer 包含值 789

 */
public class mini_parser_385 {

    public NestedInteger deserialize(String s) {
        NestedInteger ret = new NestedInteger();
        if (s==null || s.length()==0) {
            return ret;
        }
        if (s.charAt(0) != '[') {
            ret.setInteger(Integer.parseInt(s));
        } else if (s.length() > 2) {
            int start=1, count=0;
            for (int i=1; i<s.length(); i++) {
                char c = s.charAt(i);
                if (count == 0 && (c==',' || i==s.length()-1)) {
                    ret.add(deserialize(s.substring(start, i)));
                    start = i+1;
                } else if (c=='[') {
                    count++;
                } else if (c==']') {
                    count--;
                }
            }
        }
        return ret;
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger(){};

        // Constructor initializes a single integer.
        public NestedInteger(int value){};

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger(){return true;}

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger(){return null;}

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value){}

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni){}

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList(){return null;}
    }
}

