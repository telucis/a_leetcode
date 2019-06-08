package complete.unionFind.hard;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 按公因数计算最大组件大小
 *
    给定一个由不同正整数的组成的非空数组 A，考虑下面的图：

    有 A.length 个节点，按从 A[0] 到 A[A.length - 1] 标记；
    只有当 A[i] 和 A[j] 共用一个大于 1 的公因数时，A[i] 和 A[j] 之间才有一条边。
    返回图中最大连通组件的大小。



    示例 1：

    输入：[4,6,15,35]
    输出：4

    示例 2：

    输入：[20,50,9,63]
    输出：2

    示例 3：

    输入：[2,3,6,7,4,12,21,39]
    输出：8



    提示：

    1 <= A.length <= 20000
    1 <= A[i] <= 100000

 */
public class largest_component_size_by_common_factor_952 {

    public int largestComponentSize(int[] A) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        Uf uf = new Uf(n);
        for (int i=0; i<n; i++) {
            int a = A[i];
            for (int j=2; j*j<=a; j++) {
                if (a%j==0) {
                    if (!map.containsKey(j)) {
                        map.put(j, i);
                    } else {
                        uf.union(i, map.get(j));
                    }
                    if (!map.containsKey(a/j)) {
                        map.put(a/j, i);
                    } else {
                        uf.union(i, map.get(a/j));
                    }
                }
            }
            if (!map.containsKey(a)) {
                map.put(a, i);
            } else {
                uf.union(i, map.get(i));
            }
        }
        return uf.max;
    }
    private class Uf {
        int[] p;
        int[] size;
        int max;
        public Uf(int n) {
            p=new int[n];
            size = new int[n];
            max=1;
            for (int i=0; i<n; i++) {
                p[i]=i;
                size[i]=1;
            }
        }
        public int find(int i) {
            if (i!=p[i]) {
                p[i]=find(p[i]);
            }
            return p[i];
        }
        public void union(int a, int b) {
            int pa=find(a), pb=find(b);
            if(pa!=pb) {
                p[pa]=pb;
                size[pb]+=size[pa];
                max = Math.max(max, size[pb]);
            }
        }
    }
}
