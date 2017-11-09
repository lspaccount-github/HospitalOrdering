package com.cn.ssm.se.thread;

public class ThreadDemo1 {
	
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("thread1");
				}
			}
		}).start();
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 1000; i++) {
						System.out.println("thread3");
					}
				}
			}).join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("thread2");
				}
			}
		}).start();

		for (int i = 0; i < 1000; i++) {
			System.out.println("main");
		}
	}
	

}
