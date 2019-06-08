package easy.math;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 最大三角形面积
 *
    给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。

    示例:
    输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
    输出: 2
    解释:
    这五个点如下图所示。组成的橙色三角形是最大的，面积为2。


    注意:

    3 <= points.length <= 50.
    不存在重复的点。
    -50 <= points[i][j] <= 50.
    结果误差值在 10^-6 以内都认为是正确答案。

 */
public class largest_triangle_area_812 {

    public double largestTriangleArea(int[][] points) {
        double max = 0;
        for (int i=0; i<points.length-2; i++) {
            for (int j=i+1; j<points.length-1; j++) {
                for (int k=j+1; k<points.length; k++) {
                    if (judgeTriangle(L(points[i], points[j]), L(points[j], points[k]), L(points[k], points[i]))) {
                        max = Math.max(max, S(points[i], points[j], points[k]));
                    }
                }
            }
        }
        return max;
    }
    private double L(int[] x, int[] y) {
        return Math.sqrt(Math.pow(x[0]-y[0], 2) + Math.pow(x[1]-y[1], 2));
    }
    private double S(int[] x, int[] y, int[] z) {
        double a = L(x, y);
        double b = L(y, z);
        double c = L(z, x);
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    private boolean judgeTriangle(double a, double b, double c) {
        if (a+b>c && a+c>b && b+c>a) {
            return true;
        }
        return false;
    }
}
