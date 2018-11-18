package com.threads.why.current.thread.interrupt;

import java.lang.Thread.State;

import org.junit.Test;

public class InterruptThreadSpec {

	@Test
	public void aLongRunningTaskShouldBeInterruptedWhenItDoesNotRespond() throws InterruptedException {
		LongRunningTask task = new LongRunningTask();
		startThe(task);
		waitForSomeTimeForTheTaskToComplete();
		sinceTheTaskTakesTooMuchTimeInterruptThe(task);
	}

	private void sinceTheTaskTakesTooMuchTimeInterruptThe(LongRunningTask task) {
		task.interrupt();
	}

	private void startThe(LongRunningTask task) {
		task.start();
	}

	private void waitForSomeTimeForTheTaskToComplete() throws InterruptedException {
		Thread.sleep(5000);
	}
}
