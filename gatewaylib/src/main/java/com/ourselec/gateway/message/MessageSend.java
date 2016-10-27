package com.ourselec.gateway.message;

/**
 * 消息发送基类
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public abstract class MessageSend {
	protected Object message;

	public MessageSend(Object message) {
		this.message = message;
	}

	/**
	 * 消息发送
	 *
	 * @return 消息字符数组
	 */
	public abstract byte[] getMessageBuffer();

	// public void sendMessage(OutputStream outputStream) {
	// try {
	// byte[] buffer = getMessageBuffer();
	// if (buffer != null) {
	// outputStream.write(buffer);
	// outputStream.flush();
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}