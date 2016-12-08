package com.atguigu.thread;

import org.apache.log4j.Logger;

public class Ticket implements Runnable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Ticket.class);
	
	private int tickets=100;
	private int count=0;
	
	@Override
	public void run() {
		while(true){
			synchronized(Ticket.class){
				if(tickets>0){
					logger.debug(Thread.currentThread().getName()+"--卖出了第 "+ ++count+" 张票-->票号为"+tickets--);
				}else{
					break;
				}
			}
		}
	}

}
