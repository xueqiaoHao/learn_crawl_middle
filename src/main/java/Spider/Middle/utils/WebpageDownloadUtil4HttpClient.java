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


import Spider.Middle.iface.download.DownloadInterface;




/**
@author hao
@date 2019年1月14日下午9:46:20
**/
public class WebpageDownloadUtil4HttpClient implements DownloadInterface{
	
	public static String parseEntity(HttpEntity entity,String defaultCharset) throws IOException {
		
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
	public static String downloadStatic(String url) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String htmlSource = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				HttpEntity entity1 = response1.getEntity();
				htmlSource = parseEntity(entity1, StaticValue.defaultencoding);
				EntityUtils.consume(entity1);
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
		return htmlSource;
	}

	@Override
	public String download(String url) {
		try {
			return downloadStatic(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
//		String url="http://news.youth.cn/gn/";
//    	String url="https://www.baidu.com/";
    	String url="https://tieba.baidu.com/f?ie=utf-8&kw=%E9%80%9A%E4%BF%A1%E5%90%A7&fr=search";
		DownloadInterface download=new WebpageDownloadUtil4HttpClient();
		String htmlsource=download.download(url);
		System.out.println(htmlsource);
        }
}
