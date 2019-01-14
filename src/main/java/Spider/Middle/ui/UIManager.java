package Spider.Middle.ui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Spider.Middle.pojos.UrlTaskPojo;
import Spider.Middle.schedule.TaskScheduleManager;
import Spider.Middle.utils.IOUtil;
import Spider.Middle.utils.StaticValue;

/**
 * @author Hao
 *@date 2018年12月14日下午5:19:41
 *看看commit之后再push的效果
 *该类作为用户接口的管理类，包括种子添加接口，种子添加的方式和不同来源
 *某种程度讲这个是创建任务的类，让调度层有的可调度
 */
public class UIManager {
	public static UrlTaskPojo getRootUrlByDirect(){
		return new UrlTaskPojo("中国青年网-国内新闻","http://news.youth.cn/gn/");
	}
	public static UrlTaskPojo getRootUrlByStaticValue(){
		return new UrlTaskPojo(StaticValue.rootTitle,StaticValue.rootUrl);
	}
	public static List<UrlTaskPojo> getRootUrlBySeedFile(String dataFilepath,boolean isclasspath) throws Exception{
		List<String> linelist=IOUtil.readFileToList(dataFilepath, isclasspath, StaticValue.defaultencoding);
		List<UrlTaskPojo> resultTaskPojo=new ArrayList<UrlTaskPojo>();
		//对list集合进行遍历，每一个都是string类型的对象，这里面拿出来的就是具体的url和它对应的标题
		for(String line:linelist){
			line=line.trim();
			if(line.length()>0&&!line.startsWith("#")){
				String[] columnArray=line.split("\\s");
				if(columnArray.length==2){
					UrlTaskPojo urltaskpojo=new UrlTaskPojo(columnArray[0].trim(),columnArray[1]);
					resultTaskPojo.add(urltaskpojo);
				}
				else{
					System.err.println("错误行："+line);
					throw new Exception("存在不规范错误，请检查" );
			}
		}
	}
		return resultTaskPojo;
}
	//从这里调用taskschedule
	public static void addSeedUrlToTaskSchedule() throws Exception{
		String dataFilePath="seeds.txt";
		List<UrlTaskPojo> urltaskpojo=UIManager.getRootUrlBySeedFile(dataFilePath,false);
		TaskScheduleManager.addUrlPojoList(urltaskpojo);
		
	}
	public static void main(String[] args) throws Exception {
		addSeedUrlToTaskSchedule();
		System.out.println(TaskScheduleManager.todoTaskPojoList.size());
		System.out.println("种子任务已添加到任务管理器中");
	}
}
