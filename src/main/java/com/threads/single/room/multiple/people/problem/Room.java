package com.threads.single.room.multiple.people.problem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Room {
	
	private Lock lock;
	
	public Room() {
		lock = new ReentrantLock();
	}

	public void isUsedBy(String name) {
		this.lock.lock();
		System.out.println(name + " is in the room");
		try {
			Thread.sleep(20);
			System.out.println("Only "+ name + " can access this room now");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(name + " is about to leave the room. Be ready.");
		this.lock.unlock();
	}

}
