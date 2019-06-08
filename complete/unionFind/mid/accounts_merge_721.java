package complete.unionFind;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 账户合并
 *
    给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该帐户的邮箱地址。

    现在，我们想合并这些帐户。如果两个帐户都有一些共同的邮件地址，则两个帐户必定属于同一个人。请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的帐户，但其所有帐户都具有相同的名称。

    合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。accounts 本身可以以任意顺序返回。

    例子 1:

    Input:
    accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
    Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    Explanation:
    第一个和第三个 John 是同一个人，因为他们有共同的电子邮件 "johnsmith@mail.com"。
    第二个 John 和 Mary 是不同的人，因为他们的电子邮件地址没有被其他帐户使用。
    我们可以以任何顺序返回这些列表，例如答案[['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
    ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']]仍然会被接受。

    注意：

    accounts的长度将在[1，1000]的范围内。
    accounts[i]的长度将在[1，10]的范围内。
    accounts[i][j]的长度将在[1，30]的范围内。

 */
public class accounts_merge_721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : accounts) {
            for (int i=1; i<a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            for (int i=2; i<a.size(); i++) {
                parents.put(find(a.get(i), parents), p);
            }
        }
        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) {
                unions.put(p, new TreeSet<>());
            }
            for (int i=1; i<a.size(); i++) {
                unions.get(p).add(a.get(i));
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> email = new ArrayList<>(unions.get(p));
            email.add(0, owner.get(p));
            res.add(email);
        }
        return res;
    }
    private String find(String s, Map<String, String> p) {
        return p.get(s).equals(s) ? s : find(p.get(s), p);
    }
}
