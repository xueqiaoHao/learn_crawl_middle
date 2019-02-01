package Spider.Middle.download;

import java.util.ArrayList;
import java.util.List;

/**
@author Griezmann
@version 2019年1月31日下午7:21:24
*/
public class DownloadManager {
	//引入线程组,线程组初始化
	public static ThreadGroup threadGroup=new ThreadGroup("spider_group");
	public static int initConsumerNumber=3;
	//不使用线程组，转而去直接利用list集合
	public static List<DownloadRunnable> runnableList=new ArrayList<DownloadRunnable>();
	//开启线程的start方法
	public static void start() {
//		List<Runnable> runnableList=new ArrayList<Runnable>();
				for (int i=1;i<=initConsumerNumber;i++) {
					DownloadRunnable downloadrunnable=new DownloadRunnable("download_consumer_"+i);
					//将线程组引入线程中
					new Thread(threadGroup,downloadrunnable,"thread_"+i).start();
					new Thread(downloadrunnable,"thread_"+i).start();
					runnableList.add(downloadrunnable);
				}
	}
	//获取当前活跃的线程数
	public static int getActiveDownloadThread() {
		return threadGroup.activeCount();
	}
	//一共初始了多少线程
	public static int getInitDownloadThreads() {
		return initConsumerNumber;
	}
	
	//关闭所有的线程
	public static void stopAllDownloadThreads() {
		for (DownloadRunnable runnable : runnableList) {
			DownloadRunnable temprunnable=(DownloadRunnable)runnable;
			temprunnable.setEnableRunningFlag(false);
			System.out.println(temprunnable.getName());
		}
	}
	//停止某个runnable对象
	public static void stopOneDownloadThread(DownloadRunnable runnable) {
		runnable.setEnableRunningFlag(false);
	}
	public static void main(String[] args) throws InterruptedException {
		start();
		System.out.println("开启线程下载组完成");
	}
}
