package Spider.Middle.download;

import java.util.ArrayList;
import java.util.List;

/**
@author Griezmann
@version 2019年1月31日下午7:21:24
*/
public class DownloadManager {
	public static void main(String[] args) throws InterruptedException {
		int consumerNumber=3;
		//引入线程组
//		ThreadGroup threadGroup=new ThreadGroup("spider_group");
		//不使用线程组，转而去直接利用list集合
		List<Runnable> runnableList=new ArrayList<Runnable>();
		for (int i=1;i<=consumerNumber;i++) {
			DownloadRunnable downloadrunnable=new DownloadRunnable("download_consumer_"+i);
			//将线程组引入线程中
//			new Thread(threadGroup,downloadrunnable,"thread_"+i).start();
			new Thread(downloadrunnable,"thread_"+i).start();
			runnableList.add(downloadrunnable);
		}
		Thread.sleep(1000);
		for (Runnable runnable : runnableList) {
			DownloadRunnable temprunnable=(DownloadRunnable)runnable;
			temprunnable.setEnableRunningFlag(false);
			System.out.println(temprunnable.getName());
		}
		
//		Thread[] threadArray=new Thread[threadGroup.activeCount()];
//		for(int i=1;i<=3;i++) {
//			//检查活跃的线程数
//			System.out.println(threadGroup.activeCount());
//			Thread.sleep(3000);
//			//将线程组中的元素取出并放入list集合
//			threadGroup.enumerate(threadArray);
//			for (Thread thread : threadArray) {
////				System.out.println(thread.getName());
//				//以不太优雅的方式结束线程
//				thread.stop();
//			}
//		}
		System.out.println("开启线程下载组完成");
	}
}
