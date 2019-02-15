package Spider.Middle.utils;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Spider.Middle.iface.download.DownloadInterface;



/**
@author Griezmann
@version 2019年2月2日下午6:30:06
*/
public class JsoupUtil {
	public static enum ContentSelectType{
		OUTER_HTML, HTML, TEXT;
	}
	public static Document getDocument(String htmlSource) {
		return Jsoup.parse(htmlSource); 
	}
	public static Elements getElementsBySelector(String htmlSource,String selector) {
		Document doc=getDocument(htmlSource);
		return doc.select(selector);
	}
	public static List<String> getElementsBySelector(String htmlSource,String selector,ContentSelectType contentselecttype){
		Elements eles=getElementsBySelector(htmlSource,selector);
		List<String> elementList=new ArrayList<String>();
		switch (contentselecttype) {
		case OUTER_HTML:
			for (Element element : eles) {
				elementList.add(element.outerHtml());
			}
			break;
		case HTML:
			for (Element element : eles) {
				elementList.add(element.html());
			}
			break;
		case TEXT:
			for (Element element : eles) {
				elementList.add(element.text());
			}
			break;
		default:
			break;
		}
		return elementList;
	}
	public static List<String> getElementsBySelector(String htmlSource,String selector,String selectAttribute){
		Elements eles=getElementsBySelector(htmlSource,selector);
		List<String> elementList=new ArrayList<String>();
		for (Element ele : eles) {
			elementList.add(ele.attr(selectAttribute));
		}
		return elementList;
	}
	public static String getChileElementValue(Element element,int childIndex,ContentSelectType contentType) {
		String value=null;
		switch (contentType) {
		case OUTER_HTML:
			value=element.child(childIndex).outerHtml();
			break;
		case HTML:
			value=element.child(childIndex).html();
			break;
		case TEXT:
			value=element.child(childIndex).text();
			break;	
		default:
			break;
		}
		return value;
	}
	public static String getAttributeValue(Element element,String attribute) {
		return 	element.attr(attribute);
	}
	public static void main(String[] args) throws Exception {
		//数据准备
		String url="http://news.youth.cn/gn/";
		DownloadInterface httpdownload=new WebpageDownloadUtil4HttpClient();
		String htmlSource=httpdownload.download(url);
		
		//形成htmlsource对应的dom对象
		Document doc=Jsoup.parse(htmlSource);
		
		//解析该dom对象,   称为子代选择器，可以直接选出ul.tj3_1下的li标签内容  ul.tj3_1>li>a
		Elements elements=doc.select("ul.tj3_1>li");
		
//		System.out.println(elements);
		System.out.println("size"+elements.size());
		for (Element element : elements) {
			String title=getChileElementValue(element,1,ContentSelectType.TEXT);
			String postTime=getChileElementValue(element,0,ContentSelectType.TEXT);
			String href=getAttributeValue(element.child(1), "href");
			System.out.println(title);
			System.out.println(postTime);
			System.out.println(href);

		}
	}
}
