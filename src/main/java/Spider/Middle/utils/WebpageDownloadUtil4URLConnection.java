package Spider.Middle.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 用于下载给定任意网址对应的html代码
@author hao
@date 2018年12月23日下午2:01:25
**/
public class WebpageDownloadUtil4URLConnection {
	public static String download(String url) throws IOException{
		String charset=WebCharsetDetectorUtil.getCharset(url);
		System.out.println(charset);
		BufferedReader br=IOUtil.getbr(url, charset);
		int linecounter=0;
		StringBuilder stringbuilder=new StringBuilder();
		String line=null;
		while((line=br.readLine())!=null){
			if(linecounter>0){
				stringbuilder.append(StaticValue.sep_next_line);
			}
			stringbuilder.append(line);
			linecounter++;
		}
		br.close();
		return stringbuilder.toString();
	}
	public static void main(String[] args) throws IOException {
//		String url="http://news.youth.cn/gn/";
		String url="https://www.baidu.com";
		String htmlsource=download(url);
		System.out.println(htmlsource);
	}
}
