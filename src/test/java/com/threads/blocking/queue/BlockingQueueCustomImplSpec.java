package com.threads.blocking.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;

public class BlockingQueueCustomImplSpec {

	@Test
	public void elementsShouldBeAddedToCustomBlockingQueueImpl() {
		CustomBlockingQueue<Integer> blockingQueue = new CustomBlockingQueue<>();
		ExecutorService executors = Executors.newFixedThreadPool(5);
		IntStream.range(0, 5)
			.forEach(thread -> executors.execute(() -> {
				blockingQueue.add(Integer.valueOf(thread));
			}));
		
	}
}
