package com.inmindd.dcu.emailService;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EmailEncryption
{
	private static final String ALGORITHM = "AES";
	private static final String KEY = "&yosx&_-+iy(yrs*";
	private static final String CHARSET = "UTF-8";
	
	public static String encrypt(String emailAddr)
	{
		Key secretKey = generateKey();
		try 
		{
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encodeVal = cipher.doFinal(emailAddr.getBytes());
			byte[] encryptedString = new Base64().encode(encodeVal);
			return new String(encryptedString,CHARSET);
		} 
		catch (NoSuchAlgorithmException | NoSuchPaddingException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalBlockSizeException e) 
		{
			e.printStackTrace();
		} 
		catch (BadPaddingException e) 
		{
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		return emailAddr;
		
	}
	
	
	public static String decrypt(String encryptedString)
	{
		Key key = generateKey();
		try 
		{
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedStringAsBytes = encryptedString.getBytes(CHARSET);
			byte[] decodedVal = new Base64().decode(encryptedStringAsBytes);
			byte[] decode = cipher.doFinal(decodedVal);
			String decryptedVal = new String(decode);
			return decryptedVal;
			
		} 
		catch (NoSuchAlgorithmException | NoSuchPaddingException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} 
		catch (IllegalBlockSizeException e) 
		{
			e.printStackTrace();
		} 
		catch (BadPaddingException e) 
		{
			e.printStackTrace();
		}
		return encryptedString;
		
	}
	
	
	
	private static Key generateKey()
	{
		byte[] keyAsBytes = KEY.getBytes(Charset.forName("UTF-8"));
		Key secretKey = new SecretKeySpec(keyAsBytes, ALGORITHM);
		return secretKey;
	}
	
}
