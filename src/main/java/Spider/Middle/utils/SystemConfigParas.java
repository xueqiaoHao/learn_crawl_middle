package Spider.Middle.utils;

import java.io.IOException;

/**
@author Griezmann
@version 2019年2月2日上午10:17:03
*/
public class SystemConfigParas {
	//初始化参数读取的工具类实例
	public static ReadConfigUtil configUtil=null;
    static {
    	try {
			configUtil=new ReadConfigUtil("spider.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 集中读取download的相关参数
     * **/    
	public static int init_ConsumerNumber=Integer.parseInt(configUtil.getValue("init_ConsumerNumber"));
	public static int once_sleep_time_for_empty_task=Integer.parseInt(configUtil.getValue("once_sleep_time_for_empty_task"))*1000;
}
