package Spider.Middle.parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Spider.Middle.iface.parser.NewsItemParserInterface;
import Spider.Middle.pojos.entity.NewsItemEntity;
import Spider.Middle.utils.WebpageDownloadUtil4HttpClient;

/**
@author Griezmann
@version 2019年2月2日上午11:04:52
*/
public class HtmlParserManager {
	//多态使用，将解析的接口只想具体实现接口的实例
	public static NewsItemParserInterface newsItemParserInterface=new NewsItemParser4RegexImpl();
	public static List<NewsItemEntity> parserHtmlSource(String htmlSource) throws Exception{
		return newsItemParserInterface.parserHtmlSource(htmlSource);
	}
	public static void main(String[] args) throws Exception {
		String url="http://news.youth.cn/gn/";
		String htmlSource=WebpageDownloadUtil4HttpClient.download(url);
		
		List<NewsItemEntity> newsItemList=parserHtmlSource(htmlSource);
		for (NewsItemEntity ItemEntity : newsItemList) {
			System.out.println(ItemEntity);
		}
		
		System.out.println("done");
	}
}
