> BM

```java
    /**
     * O(M+N)
     * 可以跳跃search,不适合characterset太小的状况
     * BacCharacter rule & Good Suffix rule
     */
    public class BM {
        public static int search(String ts, String ps) {
            int m = ts.length(), n = ps.length();
            int skip;
            int[] right = getRight(ps);
            for (int i=0; i<m-n; i+=skip) {
                skip = 0;
                for (int j=n-1; j>=0; j--) {
                    if (ps.charAt(j) != ts.charAt(i+j)) {
                        skip = j - right[ts.charAt(i+j)];
                        if (skip < 1) skip = 1;
                        break;
                    }
                }
                if (skip==0) return i;
            }
            return -1;
        }
        public static int[] getRight(String ps) {
            int[] right = new int[256];
            for (int i=0; i<256; i++) right[i]=-1;
            for (int j=0; j<ps.length(); j++) right[ps.charAt(j)]=j;
        }
    }
```

```java
    public class BM {
        public static int pattern(String pattern, String target) {
            int tLen = target.length();
            int pLen = pattern.length();
            if (pLen > tLen) return -1;
            int[] bad_table = build_bad_table(pattern);
            int[] good_table = build_good_table(pattern);

            for (int i=pLen-1, j; i<tLen;) {
                System.out.println("跳跃位置：" + i)
                for (j=pLen-1; target.charAt(i)==pattern.charAt(j); i--, j--) {
                    if (j==0) return i;
                }
                i += Math.max(good_table[pLen-j-1], bad_table[target.charAt(i)]);
            }
            return -1;
        }
        public static int[] build_bad_table(String pattern) {
            final int table_size = 256;
            int[] bad_table = new int[table_size];
            int pLen = pattern.length();
            for (int i=0; i<bad_table.length; i++) bad_table[i] = pLen;
            for (int i=0; i<pLen-1; i++) {
                int k = pattern.charAt(i);
                bad_table[k] = pLen-1-i;
            }
            return bad_table;
        }
        public static int[] build_good_table(String pattern) {
            int pLen = pattern.length();
            int[] good_table = new int[pLen];
            int lastPrefixPosition = pLen;

            for (int i=pLen-1; i>=0; --i) {
                if (isPrefix(pattern, i+1)) lastPrefixPosition = i+1;
                good_table[pLen-1-i] = lastPrefixPosition-i+pLen-1;
            }
            for (int i=0; i<pLen-1; ++i) {
                int slen = suffixLength(pattern, i);
                good_table[slen] = pLen-1-i+slen;
            }
            return good_table;
        }
        /**
         * 前缀匹配
         */
        private static boolean isPrefix(String pattern, int p) {
            int len = pattern.length();
            for (int i=p, j=0; i<len; ++i, ++j) {
                if (pattern.charAt(i) != pattern.charAt(j)) return false;
            }
            return true;
        }
        /**
         * 后缀匹配
         */
        private static int suffixLength(String pattern, int p) {
            int pLen = pattern.length();
            int len = 0;
            for (int i=p, j=pLen-1; i>=0 && pattern.charAt(i)==pattern.charAt(j); i--, j--) {
                len+=1;
            }
            return len;
        }
    }
```

> KMP

```java
    /**
     * 核心：pattern String 的 next数组求解
     */
    public class KMP {
        public static int search(String ts, String ps) {
            char[] t = ts.toCharArray();
            char[] p = ps.toCharArray();
            int i=0;    // 主串的位置
            int j=0;    // 模式串的位置
            int[] next = getNext(ps);
            while (i<t.length && j<p.length) {
                if (j==-1 || t[i]==p[j]) {  // 当j为-1时，要移动的是i，当然j也要归0
                    i++;
                    j++;
                } else {
                    // i=i-j+1  i不需要回溯了
                    j = next[j];    // j回到指定位置
                }
            }
            if (j==p.length) return i-j;
            else return -1;
        }
        /**
         * next[j] : 当i与j不同时，j应该移动到的位置
         * 当 P[k]==P[j] 时， 有 next[j+1] == next[j]+1
         * 当 P[k]!=P[j] 时， k = next[k]
         */
        public static int[] getNext(String ps) {
            char[] p = ps.toCharArray();
            int[] next = new int[p.length];
            next[0] = -1;   // 初始条件next[0]=-1,next[1]=0
            int j=0;
            int k=-1;
            while (j < p.length-1) {
                if (k==-1 || p[j]==p[k]) {
                    next[++j] = ++k; //若相等，则j的下一个跳到k的下一个处
                    if (p[j] == p[k]) {
                        next[j] = next[k];//若继续相等，则再往前跳
                    }
                }
                else k = next[k];   //不相等则移回到k处
            }
            return next;
        }
    }
```

