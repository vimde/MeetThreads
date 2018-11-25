package com.threads.display.odd.even.wait.notify;

public class Dashboard {

	private volatile boolean isEven;
	
	public synchronized void showEven(int number)  {
		while(!isEven) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("Even: "+number);
		isEven = false;
		this.notifyAll();
	}

	public synchronized void showOdd(int number) {
		while(isEven) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println("Odd: "+number);
		isEven = true;
		this.notifyAll();
	}

}
