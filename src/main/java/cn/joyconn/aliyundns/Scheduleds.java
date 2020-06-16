package cn.joyconn.aliyundns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Eric.Zhang on 2017/2/6.
 */
@Component
public class Scheduleds {


    @Autowired
    Dnshandle dnsHandle;


    //region 使用样例

//    /**
//     * fixedRate就是每多次分钟一次，不论你业务执行花费了多少时间。我都是1分钟执行1次，
//     */
//    @Scheduled(fixedDelay= (60 * 1000))
//    public void executeFileDownLoadTask1() {
//        if(scheduledEnable){
//            // 间隔2分钟,执行任务
//            Thread current = Thread.currentThread();
//            System.out.println("定时任务1:"+current.getId());
//            LogHelper.logger("note").info("ScheduledTest.executeFileDownLoadTask 定时任务1:"+current.getId()+ ",name:"+current.getName());
//        }
//
//    }
//
   /**
    * fixedDelay是当任务执行完毕后1分钟在执行
    */
   @Scheduled(fixedRate= (60 * 1000))
   public void executeFileDownLoadTask2() {
       dnsHandle.eachDomain();

   }
//
//    /**
//     * 比如是每天的3点15分执行，那么我们就需要用另外一种方式
//     */
//    @Scheduled(cron="0 0/2 * * * ?")
//    public void executeFileDownLoadTask3() {
////           * * 第一位，表示秒，取值0-59
////                * 第二位，表示分，取值0-59
////                * 第三位，表示小时，取值0-23
////                * 第四位，日期天/日，取值1-31
////                * 第五位，日期月份，取值1-12
////                * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
////        另外：1表示星期天，2表示星期一。
////     * 第7为，年份，可以留空，取值1970-2099
////                * (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
//////     (?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
//////     (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
//////     (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
//////     (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60    另：*/y，等同于0/y
////        0 0 3 * * ?     每天3点执行
////        0 5 3 * * ?     每天3点5分执行
////        0 5 3 ? * *     每天3点5分执行，与上面作用相同
////        0 5/10 3 * * ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
////        0 10 3 ? * 1    每周星期天，3点10分 执行，注：1表示星期天
////        0 10 3 ? * 1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
//
//        if(scheduledEnable){
//            // 间隔2分钟,执行任务
//            Thread current = Thread.currentThread();
//            System.out.println("定时任务1:"+current.getId());
//            LogHelper.logger("note").info("ScheduledTest.executeFileDownLoadTask 定时任务3:"+current.getId()+ ",name:"+current.getName());
//        }
//
//    }
    //endregion


//    static   Lock updatePointCacheLock = new ReentrantLock();// 锁对象
//    static   Boolean updatePointCacheState = true;// 锁对象
//    /**
//     * 更新缓存中point的值
//     */
//    @Scheduled(fixedRate= (30 * 1000))
//    public void executeResetPointCache() {
//        if(scheduledEnable.equals("yes")&&updatePointCacheState){
//            updatePointCacheLock.lock();
//            updatePointCacheState=false;
//            try {
//                List<TLevelCompanyModel> companyModels =  tLevelCompanyService.selectAll();
//                List<TLevelProjectModel> projectModels = null;
//                List<TLevelRoomModel> roomModels = null;
//                if(companyModels!=null&&companyModels.size()>0){
//                    for(TLevelCompanyModel companyModel : companyModels){
//                        projectModels = levelProjectService.selectByParam(companyModel.getP_companycd());
//                        if(projectModels!=null){
//                            for(TLevelProjectModel projectModel :projectModels){
//                                roomModels = levelRoomService.selectByParam(projectModel.getP_companycd(),projectModel.getP_projectcd());
//                                if(roomModels!=null){
//                                    for(TLevelRoomModel roomModel :roomModels){
//                                        tConfigPointService.resetPointCache(roomModel.getP_companycd(),roomModel.getP_projectcd(),roomModel.getP_roomcd());
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }catch (Exception ex)
//            {}
//            finally {
//                updatePointCacheLock.unlock();
//                updatePointCacheState=true;
//            }
//        }
//
//    }


    //endregion
}
