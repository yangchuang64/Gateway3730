package com.ourselec.gateway.util;

import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 */
public class Utile {
	// public static byte ort(MessageFrame frameData) {
	// byte result = (byte) frameData.getDataLen();
	// result = ort(result, frameData.getCommand());
	// return ort(result, frameData.getData());
	// }

	public static byte ort(byte[] data) {
		byte temp = data[0];

		for (int i = 1; i < data.length; i++) {
			temp = (byte) (temp ^ data[i]);
		}
		return temp;
	}

	public static byte ort(byte[] data, int process, int length) {
		// (byte) 0xFE, (byte) 0x01, (byte) 0x45,
		// (byte) 0xC0, (byte) 0x08, (byte) 0x8C
		byte temp = data[process];
		// System.out.println("temp:" + Integer.toHexString((0xff & temp)));
		for (int i = 1; i < length; i++) {
			temp ^= data[process + i];
			// System.out.println("data[process:"
			// + Integer.toHexString(0xff & data[process + i]));
			// System.out.println("temp:" + Integer.toHexString((0xff & temp)));
		}
		return temp;
	}

	public static boolean ort(byte[] data, int process, int length,
							  int disPosition) {
		byte temp = data[process];
		for (int i = 1; i < length; i++) {
			temp ^= data[process + i];
		}
		return temp == data[disPosition];
	}

	public static byte[] tranferChannel(int channel) {
		byte[] b = new byte[4];

		for (int i = 0; i < 4; i++)
			b[i] = 0;

		if ((channel >= 0) && (channel <= 7)) {
			b[0] = (byte) (0x1 << channel);
		}
		if ((channel >= 8) && (channel <= 15)) {
			b[1] = (byte) (0x1 << (channel - 8));
		}
		if ((channel >= 16) && (channel <= 23)) {
			b[2] = (byte) (0x1 << (channel - 16));
		}
		if ((channel >= 24) && (channel <= 31)) {
			b[3] = (byte) (0x1 << (channel - 24));
		}
		return b;
	}

	/* Int to 4 Bytes */
	public static byte[] tranferInt2Bytes(int data) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		bytes[2] = (byte) ((data & 0xff0000) >> 16);
		bytes[3] = (byte) ((data & 0xff000000) >> 24);
		return bytes;
	}

	/* Int to 2 Bytes */
	public static byte[] Int22Bytes(int i) {
		byte[] temp = new byte[2];
		temp[0] = (byte) (i & 0xff);
		temp[1] = (byte) ((i >> 8) & 0xff);
		return temp;
	}

	public static int bytes2Int(byte[] command) {
		return ((0xff & command[0]) << 8) | (0xff & command[1]);
	}

	public static byte[] gatewayProductNum2Bytes(int gatewayProductNum) {
		return new byte[] { (byte) (0xff & (gatewayProductNum >> 16)),
				(byte) (0xff & (gatewayProductNum >> 8)),
				(byte) (0xff & gatewayProductNum) };
	}

	public static List<byte[]> pickupFrame(byte[] pStream, int iStreamLength) {
		List<byte[]> list = new ArrayList<byte[]>();
		int iProcessed = 0;
		int iRemain = iStreamLength;
		while (iProcessed < iStreamLength && iRemain >= 5) {
			try {
				int sof = 0xff & pStream[iProcessed];
				if (sof == 0xFE) {
					int length = 0xff & pStream[iProcessed + 1] + 5;
					if (length > 5) {
						if (length <= iRemain) {
							if (Utile.ort(pStream, iProcessed + 1, length - 2,
									iProcessed + length - 1)) {
								byte[] buffer = new byte[length];
								System.arraycopy(pStream, iProcessed, buffer,
										0, buffer.length);
								list.add(buffer);

								printHexString("pickup", buffer);
								iProcessed += length;
								iRemain -= length;
							} else {
								iProcessed++;
								iRemain--;
							}
						} else
							break;
					} else {
						iProcessed += 2;
						iRemain -= 2;
					}

				} else {
					iProcessed++;
					iRemain--;
				}
			} catch (BufferOverflowException e) {
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		return list;
	}

	public static void printHexString(String tag, byte[] b) {
		System.out.print(tag);
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() + " ");
		}
		System.out.println("");

	}

	public static void printHexString(String tag, byte[] b, int length) {
		System.out.print(tag);
		for (int i = 0; i < length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() + " ");
		}
		System.out.println("");

	}

	public static String getManufacInfo(byte[] name) {
		StringBuffer sb = new StringBuffer();
		for (byte b : name) {
			sb.append((char) b);
		}
		return sb.toString();
	}

	public static String getProductNum(byte[] productNum) {
		StringBuffer sb = new StringBuffer();
		for (byte b : productNum) {
			sb.append(Integer.toHexString(0xff & b));
		}
		return sb.toString();
	}

	public static int Byte2Int(byte[] bytes) {
		return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8))
				| (0xff0000 & (bytes[2] << 16))
				| (0xff000000 & (bytes[3] << 24));
	}

	public static float Byte2Float(byte[] bytes) {
		return Float.intBitsToFloat(Byte2Int(bytes));
	}

	public static boolean isDigit(String str) {
		if (str == null || str.length() == 0)
			return false;
		return Pattern.matches("[0-9]*", str);
	}

	public static boolean isHexDigit(String str) {
		if (str == null || str.length() == 0)
			return false;
		return Pattern.matches("[0-9a-fA-F]*", str);
	}

	public static int convertToHex(String str) {
		if (str == null || str.length() == 0
				|| !Pattern.matches("[0-9a-fA-F]*", str))
			return -1;
		int digit = 0;
		for (int i = 0; i < str.length(); i++) {
			digit = digit << 4;
			Character ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') {
				digit = digit | (0xf & (ch - '0'));
			} else if (ch >= 'A' && ch <= 'Z') {
				digit = digit | (0xf & (ch - 'A' + 10));
			} else if (ch >= 'a' && ch <= 'z') {
				digit = digit | (0xf & (ch - 'a' + 10));
			}
		}
		return digit;
	}

	public static boolean isIpAddress(String str) {
		String ip = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static boolean isInPortRange(int port) {
		if (port > 0 && port < 65536)
			return true;
		return false;
	}

	public static boolean isInChannelRange(int channel) {
		if (channel >= 11 && channel <= 26)
			return true;
		return false;
	}

	public static boolean isInPanIdRange(int panId) {
		if (panId > 0 && panId < 0x3fff)
			return true;
		return false;
	}
}