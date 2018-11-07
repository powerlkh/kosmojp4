package com.kosmo.member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public static void main(String[] args) {
		String hashStr = getHashVal("123", "SHA-256");  
		System.out.println(hashStr);
		//a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3
		//40bd001563085fc35165329ea1ff5c5ecbdbbeef
		//
		
	}
	
	public static String getHashVal(String str, String algorithm){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance(algorithm); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch(NoSuchAlgorithmException e){

			e.printStackTrace(); 

			SHA = null; 

		}

		return SHA;

	}

}
