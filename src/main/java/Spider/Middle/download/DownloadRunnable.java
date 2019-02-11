package Spider.Middle.download;

import java.io.IOException;

import org.apache.log4j.Logger;

import Spider.Middle.ui.UIManager;

import Spider.Middle.pojos.UrlTaskPojo;
import Spider.Middle.schedule.TaskScheduleManager;
import Spider.Middle.utils.SystemConfigParas;
import Spider.Middle.utils.WebpageDownloadUtil4HttpClient;

/**
@author Griezmann
@version 2019年1月29日下午8:39:46
*/
public class DownloadRunnable implements Runnable{
	//使用log4j
	Logger log=Logger.getLogger(DownloadRunnable.class);
	
	private String name;
	private boolean enableRunningFlag=true;
	@Override
	public void run() {
		while(enableRunningFlag) {
			UrlTaskPojo taskPojo=TaskScheduleManager.take();
			if(taskPojo!=null) {
				try {
					String htmlSource=WebpageDownloadUtil4HttpClient.download(taskPojo.getUrl());
					if(htmlSource!=null) {
						//第一步、打印下载出来的数据
						//	System.out.println(htmlSource);
						//第二步、进入解析环节
						log.info(this.name+"进入解析环节");
					}
					else {
						log.info(this.name+"下载出错");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				log.info(this.name+"没有待采集的任务，线程将等待"+SystemConfigParas.once_sleep_time_for_empty_task/1000+"s");
				try {
					Thread.sleep(SystemConfigParas.once_sleep_time_for_empty_task);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				log.info(this.name+"本次睡眠结束");
			}
					}
			enableRunningFlag=false;
			log.info(this.name+"线程run方法即将结束");
		
	}
	
	public boolean isEnableRunningFlag() {
		return enableRunningFlag;
	}

	public void setEnableRunningFlag(boolean enableRunningFlag) {
		this.enableRunningFlag = enableRunningFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DownloadRunnable(String name) {
		this.name=name;
	}
	public static void main(String[] args) throws Exception {
		DownloadRunnable downloadrunnable=new DownloadRunnable("runnable_1");
		UIManager.addSeedUrlToTaskSchedule();
		new Thread(downloadrunnable).start();
		
	}
}
