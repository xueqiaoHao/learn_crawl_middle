package Spider.Middle.pojos.entity;

import java.text.ParseException;
import java.util.Date;

import Spider.Middle.utils.DateUtil;

/**
@author Griezmann
@version 2019年2月2日下午3:04:24
*/
public class NewsItemEntity {
	private String title;
	private String postTimeString;
	private String sourceURL;
	private Date insertDate;
	private Date postDateObj;
	
	public NewsItemEntity(String title, String postTimeString, String sourceURL) throws ParseException {
		super();
		this.title = title;
		this.postTimeString = postTimeString;
		this.sourceURL = sourceURL;
		//手动完成insertDate和postDateObj
		this.insertDate=DateUtil.getDate();
		this.postDateObj=DateUtil.formatStringToDate(postTimeString);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostTimeString() {
		return postTimeString;
	}
	public void setPostTimeString(String postTimeString) {
		this.postTimeString = postTimeString;
	}
	public String getSourceURL() {
		return sourceURL;
	}
	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getPostDateObj() {
		return postDateObj;
	}
	public void setPostDateObj(Date postDateObj) {
		this.postDateObj = postDateObj;
	}
	@Override
	public String toString() {
		return "NewsItemEntity [title=" + title + ", postTimeString=" + postTimeString + ", sourceURL=" + sourceURL
				+ ", insertDate=" + insertDate + ", postDateObj=" + postDateObj + "]";
	}
	
}
