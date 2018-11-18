package com.threads.binary.semaphore;

import java.util.concurrent.Semaphore;

public class CounterUsingBinarySemaphore {

	private Semaphore semaphore;
	private int counter;

	public CounterUsingBinarySemaphore() {
		this.semaphore = new Semaphore(1);
		counter = 0;
	}

	public void increase() throws InterruptedException {
		this.semaphore.acquire();
		counter++;
		Thread.sleep(1000);
		this.semaphore.release();
	}

	public int numberOfPermitsAvailableToAccessTheCounter() {
		return this.semaphore.availablePermits();
	}

	public boolean hasQueuedThreads() {
		return this.semaphore.hasQueuedThreads();
	}
	
	public int value() {
		return this.counter;
	}

}
