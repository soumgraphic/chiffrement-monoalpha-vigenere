package service;

public class ChiffrementDeVigenere {
	public static final String alphabet = new String("azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBN0123456789+-*/=&È\"'(-Ë_Á‡)^‚ÓÍÙÔˆÎ‰$£§*µ%˘!ß:/;.,? ∞~#{[|`\\@]}\t\n<>≤" + "‚ÓÍÙÔˆÎ‰˘".toUpperCase());
	public static final int tailleAlphabet = alphabet.length();
	
	public static String chiffrerAvecVigenere(String messageAChiffrer, String cleDeCryptage) {
		String messageChiffrer = new String();
		int i = 0;
		for (i = 0; i < messageAChiffrer.length(); i++) {
		      int j = alphabet.indexOf(messageAChiffrer.charAt(i));
		      if (j >= 0) {
		        j = (j + alphabet.indexOf(cleDeCryptage.charAt(i % cleDeCryptage.length())) + 1) % tailleAlphabet;
		        messageChiffrer = messageChiffrer + alphabet.charAt(j);
		      }
		      else {
		    	  messageChiffrer = messageChiffrer + messageAChiffrer.charAt(i);
		      }
		    }
		
		return messageChiffrer;
	}
	
	public static String dechiffrerAvecVigenere(String messageADechiffrer, String cleDeCryptage) {
		String messageChiffrer = new String();
		int i = 0;
		for (i = 0; i < messageADechiffrer.length(); i++) {
		      int j = alphabet.indexOf(messageADechiffrer.charAt(i));
		      if (j >= 0) {
		        j = (j - alphabet.indexOf(cleDeCryptage.charAt(i % cleDeCryptage.length())) - 1) % tailleAlphabet;
		        while (j < 0) {
		          j += tailleAlphabet;
		        }
		        messageChiffrer = messageChiffrer + alphabet.charAt(j);
		      }
		      else {
		    	messageChiffrer = messageChiffrer + messageADechiffrer.charAt(i);
		      }
		    }
		return messageChiffrer;
	}
	
	public static boolean checkIfKeyOfCryptageIsInvalid(String cleDeCryptage) {
		for (int i = 0; i < cleDeCryptage.length(); i++) {
			if (alphabet.indexOf(cleDeCryptage.charAt(i)) == -1) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String cleDeCryptage = "lol";
		if (!checkIfKeyOfCryptageIsInvalid(cleDeCryptage)) {
			System.out.println(chiffrerAvecVigenere("12345678", cleDeCryptage));
		}
		System.out.println(chiffrerAvecVigenere("12345678", cleDeCryptage));
		System.out.println(dechiffrerAvecVigenere("HUm p S‚xHkSx|", cleDeCryptage));

	}

}
