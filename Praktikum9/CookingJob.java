package Praktikum9;

public class CookingJob implements Runnable {
	private String task;

	CookingJob(String task) {
		this.task = task;
	}

	@Override
	public void run() {
		System.out.println(task + " is being prepared by " + Thread.currentThread().getName());
	}

}
