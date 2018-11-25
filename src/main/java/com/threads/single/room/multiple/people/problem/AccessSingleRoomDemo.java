package com.threads.single.room.multiple.people.problem;

public class AccessSingleRoomDemo {

	public static void main(String[] args) throws InterruptedException {
		Room room = new Room();
		
		// Could have used a for loop but just wanted to make it more explicit
		User edward = new User("Edward", room);
		User lelouch = new User("Lelouch", room);
		User natsu = new User("Natsu", room);
		User naruto = new User("Naruto", room);
		User luffy = new User("Luffy", room);
		
		Thread ed = new Thread(edward, "Edward");
		Thread le = new Thread(lelouch, "Lelouch");
		Thread nat = new Thread(natsu, "Natsu");
		Thread nar = new Thread(naruto, "Naruto");
		Thread luf = new Thread(luffy, "Luffy");
		
		ed.start();
		le.start();
		nat.start();
		nar.start();
		luf.start();
		
		ed.join();
		le.join();
		nat.join();
		nar.join();
		luf.join();
		
		System.out.println("The room is available now.");
	}
}
