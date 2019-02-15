package CrawlTest;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
@author Griezmann
@version 2019年2月12日上午10:56:03
*/
public class TestJsoup {
	public static void main(String[] args) throws IOException {
//		String html = "<html><head><title>First parse</title></head>"
//				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
//				Document doc = Jsoup.parse(html);
//				System.out.println(doc.title());
		Document doc=Jsoup.connect("http://news.youth.cn/gn/").get();
		System.out.println(doc.head());
			
	}
}
