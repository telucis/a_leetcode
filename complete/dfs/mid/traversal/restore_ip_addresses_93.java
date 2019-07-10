package complete.dfs.mid.traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 复原IP地址
 *
    给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

    示例:

    输入: "25525511135"
    输出: ["255.255.11.135", "255.255.111.35"]

 */
public class restore_ip_addresses_93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        for (int i=1; i<4 && i<len-2; i++) {
            for (int j=i+1; j<i+4 && j<len-1; j++) {
                for (int k=j+1; k<j+4 && k<len; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if (valid(s1) && valid(s2) && valid(s3) && valid(s4)) {
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }

    private boolean valid(String s) {
        if (s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.valueOf(s)>255) {
            return false;
        }
        return true;
    }
}
