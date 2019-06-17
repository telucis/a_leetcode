package complete.twoPointer.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by telucis on 2019/5/11.
 *
 * 字符串的排列
 *
     给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

     换句话说，第一个字符串的排列之一是第二个字符串的子串。

     示例1:

     输入: s1 = "ab" s2 = "eidbaooo"
     输出: True
     解释: s2 包含 s1 的排列之一 ("ba").


     示例2:

     输入: s1= "ab" s2 = "eidboaoo"
     输出: False


     注意：

     输入的字符串只包含小写字母
     两个字符串的长度都在 [1, 10,000] 之间

 */
public class permutation_in_string_567 {

    public boolean checkInclusion(String s1, String s2) {
        if (s1==null || s2==null) {
            return false;
        }
        int len=s1.length(), left=0, right=0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        int count=map.size();
        while (right<s2.length()) {
            char c = s2.charAt(right);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c)==0) {
                    count--;
                }
            }
            while (count==0) {
                if (right-left+1==len) {
                    return true;
                }
                char leftC = s2.charAt(left);
                if (map.containsKey(leftC)) {
                    map.put(leftC, map.get(leftC)+1);
                    if (map.get(leftC)>0) {
                        count++;
                    }
                }
                left++;
            }
            right++;
        }
        return false;
    }
}
