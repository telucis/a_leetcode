package easy.string;

/**
 * @author karl.wy
 * @date 2019/04/22
 *
 * 学生出勤记录 I
 *
    给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：

    'A' : Absent，缺勤
    'L' : Late，迟到
    'P' : Present，到场
    如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。

    你需要根据这个学生的出勤记录判断他是否会被奖赏。

    示例 1:

    输入: "PPALLP"
    输出: True
    示例 2:

    输入: "PPALLL"
    输出: False

 */
public class student_attendance_record_i_551 {

    public boolean checkRecord(String s) {
        int absent = 0, late = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == 'A') {
                if (absent == 1) {
                    return false;
                } else {
                    absent = 1;
                }
                late = 0;
            } else if (s.charAt(i) == 'L') {
                if (late == 2) {
                    return false;
                } else {
                    late++;
                }
            } else {
                late = 0;
            }
        }
        return true;
    }
}
