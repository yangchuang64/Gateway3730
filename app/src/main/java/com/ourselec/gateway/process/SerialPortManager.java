package com.ourselec.gateway.process;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;
/**
 * 串口管理类
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class SerialPortManager {
	private static SerialPortManager instance;

	public final static String path = "/dev/ttyO0";
	public final static int baudrate = 115200;

	private SerialPort mSerialPort;
	private OutputStream mOutputStream;
	private InputStream mInputStream;

	public static SerialPortManager getInstance() {
		if (instance == null)
			instance = new SerialPortManager();
		return instance;
	}

	private SerialPortManager() {

	}

	public void setSerialPort(SerialPort serialPort) {
		this.mSerialPort = serialPort;
		this.mOutputStream = serialPort.getOutputStream();
		this.mInputStream = serialPort.getInputStream();
	}

	public void sendSerial(byte[] buffer) {
		if (mSerialPort == null || buffer == null) {
			return;
		}
		try {
			mOutputStream.write(buffer);
			mOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int readSerial(byte[] buffer) {
		if (mSerialPort == null) {
			return 0;
		}
		// BufferedInputStream bufInputStream = new BufferedInputStream(
		// mInputStream, 1024);
		try {
			// return bufInputStream.read(buffer);
			return mInputStream.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

//	public void clearSerial() {
//		try {
//			if (mInputStream.available() > 0) {
//				byte[] buffer = new byte[100];
//				int size = 0;
//				do {
//					try {
//						size = mInputStream.read(buffer);
//					} catch (IOException e1) {
//					}
//				} while (size == 100);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
