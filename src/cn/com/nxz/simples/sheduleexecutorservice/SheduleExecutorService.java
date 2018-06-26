package cn.com.nxz.simples.sheduleexecutorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时器sheduleExecutorService
 * @author buf
 * url:https://blog.csdn.net/u011315960/article/details/71422386
 * todo:使用scheduleAtFixedRate()方法实现周期性执行,按照上一次任务的发起时间计算下一次任务的开始时间
 */
public class SheduleExecutorService {
	public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run "+ System.currentTimeMillis());
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

}
