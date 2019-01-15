package Spider.Middle.schedule;

import java.util.LinkedList;
import java.util.List;

import Spider.Middle.pojos.UrlTaskPojo;

/**
 * 做任务的调度，决定什么任务先被采集，什么任务后被采集
@author hao
@date 2018年12月17日下午6:33:45
**/
public class TaskScheduleManager {
	//即将进行工作的任务集合
	public static LinkedList<UrlTaskPojo> todoTaskPojoList=new LinkedList<UrlTaskPojo>();
	//读取结束的任务集合
	public static LinkedList<UrlTaskPojo> doneTaskPojoList=new LinkedList<UrlTaskPojo>();
	//向即将进行工作的任务（TODO）集合中增添一个或多个任务对象
	public static void addUrlPojoList(List<UrlTaskPojo> todoAddTaskList){
		todoTaskPojoList.addAll(todoAddTaskList);
	}
	public static void addOneUrlPojo(UrlTaskPojo todoAddTaskPojo){
		todoTaskPojoList.add(todoAddTaskPojo);
	}
	//向已完成工作的任务集合（done）中添加任务对象，此处就应该是一个一个的加入了，完成一个放一个
	public static void adddDoneTaskPojoList(List<UrlTaskPojo> doneAddTaskPojo){
		doneTaskPojoList.addAll(doneAddTaskPojo);
	}
	//将已完成的任务从TODO集合中剔除出去，也是两种方式，单个剔除和整体剔除
	public static void removeoneUrlPojo(UrlTaskPojo todoRemoveTaskPojo){
		todoTaskPojoList.remove(todoRemoveTaskPojo);
	}
	public static void removeUrlPojoList(List<UrlTaskPojo> todoRemoveTaskList){
		todoTaskPojoList.removeAll(todoRemoveTaskList);
	}
	//取出某个任务的方法take，从已知的任务集合中取出要去执行的任务
	//默认呢，我们就让先读到的先去执行，当然也可以设置其他的规则，简单起见，就这么整
	public static UrlTaskPojo take(){
		//要知道，为什么能用pollfirst来取出，因为这是用的linkedlist集合，这个方法就是取出第一个
		UrlTaskPojo urltaskpojo=todoTaskPojoList.pollFirst();
		return urltaskpojo;
	}
}
