package Spider.Middle.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Spider.Middle.iface.download.DownloadInterface;
import Spider.Middle.iface.parser.NewsItemParserInterface;
import Spider.Middle.pojos.entity.NewsItemEntity;
import Spider.Middle.utils.JsoupUtil;
import Spider.Middle.utils.WebpageDownloadUtil4HttpClient;
import Spider.Middle.utils.JsoupUtil.ContentSelectType;

/**
@author Griezmann
@version 2019年2月2日下午5:41:15
*/
public class NewsItemParser4JsoupImpl implements NewsItemParserInterface{

	@Override
	public List<NewsItemEntity> parserHtmlSource(String htmlSource) throws Exception {
		//将htmlsource持久化保存到集合中
		List<NewsItemEntity> newsItemList=new ArrayList<NewsItemEntity>();
		//第一步，要取得所有的li集合
		String liSelector="ul.tj3_1>li";
		Elements liElements=JsoupUtil.getElementsBySelector(htmlSource, liSelector);
		//遍历li element，通过child和attribute获取3个字段
		NewsItemEntity itemEntity=null;
		String title=null;
		String postTime=null;
		String href=null;
		for (Element element : liElements) {
			 title=JsoupUtil.getChileElementValue(element,1,ContentSelectType.TEXT);
			 postTime=JsoupUtil.getChileElementValue(element,0,ContentSelectType.TEXT);
			 href=JsoupUtil.getAttributeValue(element.child(1), "href");
			 itemEntity=new NewsItemEntity(title, postTime, href);
			 newsItemList.add(itemEntity);
		}
//		for (String string : postTimeList) {
//			System.out.println(string);
//		}
////		
//		for (NewsItemEntity item : newsItemList) {
//			System.out.println(item.toString());
//		}
		return newsItemList;
	}
	public static void main(String[] args) throws Exception {
		// 将数据准备好
			String url = "http://news.youth.cn/gn/";
			DownloadInterface download = new WebpageDownloadUtil4HttpClient();
			String htmlSource = download.download(url);
			NewsItemParserInterface jsoupParse=new NewsItemParser4JsoupImpl();
			
			List<NewsItemEntity> newsItemList=jsoupParse.parserHtmlSource(htmlSource);
			System.out.println(newsItemList);
	}
}
