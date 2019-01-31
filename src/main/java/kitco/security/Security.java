package kitco.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class Security {
	
	private static final String KEY_ALGORITHM = "RSA";
	
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDzAfaQW9t0gEuljVkpzmZjYEKrJAv/dlwjleVLmMrJ9CIdBHABjDFGmVifi/lw1WBIB2s+lZq2w2JT67oUTxV936CVWW13DEXfO1gn7kZQqeBoNs5qeuWmh89zDtCDdnWWpZ87Am7z9d2rt9ccPeZTpO7VAzNOl5/+ty6I66Sf5QIDAQAB";
	private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPMB9pBb23SAS6WNWSnOZmNgQqskC/92XCOV5UuYysn0Ih0EcAGMMUaZWJ+L+XDVYEgHaz6VmrbDYlPruhRPFX3foJVZbXcMRd87WCfuRlCp4Gg2zmp65aaHz3MO0IN2dZalnzsCbvP13au31xw95lOk7tUDM06Xn/63LojrpJ/lAgMBAAECgYEA21jmSp/fk2VK4wCXfxkDaqg1BW3gNpOLz8M52ebtBSYMtMyQnZUB1YwNZtZeLe8snVe1AiTgJiBu3PlmWXaZGec+swvlreynnNbutyj8q/xkmYEuQGl9qP3zWTxq3C9hoSXAXWErtjBnHYkqIblgMBLnUShTY7ke4AJKrSEZzwECQQD5wPeqSn40Ehe1Tknd/hQrbaT9NlieZZcbUVCQUsT5kbHWxP1Tfu1hW9HM2J74tf3jUwIjymxmt6ZxEDty/5o1AkEA+RXN3UhI7bZPEYaYbRUqx/HYeWIOH5pPHA0Inc4LF8p/WxfIR3hUJX3Tr2EcMm8CQYiOp6a7iQW7iuomTKEk8QJAPPtS/a+GtSwdwdVF0ABz6E6zVGhqpvJjPPZT14l+MvnSFICw4ixBxd3nkj7qQgwd292uzhJk+YeqCj1OZrvmaQJAOmuVxB+vEyneM6MY3wTG6uxbNGT+IQVlJpQcAD8SNPtGkYvram/bdIuleY3SOjX1Fn+7zbB6bbHNgdBXOUPWUQJASuWN52f+K8sGxGBn3hH1aiCnaTEdM24K99gKUX2AzKfzdKoq+xmnAA2OdIcqWsjGE76OVXhvWA9mWY5GuVAnhw==";
			
	/*public static Map<String, Object> initKey() throws NoSuchAlgorithmException{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
		
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		
		return keyMap;
	}*/
	
	public static String getPublicKey(Map<String, Object> keyMap){
		Key key = (Key)keyMap.get(PUBLIC_KEY);
		//byte[] publicKey = key.getEncoded();
		return encryptBASE64(key.getEncoded());
	}
	
	public static String getPrivateKey(Map<String, Object> keyMap){
		Key key = (Key)keyMap.get(PRIVATE_KEY);
		//byte[] privateKey = key.getEncoded();
		return encryptBASE64(key.getEncoded());
	}
	
	public static byte[] decryptBASE64(String key){
		return (new Base64()).decode(key);
	}
	
	public static String encryptBASE64(byte[] key){
		return (new Base64()).encodeToString(key);
	}
	
	public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {  
		byte[] buffer = (new Base64()).decode(publicKeyStr);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
    }  
	
	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {  
		byte[] buffer = (new Base64()).decode(privateKeyStr);  
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
    } 
	
	public static String encrypt(String source) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, loadPublicKeyByStr(PUBLIC_KEY));
		byte[] cipherText = cipher.doFinal(source.getBytes("utf-8"));
		return encryptBASE64(cipherText);
	}
	
	public static String decrypt(String cryptograph) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		byte[] plainText = decryptBASE64(cryptograph);
		cipher.init(Cipher.DECRYPT_MODE, loadPrivateKeyByStr(PRIVATE_KEY)); 
		byte[] plainTextStr = cipher.doFinal(plainText);
		return new String(plainTextStr, "UTF-8");
	}
	
	public static void main(String[] args){
		//Map<String, Object> keyMap;
		try{
			//keyMap = initKey();
			
			RSAPublicKey publicKey = loadPublicKeyByStr(PUBLIC_KEY);
			//System.out.println("publicKey = " + PUBLIC_KEY);
			
			RSAPrivateKey privateKey = loadPrivateKeyByStr(PRIVATE_KEY);
			//System.out.println("privateKey = " + PRIVATE_KEY);
			
			String str = "paul_123";

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] cipherText = cipher.doFinal(str.getBytes("utf-8"));
			String chipherTextStr = encryptBASE64(cipherText);
			System.out.println("cipher: " + chipherTextStr); 
			
			byte[] plainText = decryptBASE64(chipherTextStr);
			cipher.init(Cipher.DECRYPT_MODE, privateKey); 
			byte[] plainTextStr = cipher.doFinal(plainText);
			//BASE64Encoder encoder = new BASE64Encoder();
	        //return encoder.encode(b1);
	        System.out.println("plain : " + new String(plainTextStr, "UTF-8"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//publicKey : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTLcpJxBo+pxS7xGv0Dq3HUMw83M52/KB7epwx9/mAewu+ORUu/Uqv8hRjlAGHCJ2CaaFknDst+lZRYZs9grTUlxAdFeROErDCC4oje2ZjMfwcTqH5Vh4qFBh23/4agTophVEdjEReOLyttBv4W2cfWPVeYPXpvO13jLmBy6elzQIDAQAB
	//publicKey : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDzAfaQW9t0gEuljVkpzmZjYEKrJAv/dlwjleVLmMrJ9CIdBHABjDFGmVifi/lw1WBIB2s+lZq2w2JT67oUTxV936CVWW13DEXfO1gn7kZQqeBoNs5qeuWmh89zDtCDdnWWpZ87Am7z9d2rt9ccPeZTpO7VAzNOl5/+ty6I66Sf5QIDAQAB
	//privateKey: MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPMB9pBb23SAS6WNWSnOZmNgQqskC/92XCOV5UuYysn0Ih0EcAGMMUaZWJ+L+XDVYEgHaz6VmrbDYlPruhRPFX3foJVZbXcMRd87WCfuRlCp4Gg2zmp65aaHz3MO0IN2dZalnzsCbvP13au31xw95lOk7tUDM06Xn/63LojrpJ/lAgMBAAECgYEA21jmSp/fk2VK4wCXfxkDaqg1BW3gNpOLz8M52ebtBSYMtMyQnZUB1YwNZtZeLe8snVe1AiTgJiBu3PlmWXaZGec+swvlreynnNbutyj8q/xkmYEuQGl9qP3zWTxq3C9hoSXAXWErtjBnHYkqIblgMBLnUShTY7ke4AJKrSEZzwECQQD5wPeqSn40Ehe1Tknd/hQrbaT9NlieZZcbUVCQUsT5kbHWxP1Tfu1hW9HM2J74tf3jUwIjymxmt6ZxEDty/5o1AkEA+RXN3UhI7bZPEYaYbRUqx/HYeWIOH5pPHA0Inc4LF8p/WxfIR3hUJX3Tr2EcMm8CQYiOp6a7iQW7iuomTKEk8QJAPPtS/a+GtSwdwdVF0ABz6E6zVGhqpvJjPPZT14l+MvnSFICw4ixBxd3nkj7qQgwd292uzhJk+YeqCj1OZrvmaQJAOmuVxB+vEyneM6MY3wTG6uxbNGT+IQVlJpQcAD8SNPtGkYvram/bdIuleY3SOjX1Fn+7zbB6bbHNgdBXOUPWUQJASuWN52f+K8sGxGBn3hH1aiCnaTEdM24K99gKUX2AzKfzdKoq+xmnAA2OdIcqWsjGE76OVXhvWA9mWY5GuVAnhw==
}
