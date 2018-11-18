package com.threads.binary.semaphore;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.junit.Test;

//Binary semaphore can be used as mutex. We can use it for mutual exclusion.
public class BinarySemaphoreSpec {

	@Test
	public void otherThreadsAreBlockedFromIncrementingCounterWhenTheFirstOneHasAcquiredCounter() {
		CounterUsingBinarySemaphore counter = new CounterUsingBinarySemaphore();
		int NUMBER_OF_THREADS_ACCESSING_THE_COUNTER = 7;
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS_ACCESSING_THE_COUNTER);
		IntStream.range(0, NUMBER_OF_THREADS_ACCESSING_THE_COUNTER)
				.forEach(tryToIncreaseTheCounter(counter, executorService));
		executorService.shutdown();
		assertTrue("Counter does not have queued threads",counter.hasQueuedThreads());
	}

	@Test
	public void allTheThreadsCanIncrementTheCounterWhenTheyWaitForSomeTimeIntervalToAcquireCounter()
			throws InterruptedException {
		CounterUsingBinarySemaphore counter = new CounterUsingBinarySemaphore();
		int NUMBER_OF_THREADS_ACCESSING_THE_COUNTER = 5;
		int NUMBER_OF_THREADS_THAT_ACCESSED_THE_COUNTER = 5;
		int AVAILABLE_PERMITS = 1;
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS_ACCESSING_THE_COUNTER);
		IntStream.range(0, NUMBER_OF_THREADS_ACCESSING_THE_COUNTER)
				.forEach(tryToIncreaseTheCounter(counter, executorService));
		executorService.shutdown();
		assertTrue(counter.hasQueuedThreads());
		Thread.sleep(5000);
		assertFalse(counter.hasQueuedThreads());
		assertEquals(NUMBER_OF_THREADS_THAT_ACCESSED_THE_COUNTER, counter.value());
		Thread.sleep(1000);
		assertEquals(AVAILABLE_PERMITS, counter.numberOfPermitsAvailableToAccessTheCounter());
	}

	private IntConsumer tryToIncreaseTheCounter(CounterUsingBinarySemaphore counter, ExecutorService executorService) {
		return thread -> executorService.execute(() -> {
			try {
				counter.increase();
			} catch (InterruptedException interruptedException) {
				Thread.currentThread().interrupt();
				interruptedException.printStackTrace();
			}
		});
	}
}
