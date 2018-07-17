package com.epic.tle.FieldEngineerManagement.smartcard;

import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

import javax.crypto.SecretKey;



import au.com.safenet.crypto.WrappingKeyStore;
import com.epic.tle.util.Util;
import java.util.Enumeration;







public class HSMConnector {
	
	 private static Provider PROVIDER                         = null;
     private static KeyStore KEYSTORE                         = null;
     private static SecretKey BDK0                         	  = null;
     private static SecretKey LMK                             = null;
     private static SecretKey ZMK                             = null;
     private static WrappingKeyStore WRAPKEYSTORE 			  = null;
     private static String TDES_ECB_TRANSFORM  	              = "DESede/ECB/NoPadding";
     private static String TDES_CBC_TRANSFORM  	              = "DESede/CBC/NoPadding";
     private static String AES_ECB_TRANSFORM  	              = "AES/ECB/NoPadding";
     private static String AES_CBC_TRANSFORM  	              = "AES/CBC/NoPadding";
     private static String RSA_NON_TRANSFORM  	              = "RSA/NONE/PKCS1Padding";
     private static String TDES_PCKS5_TRANSFORM  	          = "DESede/CBC/PKCS5Padding";
     private static String TDES_ALGORITHEM                    = "DESede";
     private static String AES_ALGORITHEM                     = "AES";
     private static String AES_ALGO_MODE                      = "AES";
     private static String TDES_ALGO_MODE                     = "TDES";
     private static String ENCR_MOE_ECB                       = "ECB";
     private static String ENCR_MOE_CBC                       = "CBC";
     


     

     public static int [] getBDKIndex()throws  Exception{
     
         int bdk[] = new int[10];int c =0;
         
         for (int i=0;i<10;i++){bdk[i]= -1;}
     
         for (Enumeration enumKeys = KEYSTORE.aliases() ;enumKeys.hasMoreElements();)
                    {
                        String v = enumKeys.nextElement().toString();
                        if (v.contains("BDK")){
                        
                            v = v.substring(3);
                            System.out.println("String v : " + v);
                            bdk[c] = Integer.parseInt(v);
                            c++;
                            
                        }
                    }
         
         return bdk;
     }
 
     public static boolean init(int slot,String password)throws Exception{
    	 
                     boolean ok = true;
    	 	     PROVIDER = new au.com.safenet.crypto.provider.SAFENETProvider(slot);

    	 		
	             Security.addProvider(PROVIDER);
	    	     
	    	     
	             KEYSTORE = KeyStore.getInstance("CRYPTOKI", PROVIDER.getName());
	             KEYSTORE.load(null, Util.dataEncrypter(0,password).toCharArray());
	             WRAPKEYSTORE = WrappingKeyStore.getInstance("CRYPTOKI", PROVIDER.getName());
	             
	           
             
	             if(!KEYSTORE.containsAlias("LMK")){
	            	
	            	 ok = false;
	             }
	             if(!KEYSTORE.containsAlias("BDK0")){
	            	 
	            	
	            	 ok = false;
	            	 
	             }
	            
	             if(!KEYSTORE.containsAlias("ZMK")){
	            	 
	            	
	            	 ok = false;
	            	 
	             }
	             
	             
	             if(ok){
		             
	            	 
	            	 BDK0      = (SecretKey)KEYSTORE.getKey("BDK0", null);
	            	 LMK       = (SecretKey)KEYSTORE.getKey("LMK", null);
	            	 ZMK       = (SecretKey)KEYSTORE.getKey("ZMK", null);
	            	 
	            	
	            	 

	            	 
	            	
	            	 System.out.println( "HSM keys loading is successfully done.");
	            	 
	             }else{
	            	 System.out.println("HSM  init() is getting error ");
	             }


	             

         
             
	             return ok;


     }


     
     
  
	
	public static String getTDES_PCKS5_TRANSFORM() {
		return TDES_PCKS5_TRANSFORM;
	}






	





	public static void setBDK(SecretKey bDK) {
		BDK0 = bDK;
	}






	public static void setLMK(SecretKey lMK) {
		LMK = lMK;
	}






	public static void setZMK(SecretKey zMK) {
		ZMK = zMK;
	}






	public static void setPROVIDER(Provider pROVIDER) {
		PROVIDER = pROVIDER;
	}






	public static SecretKey getBDK() {
		return BDK0;
	}






	public static String getTDES_CBC_TRANSFORM() {
		return TDES_CBC_TRANSFORM;
	}






	public static SecretKey getZMK() {
		return ZMK;
	}


	














	public static String getRSA_NON_TRANSFORM() {
		return RSA_NON_TRANSFORM;
	}






	





	public static Provider getPROVIDER() {
		return PROVIDER;
	}



	public static KeyStore getKEYSTORE() {
		return KEYSTORE;
	}



	


	public static WrappingKeyStore getWRAPKEYSTORE() {
		return WRAPKEYSTORE;
	}



	
	public static SecretKey getLMK() {
		return LMK;
	}






	public static String getTDES_ECB_TRANSFORM() {
		return TDES_ECB_TRANSFORM;
	}









	public static String getAES_ECB_TRANSFORM() {
		return AES_ECB_TRANSFORM;
	}






	public static String getAES_CBC_TRANSFORM() {
		return AES_CBC_TRANSFORM;
	}






	public static String getTDES_ALGORITHEM() {
		return TDES_ALGORITHEM;
	}






	public static String getAES_ALGORITHEM() {
		return AES_ALGORITHEM;
	}










	public static String getAES_ALGO_MODE() {
		return AES_ALGO_MODE;
	}






	public static String getTDES_ALGO_MODE() {
		return TDES_ALGO_MODE;
	}






	public static String getENCR_MOE_ECB() {
		return ENCR_MOE_ECB;
	}






	public static String getENCR_MOE_CBC() {
		return ENCR_MOE_CBC;
	}










	

     
}
