package dataStruct;

/**
 * @author karl.wy
 * @date 2019/05/08
 */
public class UnionFind {
    public int count = 0;
    private int[] parent, rank;
    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
    }
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (rank[rootQ] > rank[rootP]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
            if (rank[rootP] == rank[rootQ]) {
                rank[rootP]++;
            }
        }
        count--;
    }
}
