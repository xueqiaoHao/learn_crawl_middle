package Spider.Middle.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Spider.Middle.iface.parser.NewsItemParserInterface;
import Spider.Middle.pojos.entity.NewsItemEntity;
import Spider.Middle.utils.RegexUtil;

/**
@author Griezmann
@version 2019年2月2日下午5:41:15
*/
public class NewsItemParser4JsoupImpl implements NewsItemParserInterface{

	@Override
	public List<NewsItemEntity> parserHtmlSource(String htmlSource) throws Exception {
		//将htmlsource持久化保存到集合中
				List<NewsItemEntity> newsItemList=new ArrayList<NewsItemEntity>();
				
				/**
				 * 进行全面的正则解析
				 * **/		

				//拿到完整标题部分的所有信息
				String htmlSourceRegex="<ul class=\"tj3_1\">([\\s\\S]*?)</ul>";
				String ulContent=RegexUtil.getMatchText(htmlSource, htmlSourceRegex, 0);
//				System.out.println(ulContent);
				//从标题部分的所有信息中抽取出一个个我们需要的title,先拿出每一个行
				String lineRegex="<li>([\\s\\S]*?)</li>";
//				String title=RegexUtil.getMatchText(ulContent, titleRegex, 0);
				Pattern pattern=Pattern.compile(lineRegex);
				Matcher matcher=pattern.matcher(ulContent);
				NewsItemEntity newsItemEntity=null;
				while(matcher.find()) {
//					System.out.println(matcher.group(0));
					String lineContent=matcher.group(0);
//					第一步、在每行内容中拿出最后的标题
					
					String titleRegex="<a[\\s\\S]*?>([\\s\\S]*?)</a>";
					String title=RegexUtil.getMatchText(lineContent, titleRegex, 1);
//					System.out.println(title);
					
					//第二部、获取链接
					String hrefRegex="<a href=\"([\\s\\S]*?)\">";
					String href=RegexUtil.getMatchText(lineContent, hrefRegex, 1);
//					System.out.println(href);
					
					//第三步、获取发布时间
					String posttimeRegex="<font>([\\s\\S]*?)</font>";
					String posttime=RegexUtil.getMatchText(lineContent, posttimeRegex, 1);
//					System.out.println(posttime);
					newsItemEntity=new NewsItemEntity(title, posttime, href);
					newsItemList.add(newsItemEntity);
				}
				
				
				
				return newsItemList;
	}
	
}
