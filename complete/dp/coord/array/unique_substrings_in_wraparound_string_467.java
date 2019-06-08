package complete.dp.coord.array;

/**
 * @author karl.wy
 * @date 2019/05/17
 *
 * 环绕字符串中唯一的子字符串
 *
    把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

    现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。

    注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。



    示例 1:

    输入: "a"
    输出: 1
    解释: 字符串 S 中只有一个"a"子字符。


    示例 2:

    输入: "cac"
    输出: 2
    解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.


    示例 3:

    输入: "zab"
    输出: 6
    解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.

 */
public class unique_substrings_in_wraparound_string_467 {

    /**
     * count[26]
     * for(p.len) if(p.char(i)==(p.char(i-1)+1 || p.char(i-1)-25)) max++, else max=1, count[index]=Math.max(count[index], max)
     *
     *
         After failed with pure math solution and time out with DFS solution, I finally realized that this is a DP problem...
         The idea is, if we know the max number of unique substrings in p ends with 'a', 'b', ..., 'z', then the summary of them is the answer. Why is that?

         The max number of unique substring ends with a letter equals to the length of max contiguous substring ends with that letter. Example "abcd", the max number of unique substring ends with 'd' is 4, apparently they are "abcd", "bcd", "cd" and "d".
         If there are overlapping, we only need to consider the longest one because it covers all the possible substrings. Example: "abcdbcd", the max number of unique substring ends with 'd' is 4 and all substrings formed by the 2nd "bcd" part are covered in the 4 substrings already.
         No matter how long is a contiguous substring in p, it is in s since s has infinite length.
         Now we know the max number of unique substrings in p ends with 'a', 'b', ..., 'z' and those substrings are all in s. Summary is the answer, according to the question.
     */
    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int maxLengthCur = 0;
        for (int i=0; i<p.length(); i++) {
            if (i>0 && (p.charAt(i)==p.charAt(i-1)+1 || p.charAt(i)+25==p.charAt(i-1))) {
                maxLengthCur++;
            }
            else {
                maxLengthCur=1;
            }
            int index = p.charAt(i)-'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }
        int sum=0;
        for (int i=0; i<26; i++) sum+=count[i];
        return sum;
    }
}
