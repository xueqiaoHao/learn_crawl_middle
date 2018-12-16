package Spider.Middle.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
@author hao
@date 2018年12月16日上午11:37:39
**/
public class IOUtil {
	public static List<String> readFileToList(String Filepath,boolean isclasspath,String charset) throws Exception {
		InputStream is=null;
		if(isclasspath){
			is=ReadConfigUtil.class.getClassLoader().getResourceAsStream(Filepath);
		}
		else{
			
		}
		//3.正式进行文件读取
		InputStreamReader isr=new InputStreamReader(is,StaticValue.defaultencoding);
		BufferedReader br=new BufferedReader(isr);
		String line=null;
		List<String> lineList=new ArrayList<String>();
		while((line=br.readLine())!=null){
			lineList.add(line);
		}
		br.close();
		return lineList;
	}
	public static void main(String[] args) {
		String Filepath="seeds.txt";
		boolean isclasspath=true;
		String charset=StaticValue.defaultencoding;
		try {
			List<String> linelist=readFileToList(Filepath,isclasspath,charset);
			System.out.println(linelist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
}
