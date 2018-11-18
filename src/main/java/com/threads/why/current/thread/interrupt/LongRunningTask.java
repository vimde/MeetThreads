package com.threads.why.current.thread.interrupt;

public class LongRunningTask extends Thread {

	@Override
	public void run() {
		doSomeIntensiveComputation();
	}

	private void doSomeIntensiveComputation() {
		for (int i = 0; i < 100000; i++) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Thread has been interrupted. So exiting...");
				break;
			} else {
				eachOperationTakesSomeTimeToCompute();
			}
		}
	}

	private void eachOperationTakesSomeTimeToCompute() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			//comment the line above and see what happens in the corresponding spec class
			//the interrupt status is not sent back, so it will be missed
		}
	}
}
