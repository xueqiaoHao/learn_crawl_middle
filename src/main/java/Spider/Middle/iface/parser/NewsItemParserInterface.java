package Spider.Middle.iface.parser;

import java.util.List;

import Spider.Middle.pojos.entity.NewsItemEntity;

/**
@author Griezmann
@version 2019年2月2日下午4:40:09
*/
public interface NewsItemParserInterface {
	public List<NewsItemEntity> parserHtmlSource(String htmlSource) throws Exception;
}
