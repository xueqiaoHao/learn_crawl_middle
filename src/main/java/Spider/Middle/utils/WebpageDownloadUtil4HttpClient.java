package Spider.Middle.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


import org.apache.http.HttpEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;




/**
@author hao
@date 2019年1月14日下午9:46:20
**/
public class WebpageDownloadUtil4HttpClient {
	
	public static String parseResponse(HttpEntity entity,String defaultCharset) throws IOException {
		
		  String findCharset=null;
		//将流儿转化为字符数组
		  byte[] contentByteArray=IOUtil.convertInputStreamToByteArray(entity.getContent());
		  findCharset=EntityUtils.getContentCharSet(entity);
		  String htmlSource=null;
		  if(findCharset==null) {	 
			  //将字符数组转为字符串
			  htmlSource=new String(contentByteArray,defaultCharset);  
			  //将字符串封装为Reader
			  StringReader sr=new StringReader(htmlSource);
			  BufferedReader br=new BufferedReader(sr); 
				String line=null;
				while((line=br.readLine())!=null){
					//将读出来的内容转出小写
					line=line.trim().toLowerCase();
					if(line.contains("<meta")){
					findCharset=RegexUtil.getMatchText(line, StaticValue.meta_charset_regex, 1);
					if(findCharset!=null){
//						System.out.println("find_at_meta");
						break;
					}
					}
					//charset一定在head中，如果在head中没有找到，那就是没有，就不要再找了
					else if(line.contains("</head>")){
						break;
					}
					
				}
				br.close();
		  }
		  //无论如何都得有个编码
		  findCharset=(findCharset==null ? defaultCharset:findCharset);
		  if(htmlSource==null || findCharset!=defaultCharset ) {
			  htmlSource=new String(contentByteArray,findCharset);
		  }
		  return htmlSource;
	}
	
	//封装httpclient自己的download方法
	public static String download(String url) throws IOException {
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 String htmlsource=null;  
		 try {
	        	
	            HttpGet httpGet = new HttpGet(url);
	            CloseableHttpResponse response1 = httpclient.execute(httpGet);
	            try {
//	                System.out.println(response1.getStatusLine());
	                HttpEntity entity1 = response1.getEntity();
	                htmlsource=parseResponse(entity1, StaticValue.defaultencoding);
//	                System.out.println(htmlsource);
	                EntityUtils.consume(entity1);
	                
	            } finally {
	                response1.close();
	            }        
	        }
	          
	         finally {
	            httpclient.close();
	        }
	        return htmlsource;
	}
	public static void main(String[] args) throws Exception {
		String url="http://news.youth.cn/gn/";
//    	String url="https://www.baidu.com/";
//    	String url="https://www.163.com/";
		String htmlsource=download(url);
		System.out.println(htmlsource);
        }
}
