package Spider.Middle.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

/**
@author hao
@date 2018年12月26日下午3:17:16
**/
public class WebCharsetDetectorUtil {
	static String meta_charset_regex="charset=[\"]*([\\s\\S]*?)[\">]";
	public static String getCharsetByHttpHeader(URLConnection urlConnection) {
		Map<String,List<String>> headerMap=urlConnection.getHeaderFields();
		Set<String> keyset=headerMap.keySet();
		String findCharset=null;
		//先在http-header中找
		for(String key:keyset){
			if(key!=null&&key.equalsIgnoreCase("Content-Type")){
				//将得到的value=[text/html; charset=GB2312]中的字符集取出
				List<String> valueList=headerMap.get(key);
				String line=valueList.get(0);
				String[] valueArray=line.split(StaticValue.sep_semicolon);
				if(valueArray.length==2){
				String[] charsetArray=valueArray[1].trim().split("=");
				if(charsetArray.length==2){
					findCharset=charsetArray[1];
					break;
				}
				
			}
				}
			}
		return findCharset;
	}
	public static String getCharsetByhtmlSource(String htmlSource) throws IOException {
		//将字符串封装为Reader
		  StringReader sr=new StringReader(htmlSource);
		  BufferedReader br=new BufferedReader(sr); 
			String line=null;
			String findCharset=null;
			while((line=br.readLine())!=null){
				//将读出来的内容转出小写
				line=line.trim().toLowerCase();
				if(line.contains("<meta")){
					findCharset=RegexUtil.getMatchText(line, StaticValue.meta_charset_regex, 1);
				if(findCharset!=null){
//					System.out.println("find_at_meta");
					break;
				}
				}
				//charset一定在head中，如果在head中没有找到，那就是没有，就不要再找了
				else if(line.contains("</head>")){
					break;
				}
				
			}
			br.close();
			return findCharset; 
	}
	public static String getCharset(String url) throws IOException{
		String findCharset=null;
		URLConnection urlconnection=IOUtil.getUrlConnection(url);
		findCharset=getCharsetByHttpHeader(urlconnection);
		//如果httpheader中找不到charset的话
		if(findCharset==null){
			BufferedReader br=IOUtil.getURLBufferedReader(urlconnection, StaticValue.defaultencoding);
			String line=null;
			while((line=br.readLine())!=null){
				//将读出来的内容转出小写
				line=line.trim().toLowerCase();
				if(line.contains("<meta")){
				findCharset=RegexUtil.getMatchText(line, meta_charset_regex, 1);
				if(findCharset!=null){
//					System.out.println("find_at_meta");
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
		
	
		return findCharset;
	}
	public static String getCharsetHttpClient(HttpEntity entity,String defaultCharset) throws IOException {
		  String findCharset=null;
		  findCharset=EntityUtils.getContentCharSet(entity);
		  if(findCharset==null) {
			  System.out.println("come");
			  //将流儿转化为字符数组
			  byte[] contentByteArray=IOUtil.convertInputStreamToByteArray(entity.getContent());
			  //将字符数组转为字符串
			  String htmlSource=new String(contentByteArray,defaultCharset); 
			  System.out.println(contentByteArray.length);
			  //将字符串封装为Reader
			  StringReader sr=new StringReader(htmlSource);
			  BufferedReader br=new BufferedReader(sr); 
				String line=null;
				while((line=br.readLine())!=null){
					//将读出来的内容转出小写
					line=line.trim().toLowerCase();
					if(line.contains("<meta")){
					findCharset=RegexUtil.getMatchText(line, meta_charset_regex, 1);
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
		  System.out.println(findCharset);
		  return findCharset==null? defaultCharset:findCharset ;
	}
	
	public static void main(String[] args) throws IOException {
//		String url="http://news.youth.cn/gn/";
//		String url="https://www.qq.com/";
		String url="https://www.baidu.com/";
//		String url="https://tieba.baidu.com/f?ie=utf-8&kw=%E9%80%9A%E4%BF%A1%E5%90%A7&fr=search";
		String findCharset=getCharset(url);
		
		System.out.println(findCharset);
		System.out.println("done!");
}}
