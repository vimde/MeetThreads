package com.threads.single.room.multiple.people.problem;

public class User implements Runnable {
	
	private Room room;
	private String name;

	public User(String name, Room room) {
		this.name = name;
		this.room = room;
	}

	@Override
	public void run() {
		this.room.isUsedBy(this.name);
	}

	
}
