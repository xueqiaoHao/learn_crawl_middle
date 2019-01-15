package Spider.Middle.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
@author hao
@date 2019年1月3日下午10:29:42
**/
public class RegexUtil {
	public static String getMatchText(String input,String regex,int groupid){
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(input);
		if(matcher.find()){
			return matcher.group(groupid);
		}
		return null;
	}	
	public static void main(String[] args) {
		String line="<meta http-equiv=\"content-type\" content=\"text/html; charset=gb2312\" />";
		String regex="charset=([\\s\\S]*)\"";
		String result=getMatchText(line, regex,1);
		System.out.println(result);
		
	}
}
