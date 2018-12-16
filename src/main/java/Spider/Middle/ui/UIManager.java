package Spider.Middle.ui;

import Spider.Middle.utils.StaticValue;

/**
 * @author Hao
 *@date 2018年12月14日下午5:19:41
 *
 *该类作为用户接口的管理类，包括种子添加接口，种子添加的方式和不同来源
 */
public class UIManager {
	public String getRootUrlByDirect(){
		return "http://news.youth.cn/gn/";
	}
	public String getRootUrlByStaticValue(){
		return StaticValue.rootUrl;
	}
	public String getRootUrlBySeedFileFromClasspath(){
		return null;
	}
}
