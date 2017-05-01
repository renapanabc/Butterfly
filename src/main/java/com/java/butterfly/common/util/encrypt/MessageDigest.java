package com.java.butterfly.common.util.encrypt;

import java.security.NoSuchAlgorithmException;

public class MessageDigest {
	public static byte[] getDigest(byte[] srcData) throws NoSuchAlgorithmException {
		byte[] dgt = null;
		String algorithm = "MD5";
		
		if (srcData == null){
			return dgt;
		}
		
		java.security.MessageDigest md = java.security.MessageDigest.getInstance(algorithm.toUpperCase());
		md.reset();
		md.update(srcData);
		dgt = md.digest();
		return dgt;
	}

	public static String getDigestAsUpperHexString(String srcData) throws NoSuchAlgorithmException {
		if (srcData == null){
			return null;
		}
		
		return bytesToHexString(getDigest(srcData.getBytes()));
	}

	public static String bytesToHexString(byte[] bytes) {
		if (bytes == null){
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int t = bytes[i];
			sb.append(Integer.toString(t >> 4 & 0xF, 16)).append(Integer.toString(t & 0xF, 16));
		}
		
		String ret = sb.toString();
		return ret.toUpperCase();
	}

}