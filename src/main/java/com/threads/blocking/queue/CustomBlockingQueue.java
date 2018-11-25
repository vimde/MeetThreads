package com.threads.blocking.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue<T> {

	private List<T> queue;
	private int limit;
	private Lock lock;
	private Condition isFull;
	private Condition isEmpty;

	public CustomBlockingQueue() {
		this(Integer.MAX_VALUE);
	}

	public CustomBlockingQueue(int limit) {
		this.limit = limit;
		this.queue = new LinkedList<>();
		this.lock = new ReentrantLock();
		this.isEmpty = this.lock.newCondition();
		this.isFull = this.lock.newCondition();
	}

	public void add(T value) {
		lock.lock();
		try {
			while (isFull()) {
				isFull.await();
			}
			this.queue.add(value);
			isEmpty.signalAll();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			lock.unlock();
		}

	}

	public void remove() {
		lock.lock();
		try {
			while (isEmpty()) {
				isEmpty.await();
			}
			this.queue.remove(0);
			isFull.signalAll();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		} finally {
			lock.unlock();
		}
	}

	private boolean isEmpty() {
		return this.queue.isEmpty();
	}

	private boolean isFull() {
		return this.queue.size() == this.limit;
	}
}
