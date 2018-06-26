package cn.com.nxz.simples.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author buf
 *
 */
public class AtomicIntegerTest {
	public static final AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println(AtomicIntegerTest.atomicInteger.getAndIncrement());
		}
	}

}
