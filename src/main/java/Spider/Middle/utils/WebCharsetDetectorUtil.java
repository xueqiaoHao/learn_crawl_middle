package Spider.Middle.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
@author hao
@date 2018年12月26日下午3:17:16
**/
public class WebCharsetDetectorUtil {
	static String regex="charset=[\"]*([\\s\\S]*?)[\">]";
	public static String getCharset(String url) throws IOException{
		String findCharset=null;
		URL urlObj=new URL(url);
		URLConnection urlconnection=urlObj.openConnection();
		Map<String,List<String>> headerMap=urlconnection.getHeaderFields();
		Set<String> keyset=headerMap.keySet();
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
				}
				
			}
				}
		//如果httpheader中找不到charset的话
		if(findCharset==null){
			BufferedReader br=IOUtil.getURLBufferedReader(urlconnection, StaticValue.defaultencoding);
			String line=null;
			while((line=br.readLine())!=null){
				//将读出来的内容转出小写
				line=line.trim().toLowerCase();
				if(line.contains("<meta")){
				findCharset=RegexUtil.getMatchText(line, regex, 1);
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
		
	}
		return findCharset;
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
