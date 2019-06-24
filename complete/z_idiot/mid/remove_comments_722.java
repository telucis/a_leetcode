package complete.z_idiot.mid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 删除注释
 *
 */
public class remove_comments_722 {

    public List<String> removeComments(String[] source) {
        boolean isComment = false;
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String s : source) {
            for (int i=0; i<s.length(); i++) {
                if (isComment) {
                    if (s.charAt(i)=='*' && i<s.length()-1 && s.charAt(i+1)=='/') {
                        isComment = false;
                        i++;
                    }
                } else {
                    if (s.charAt(i)=='/' && i<s.length()-1 && s.charAt(i+1)=='/') {
                        break;
                    } else if (s.charAt(i)=='/' && i<s.length()-1 && s.charAt(i+1)=='*') {
                        isComment = true;
                        i++;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
            if (!isComment && sb.length()>0) {
                ans.add(sb.toString());
                sb = new StringBuilder();
            }

        }
        return ans;
    }
}
