package Spider.Middle.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
@author hao
@date 2018年12月16日上午11:14:35

读取配置文件下的工具类，及支持读取classpath下的又支持读取外置配置文件
**/
public class ReadConfigUtil {
	
	//初始化javase自带的配置文件读取工具类
	private static Properties configObj=new Properties();
	//读取配置文件内容的方法
	public  ReadConfigUtil(String filepath) throws IOException {
		File configFile=new File(filepath);
		InputStream is=null;
		Reader reader=null;
		if(configFile.exists()) {
			is=new FileInputStream(configFile);
		}else {
			is=ReadConfigUtil.class.getClassLoader().getResourceAsStream(filepath);
		}
		reader=new InputStreamReader(is);
		configObj.load(reader);
		reader.close();
//		System.out.println(configObj.getProperty("initConsumerNumber"));
	}
	public  String getValue(String key) {
		return configObj.getProperty(key);
			
	}
	public static void main(String[] args) throws Exception {
		String filepath="spider.properties"; 
		ReadConfigUtil readConfigUtil=new ReadConfigUtil(filepath);
		System.out.println(readConfigUtil.getValue("init_ConsumerNumber"));
	}

}
