package com.ourselec.gateway.process;

/**
 * 处理线程
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class ProcessThread extends Thread {
	private volatile Thread blinker;
	IProcess process;
	boolean isLoop;

	public ProcessThread(IProcess process, boolean isLoop) {
		this.process = process;
		this.isLoop = isLoop;
	}

	public void start() {
		blinker = new Thread(this);
		blinker.start();
	}

	public void stopThread() {
		Thread tmpBlinker = blinker;
		blinker = null;
		if (tmpBlinker != null) {
			tmpBlinker.interrupt();
		}
	}

	public void setProcess(IProcess process, boolean isLoop) {
		this.process = process;
		this.isLoop = isLoop;
	}

	@Override
	public void run() {
		super.run();
		if (process == null)
			return;
		if (isLoop) {
			Thread thisThread = Thread.currentThread();
			while (blinker == thisThread) {
				process.process();
			}
		} else
			process.process();

	}

	public interface IProcess {
		public void process();
	}
}
