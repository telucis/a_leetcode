package complete.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/22
 *
 * 前缀和后缀搜索
 *
    给定多个 words，words[i] 的权重为 i 。

    设计一个类 WordFilter 实现函数WordFilter.f(String prefix, String suffix)。这个函数将返回具有前缀 prefix 和后缀suffix 的词的最大权重。如果没有这样的词，返回 -1。

    例子:

    输入:
    WordFilter(["apple"])
    WordFilter.f("a", "e") // 返回 0
    WordFilter.f("b", "") // 返回 -1
    注意:

    words的长度在[1, 15000]之间。
    对于每个测试用例，最多会有words.length次对WordFilter.f的调用。
    words[i]的长度在[1, 10]之间。
    prefix, suffix的长度在[0, 10]之前。
    words[i]和prefix, suffix只包含小写字母。

 */
public class prefix_and_suffix_search_745 {

    /**
     * method 1
     *
         WordFilter: Time = O(NL^2)
         f: Time = O(1)
         Space = O(NL^2)
         Note: N is the size of input array and L is the max length of input strings.
     */
    class WordFilter {
        HashMap<String, Integer> map = new HashMap<>();
        public WordFilter(String[] words) {
            for (int w=0; w<words.length; w++) {
                for (int i=0; i<=10&&i<=words[w].length(); i++) {
                    for (int j=0; j<=10&&j<=words[w].length(); j++) {
                        map.put(words[w].substring(0, i) + "#" + words[w].substring(words[w].length()-j), w);
                    }
                }
            }
        }
        public int f(String prefix, String suffix) {
            return (map.containsKey(prefix+"#"+suffix)) ? map.get(prefix+"#"+suffix) : -1;
        }
    }

    /**
     * method 2
     *
         WordFilter: Time = O(NL)
         f: Time = O(N)
         Space = O(NL)
     */
    class WordFilter2 {
        HashMap<String, List<Integer>> mapPrefix = new HashMap<>();
        HashMap<String, List<Integer>> mapSuffix = new HashMap<>();
        public WordFilter2(String[] words) {
            for (int w=0; w<words.length; w++) {
                for (int i=0; i<=10&&i<=words[w].length(); i++) {
                    String s = words[w].substring(0, i);
                    if (!mapPrefix.containsKey(s)) mapPrefix.put(s, new ArrayList<>());
                    mapPrefix.get(s).add(w);
                }
                for(int i = 0; i <= 10 && i <= words[w].length(); i++){
                    String s = words[w].substring(words[w].length() - i);
                    if(!mapSuffix.containsKey(s)) mapSuffix.put(s, new ArrayList<>());
                    mapSuffix.get(s).add(w);
                }
            }
        }
        public int f(String prefix, String suffix) {
            if (!mapPrefix.containsKey(prefix) || !mapSuffix.containsKey(suffix)) return -1;
            List<Integer> p = mapPrefix.get(prefix);
            List<Integer> s = mapSuffix.get(suffix);
            int i=p.size()-1, j=s.size()-1;
            while (i>=0 && j>=0) {
                if (p.get(i) < s.get(j)) j--;
                else if (p.get(i) > s.get(j)) i--;
                else return p.get(i);
            }
            return -1;
        }
    }

    /**
     * method 3
     *
         WordFilter: Time = O(1)
         f: Time = O(NL)
         Space = O(1)
     */
    class WordFilter3 {
        String[] input;
        public WordFilter3(String[] words) {
            input = words;
        }
        public int f(String prefix, String suffix) {
            for (int i=input.length-1; i>=0; i--) {
                if (input[i].startsWith(prefix) && input[i].endsWith(suffix)) return i;
            }
            return -1;
        }
    }

    /**
     * method 4
     *

     */
    class WordFilter4 {
        class TrieNode{
            String word;
            TrieNode[] children;
            TrieNode(){
                word=null;
                children= new TrieNode[26];
            }
        }

        Map<String,Integer> map;
        TrieNode root;
        int ans=-1;
        public WordFilter4(String[] words) {
            map= new HashMap<>();
            root= new TrieNode();
            for(int i=0;i<words.length;i++){ map.put(words[i],i);
                add(words[i],root);
            }

        }

        public void add(String word,TrieNode node){
            char[] wordc= word.toCharArray();
            for(int i=0;i<word.length();i++){
                int c=wordc[i]-'a';
                if(node.children[c]==null) node.children[c]=new TrieNode();
                node=node.children[c];
            }
            node.word=word;
        }

        public int f(String prefix, String suffix) {
            TrieNode node=find(root,prefix);
            if(node==null) return -1;
            ans=-1;
            findf(node,suffix,suffix.length());
            return ans;
        }

        public TrieNode find(TrieNode node, String prefix){
            for(int i=0;i<prefix.length();i++){
                int c=prefix.charAt(i)-'a';
                if(node.children[c]==null) return null;
                node=node.children[c];
            }
            return node;
        }

        public void findf(TrieNode node,String suffix,int len){
            if(node.word!=null){
                int start= node.word.length()-len;
                if(start>=0 && node.word.substring(start).equals(suffix)){
                    if(map.get(node.word)>ans) ans=map.get(node.word);
                }
            }
            for(int i=0;i<26;i++){
                if(node.children[i]!=null) findf(node.children[i],suffix,len);
            }
        }
    }
}
