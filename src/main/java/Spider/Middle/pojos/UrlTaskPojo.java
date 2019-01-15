package Spider.Middle.pojos;
/**
 * 对url任务的封装类
 *   
@author hao
@date 2018年12月16日下午8:52:52

**/
public class UrlTaskPojo {
	private String title;
	private String url;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public UrlTaskPojo() {
		
	}
	public UrlTaskPojo(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	@Override
	public String toString() {
		return "UrlTaskPojo [title=" + title + ", url=" + url + "]";
	}
	
}
