package com.atguigu.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class TicketTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TicketTest.class);

	public static void main(String[] args) {

		/*
		 * Ticket ticket=new Ticket();
		 * 
		 * Thread th1=new Thread(ticket, "售票员一"); Thread th2=new Thread(ticket,
		 * "售票员二"); Thread th3=new Thread(ticket, "售票员三");
		 * 
		 * th1.start(); th2.start(); th3.start();
		 */
		final Res res = new Res();
		new Thread(new Runnable() {
			public void run() {
				try {
					while(true){
						
						res.add();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "线程一").start();

		new Thread(new Runnable() {
			public void run() {
				try {
					while(true){
						
						res.dec();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "线程二").start();
		
	}
}

class Res {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Res.class);

	private int count = 0;

	public synchronized void add() throws InterruptedException {
		if(count!=0){
			this.wait();
		}
		count++;
		logger.debug(count);
		this.notifyAll();
	}

	public  synchronized void dec() throws InterruptedException {
		if(count==0){
			wait();
		}
		count--;
		logger.debug(count);
		this.notifyAll();
	}
}
