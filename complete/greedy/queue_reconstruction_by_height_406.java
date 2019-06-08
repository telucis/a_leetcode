package complete.greedy;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 根据身高重建队列
 *
    假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

    注意：
    总人数少于1100人。

    示例

    输入:
    [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

    输出:
    [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 */
public class queue_reconstruction_by_height_406 {

    /**
         Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
         For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
         E.g.
         input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
         subarray after step 1: [[7,0], [7,1]]
         subarray after step 2: [[7,0], [6,1], [7,1]]
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b)->a[0]==b[0]?a[1]-b[1]:b[0]-a[0]);
        int[][] res = new int[people.length][2];
        for (int i=0; i<people.length; i++) {
            int pos = people[i][1];
            for (int j=i; j>pos; j--) {
                res[j] = res[j-1];
            }
            res[pos] = people[i];
        }
        return res;
    }
}
