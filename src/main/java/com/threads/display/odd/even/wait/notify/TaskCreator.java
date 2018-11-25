package com.threads.display.odd.even.wait.notify;

public class TaskCreator {

	public static void main(String[] args) throws InterruptedException {
		int limit = 20;
		Dashboard dashboard = new Dashboard();
		Thread even = new Thread(new Task(dashboard, limit, true), "Even");
		Thread odd = new Thread(new Task(dashboard, limit, false), "Odd");
		even.start();
		odd.start();
		even.join();
		odd.join();
	}
}
