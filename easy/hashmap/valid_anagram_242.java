package easy.hashmap;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 有效的字母异位词
 */
public class valid_anagram_242 {

    public boolean isAnagram(String s, String t) {
        int[] x = new int[26];
        int[] y = new int[26];
        for (int i=0; i<s.length(); i++) {
            x[s.charAt(i) - 'a']++;
        }
        for (int i=0; i<t.length(); i++) {
            y[t.charAt(i) - 'a']++;
        }
        for (int i=0; i<26; i++) {
            if (x[i] != y[i]) {
                return false;
            }
        }
        return true;
    }
}
