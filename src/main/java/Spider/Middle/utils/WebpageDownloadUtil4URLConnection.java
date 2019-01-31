package Spider.Middle.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 用于下载给定任意网址对应的html代码
@author hao
@date 2018年12月23日下午2:01:25
**/
public class WebpageDownloadUtil4URLConnection {
	public static String download(String url,String defaultCharset) throws IOException{ 
		//第1步、声明最终的HTML源码变量
		String htmlSource=null;
		//第2步、将url得UrlConnection拿到
		URLConnection urlConnection=IOUtil.getUrlConnection(url);
		//第3步、将流转化为字节数组
		byte[] contentByteArray=IOUtil.convertInputStreamToByteArray(urlConnection.getInputStream());
		//第4步、从httpheader中获取编码，如果拿到则为最终网页编码
		String findCharset=WebCharsetDetectorUtil.getCharsetByHttpHeader(urlConnection);
		//第5步、编码逻辑判断
		 if(findCharset==null) {	 
			  //将字符数组转为字符串
			  htmlSource=new String(contentByteArray,defaultCharset);  
			  findCharset=WebCharsetDetectorUtil.getCharsetByhtmlSource(htmlSource);
		  }
		 //无论如何都得有个编码
		 findCharset=(findCharset==null ? defaultCharset:findCharset);
		  if(htmlSource==null || findCharset!=defaultCharset ) {
			  htmlSource=new String(contentByteArray,findCharset);
		  }
		  return htmlSource;
//		charset=WebCharsetDetectorUtil.getCharset(url);
//		System.out.println(charset);
//		BufferedReader br=IOUtil.getbr(url, charset);
//		int linecounter=0;
//		StringBuilder stringbuilder=new StringBuilder();
//		String line=null;
//		while((line=br.readLine())!=null){
//			if(linecounter>0){
//				stringbuilder.append(StaticValue.sep_next_line);
//			}
//			stringbuilder.append(line);
//			linecounter++;
//		}
//		br.close();
//		return stringbuilder.toString();
	}
	public static void main(String[] args) throws IOException {
		String url="http://news.youth.cn/gn/";
//		String url="https://www.baidu.com";
		String htmlsource=download(url,"utf-8");
		System.out.println(htmlsource);
	}
}
