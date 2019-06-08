package easy.string;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/22
 *
 * 赎金信
 *
    给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。

    (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)

    注意：

    你可以假设两个字符串均只含有小写字母。

    canConstruct("a", "b") -> false
    canConstruct("aa", "ab") -> false
    canConstruct("aa", "aab") -> true

 */
public class ransom_note_383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0; i<magazine.length(); i++) {
            if (map.containsKey(magazine.charAt(i))) {
                map.put(magazine.charAt(i), map.get(magazine.charAt(i))+1);
            } else {
                map.put(magazine.charAt(i), 1);
            }
        }
        for (int i=0; i<ransomNote.length(); i++) {
            if (!map.containsKey(ransomNote.charAt(i)) || map.get(ransomNote.charAt(i)) == 0) {
                return false;
            } else {
                map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i))-1);
            }
        }
        return true;
    }
}
