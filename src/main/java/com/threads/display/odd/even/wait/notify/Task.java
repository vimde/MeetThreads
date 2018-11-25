package com.threads.display.odd.even.wait.notify;

public class Task implements Runnable {
	
	private Dashboard dashboard;
	private int limit;
	private boolean isEven;

	public Task(Dashboard dashboard, int limit, boolean isEven) {
		this.dashboard = dashboard;
		this.limit = limit;
		this.isEven = isEven;
	}

	@Override
	public void run() {
		int number = this.isEven ? 2 : 1;
		while(number <= limit) {
			if(isEven) {
				dashboard.showEven(number);
			} else {
				dashboard.showOdd(number);
			}
			number += 2;
		}
	}

}
