package cn.com.nxz.simples.threads;

public class ThreadDemo {
	public static void main(String[] args) {
//		new ThreadTest1().start();
//		new ThreadTest1().start();
//		new ThreadTest1().start();
//		new ThreadTest1().start();
        ThreadTest1 test=new ThreadTest1();   
        new Thread(test).start();   
        new Thread(test).start();   
        new Thread(test).start();   
        new Thread(test).start();  
	}
}

class ThreadTest1 extends Thread {
	private int count = 10;

	public void run() {
		while (count > 0) {
			System.out.println(Thread.currentThread().getName() + "   "
					+ count--);
		}
	}
}