package complete.design.system;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by telucis on 2019/5/12.
 *
 * TinyURL 的加密与解密
 *
     TinyURL是一种URL简化服务， 比如：当你输入一个URL https://leetcode.com/problems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk.

     要求：设计一个 TinyURL 的加密 encode 和解密 decode 的方法。你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，并且这个TinyURL可以用解密方法恢复成原本的URL。


 */
public class encode_and_decode_tinyurl_535 {

    public class Codec {
        HashMap<String, Integer> urls2code = new HashMap<>();
        HashMap<Integer, String> code2url = new HashMap<>();
        Random r = new Random();
        int key = r.nextInt(10000);
        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            if (!urls2code.containsKey(longUrl)) {
                while (code2url.containsKey(key)) {
                    key = r.nextInt(10000);
                }
                urls2code.put(longUrl, key);
                code2url.put(key, longUrl);
            }
            return "http://tinyurl.com/" + urls2code.get(longUrl);
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return code2url.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
        }
    }
}
