package Spider.Middle.utils;
/**
@author hao
@date 2018年12月14日下午5:40:24

存放项目当中的静态变量
**/
public class StaticValue {
	//根URL的静态常量定义
	public static String rootUrl="http://news.youth.cn/gn/";
	public static String rootTitle="中国青年网-国内新闻";
	//默认编码设定
	public static String defaultencoding="utf-8";
	//设定分隔符
	public static String sep_next_line="\n";
	
	public static String sep_semicolon=";";
	
	public static String sep_equals="=";
	
	public static String meta_charset_regex="charset=[\"]*([\\s\\S]*?)[\">]";
}
