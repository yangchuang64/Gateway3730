package com.ourselec.gateway.pc.message.tedstlv.send;

import com.ourselec.gateway.message.MessageSend;

public abstract class ValueSend extends MessageSend {

	int length;

	public ValueSend(int length, Object message) {
		super(message);
		this.length = length;
	}

}
