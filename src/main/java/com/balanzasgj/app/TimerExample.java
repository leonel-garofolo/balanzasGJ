package com.balanzasgj.app;

import java.util.Date;
import java.util.TimerTask;

public class TimerExample extends TimerTask {
	private String name;

	public TimerExample(String n) {
		this.name = n;
	}

	@Override
	public void run() {
		System.out.println(
				Thread.currentThread().getName() + " " + name + " the task has executed successfully " + new Date());
		if ("Task1".equalsIgnoreCase(name)) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}