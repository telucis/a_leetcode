/**
 * dfs
 */
//60 第K个排列
StringBuilder ans = new StringBuilder();
int index;
public String getPermutation(int n, int k) {
    index = k;
    dfs(n, new LinkedList<Integer>());
    return ans.toString();
}
private void dfs(int n, LinkedList<Integer> tmp) {
    if (tmp.size() == n) {
        index--;
        if (index==0) {
            for (Integer i : tmp) ans.append(i);
        }
        return;
    }
    for (int i=1; i<=n; i++) {
        if (tmp.contains(i)) continue;
        tmp.add(i);
        dfs(n, tmp);
        if (index==0) {
            return;
        }
        tmp.removeLast();
    }
}
