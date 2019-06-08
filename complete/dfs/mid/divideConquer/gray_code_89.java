package complete.dfs.mid.divideConquer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 格雷编码
 *
    格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

    给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

    示例 1:

    输入: 2
    输出: [0,1,3,2]
    解释:
    00 - 0
    01 - 1
    11 - 3
    10 - 2

    对于给定的 n，其格雷编码序列并不唯一。
    例如，[0,2,3,1] 也是一个有效的格雷编码序列。

    00 - 0
    10 - 2
    11 - 3
    01 - 1
    示例 2:

    输入: 0
    输出: [0]
    解释: 我们定义格雷编码序列必须以 0 开头。
    给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
    因此，当 n = 0 时，其格雷编码序列为 [0]。

 */
public class gray_code_89 {

    public List<Integer> grayCode(int n) {
        if (n<0) return new ArrayList<>();
        if (n==0) {
            List<Integer> ans = new ArrayList<>();
            ans.add(0);
            return ans;
        }
        List<Integer> tmp = grayCode(n-1);
        List<Integer> result = new ArrayList<>(tmp);
        int addNumber = 1<<(n-1);
        for (int i=tmp.size()-1; i>=0; i--) {
            result.add(addNumber+tmp.get(i));
        }
        return result;
    }

    /**
     * backtracking
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<Math.pow(2, n); i++) {
            list.add(i);
        }
        return helper(list, new LinkedList<>(), (int)Math.pow(2, n));
    }
    private List<Integer> helper(List<Integer> list, LinkedList<Integer> tmp, int n) {
        if (tmp.size()==n) {
            System.out.println(tmp);
            return tmp;
        }
        for (int i=0; i<list.size(); i++) {
            int num = list.get(i);
            if (tmp.isEmpty() || judge(tmp.getLast(), num)) {
                tmp.add(num);
                list.remove(i);
                List<Integer> res = helper(list, tmp, n);
                if (res!=null) return res;
                tmp.removeLast();
                list.add(i, num);
            }
        }
        return null;
    }
    private boolean judge(int a, int b) {
        int c = a ^ b, count=0;
        while (c>0) {
            if ((c&1)==1) {
                count++;
            }
            c>>=1;
            if (count>1) return false;
        }
        return count==1;
    }
}
