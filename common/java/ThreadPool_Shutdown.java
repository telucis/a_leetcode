package common.java;

/**
 * @author karl.wy
 * @date 2019/07/24
 *
 * 验证：
 * shutdown方法：平滑的关闭ExecutorService，当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。
 *   当所有已提交任务执行完毕，线程池即被关闭。
 *
 * awaitTermination方法：接收timeout和unit两个参数，用于设定超时时间及单位。当等待超过设定时间时，会监测ExecutorService是否已经关闭，
 *   若关闭则返回true，否则返回false。一般情况下会和shutdown方法组合使用。
 *
 * shutdown调用后，不可以再submit新的task，已经submit的将继续执行。
 * shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list
 *
 */
public class ThreadPool_Shutdown {

}
