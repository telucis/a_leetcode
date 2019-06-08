package complete.bit.modOR;

import java.util.*;

/**
 * Created by telucis on 2019/5/12.
 *
 * 重复的DNA序列
 *
     所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

     编写一个函数来查找 DNA 分子中所有出现超多一次的10个字母长的序列（子串）。

     示例:

     输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

     输出: ["AAAAACCCCC", "CCCCCAAAAA"]

 */
public class repeated_dna_sequences_187 {

    public List<String> findRepeatedDnaSequences(String s) {
        return hashInteger(s);
    }

    /**
     * hashset
     */
    public List<String> hashString(String s) {
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }

    /**
     * bit & hashset
     * for(s)set.contain(s[i,i+10])
     * key<<=2; key|=map[s.charAt(j)-'A']
     */
    public List<String> hashInteger(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;

    }
}
