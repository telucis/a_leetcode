package complete.stack.hard;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/06/13
 *
 * 去除重复字母
 *
    给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

    示例 1:

    输入: "bcabc"
    输出: "abc"
    示例 2:

    输入: "cbacdcbc"
    输出: "acdb"

 */
public class remove_duplicate_letters_316 {

    /**
     * monotone stack
     */
    public String removeDuplicateLetters(String str) {

        int[] res = new int[26];
        boolean[] visited = new boolean[26];
        char[] ch = str.toCharArray();
        for(char c: ch){
            res[c-'a']++;
        }
        Stack<Character> st = new Stack<>();
        int index;
        for(char s:ch){
            index= s-'a';
            res[index]--;
            if(visited[index]) continue;
            while(!st.isEmpty() && s<st.peek() && res[st.peek()-'a']!=0){
                visited[st.pop()-'a']=false;
            }
            st.push(s);
            visited[index]=true;
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.insert(0,st.pop());
        }
        return sb.toString();
    }
}
