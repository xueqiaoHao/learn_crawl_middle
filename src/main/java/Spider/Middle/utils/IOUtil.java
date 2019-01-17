package Spider.Middle.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * 
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
			is=new FileInputStream(Filepath);
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
	public static BufferedReader getbr(String url,String charset) throws IOException{
		URL urlObj=new URL(url);
		URLConnection urlconnection=urlObj.openConnection();
		BufferedReader br=getURLBufferedReader(urlconnection, charset);
		return br;
	}
	public static BufferedReader getURLBufferedReader(URLConnection urlconnection,String charset) throws IOException{
		InputStream is=urlconnection.getInputStream();
		InputStreamReader isr=new InputStreamReader(is,StaticValue.defaultencoding);
		BufferedReader br=new BufferedReader(isr);
		return br;
	}
	public static byte[] convertInputStreamToByteArray(InputStream is) throws IOException {
		//将输入的字节流转换为字节数组的形式
		byte[] byteBuffer=new byte[4096];
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int readLength=0;
		while((readLength=is.read(byteBuffer))!=-1) {
			bos.write(byteBuffer,0,readLength);
		}
		return bos.toByteArray();
		
	}
	public static void main(String[] args) {
		String Filepath="seeds.txt";
		boolean isclasspath=false;
		String charset=StaticValue.defaultencoding;
		try {
			List<String> linelist=readFileToList(Filepath,isclasspath,charset);
			System.out.println(linelist);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
