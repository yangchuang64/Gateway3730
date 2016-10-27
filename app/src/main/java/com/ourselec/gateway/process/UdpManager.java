package com.ourselec.gateway.process;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Message;

/**
 * Udp管理类
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class UdpManager {

	private static UdpManager instance;
	private DatagramSocket dataSocket;
	// private InetSocketAddress socketAddress;
	// private DatagramPacket packet = null;
	// private byte[] buffer = new byte[1024];

	private int localPort;
	private String hostIp;
	private int hostPort;

	public static UdpManager getInstance() {
		if (instance == null) {
			instance = new UdpManager();
		}
		return instance;
	}

	private UdpManager() {
		// try {
		// socketAddress = new InetSocketAddress(0);
		// dataSocket = new DatagramSocket(socketAddress);
		// } catch (SocketException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// packet = new DatagramPacket(buffer, buffer.length);
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public int getHostPort() {
		return hostPort;
	}

	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	public byte[] receive() {
		// try {
		// dataSocket.receive(packet);
		// byte[] temp = packet.getData();
		// byte[] data = new byte[packet.getLength()];
		// System.arraycopy(temp, 0, data, 0, packet.getLength());
		//
		// return data;
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return null;
		try {
			if (dataSocket == null)
				dataSocket = new DatagramSocket(localPort);
			DatagramPacket receivePacket = new DatagramPacket(new byte[1024],
					1024);
			dataSocket.receive(receivePacket);
			byte[] data = new byte[receivePacket.getLength()];
			System.arraycopy(receivePacket.getData(),
					receivePacket.getOffset(), data, 0, data.length);
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public void send(byte[] data) {
		try {
			if (dataSocket == null)
				dataSocket = new DatagramSocket(localPort);

			// IOTPUdpAddress addr = (IOTPUdpAddress) message.getAddress();
			// String ip = addr.getIp();
			// int port = addr.getPort();
			// byte[] data = message.toBytes();

			// try {
			// DatagramPacket dp = new DatagramPacket(data, data.length,
			// InetAddress.getByName("168.1.0.0"), 0);
			// dataSocket.send(dp);
			// } catch (Exception e) {
			// /* mask message */
			// // e.printStackTrace();
			// }

			DatagramPacket sendPacket = new DatagramPacket(data, data.length,
					InetAddress.getByName(hostIp), hostPort);
			dataSocket.send(sendPacket);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
