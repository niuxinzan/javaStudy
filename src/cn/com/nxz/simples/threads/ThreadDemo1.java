package cn.com.nxz.simples.threads;

public class ThreadDemo1 {
	    public static void main(String []args)   
	    {   
	        ThreadTest test=new ThreadTest();   
	        new Thread(test).start();   
	        new Thread(test).start();   
	        new Thread(test).start();   
	        new Thread(test).start();   
//			new ThreadTest().start();
//			new ThreadTest().start();
//			new ThreadTest().start();
//			new ThreadTest().start();
	    }   
	}   
class ThreadTest implements Runnable   
{   
    private int count=10;   
    public void run()   
    {   
        while(count>0)   
        {   
            System.out.println(Thread.currentThread().getName()+"   "+count--);   
        }   
    }   
}  

