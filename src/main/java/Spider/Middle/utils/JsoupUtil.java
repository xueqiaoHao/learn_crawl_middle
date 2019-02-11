package Spider.Middle.utils;

import java.io.IOException;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
@author Griezmann
@version 2019年2月2日下午6:30:06
*/
public class JsoupUtil {
	public static void main(String[] args) throws IOException {
		//数据准备
		String url="http://news.youth.cn/gn/";
		WebpageDownloadUtil4HttpClient download=new WebpageDownloadUtil4HttpClient();
		String htmlSource=download.download(url);
		
		//形成htmlsource对应的dom对象
		Document doc=Jsoup.parse(htmlSource);
		
		//解析该dom对象
		Elements elements=doc.select("ul.tj3_1>li");
		
//		System.out.println(elements);
		System.out.println(elements.size());
		for (Element element : elements) {
			System.out.println(element.outerHtml());
		}
	}
}
