package Spider.Middle.download;



import java.util.List;

import org.apache.log4j.Logger;
import Spider.Middle.ui.UIManager;
import Spider.Middle.iface.download.DownloadInterface;
import Spider.Middle.parser.HtmlParserManager;
import Spider.Middle.pojos.UrlTaskPojo;
import Spider.Middle.pojos.entity.NewsItemEntity;
import Spider.Middle.schedule.TaskScheduleManager;
import Spider.Middle.utils.SystemConfigParas;
import Spider.Middle.utils.WebpageDownloadUtil4HttpClient;

/**
@author Griezmann
@version 2019年1月29日下午8:39:46
*/
public class DownloadRunnable implements Runnable{
	//使用log4j
	Logger logger=Logger.getLogger(DownloadRunnable.class);
	
	private String name;
	private boolean enableRunningFlag=true;
	@Override
	public void run() {
		while(enableRunningFlag) {
			UrlTaskPojo taskPojo=TaskScheduleManager.take();
			if(taskPojo!=null) {
				try {
					//第一步、先进行下载
					DownloadInterface download=new WebpageDownloadUtil4HttpClient();
					String htmlSource=download.download(taskPojo.getUrl());
					if(htmlSource!=null) {
						
						//	System.out.println(htmlSource);
						//第二步、进入解析环节
						logger.info(this.name+"进入解析环节");
						//解析
						List<NewsItemEntity> newsItemEntityList=HtmlParserManager.parserHtmlSource(htmlSource);
						for (NewsItemEntity newsItemEntity : newsItemEntityList) {
							System.out.println(newsItemEntity);
						}
						logger.info("本页下载解析完成，将进入下一页");
					}
					else {
						logger.info(this.name+"下载出错");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				logger.info(this.name+"没有待采集的任务，线程将等待"+SystemConfigParas.once_sleep_time_for_empty_task/1000+"s");
				try {
					Thread.sleep(SystemConfigParas.once_sleep_time_for_empty_task);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logger.info(this.name+"本次睡眠结束");
			}
					}
			enableRunningFlag=false;
			logger.info(this.name+"线程run方法即将结束");
		
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
