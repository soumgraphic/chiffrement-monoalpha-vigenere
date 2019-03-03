package service;

import java.util.Random;

public class ChiffrementDeVernam {
	static char lettreAlphabetTableau[] = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'à', 'é', 'è', 'ç', 'â', '-', '.', 'ê', ',', ' '};
	static char letterMasqueTableau[]   = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	static char messageAChiffrerTab[];
	static char masqueDeChiffrementMessage[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Le chiffrement est " + chiffrementMessageAvecVernam("HELLO"));
		//System.out.println(searchCharInCharArrayAndReturnIndice('o', letterMasqueTableau));
		
		System.out.println("Le chiffrement donne " + calculChiffrementVernam('d', 'o', lettreAlphabetTableau, letterMasqueTableau));
		System.out.println("Le chiffrement du mot entier donne " + chiffrementMessageAvecVernam("HELLO"));
		
		displayAlphabetWithIndiceInArray(lettreAlphabetTableau);
		System.out.println("\n");
		displayAlphabetWithIndiceInArray(letterMasqueTableau);
		
	}
	
	static String chiffrementMessageAvecVernam(String messageAChiffrer) {
		messageAChiffrerTab = messageAChiffrer.toLowerCase().toCharArray();
		StringBuilder chiffrementMessageBuilder = new StringBuilder();
		String masqueDeChiffrementGenerer = getSaltString(messageAChiffrer.length());
		System.out.println("Le masque de chiffrement generer est : " + masqueDeChiffrementGenerer);
		masqueDeChiffrementMessage = masqueDeChiffrementGenerer.toCharArray();
		for (char l : messageAChiffrerTab){
			for (char c : masqueDeChiffrementMessage) {
				char a = calculChiffrementVernam(l, c, lettreAlphabetTableau, letterMasqueTableau);
				chiffrementMessageBuilder.append(a);
			}
        }
        System.out.println(chiffrementMessageBuilder.toString());
        
    	return chiffrementMessageBuilder.toString();
	}
	
	static String getSaltString(int tailleMessageAChiffrer) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        System.out.println("Taille SALTCHARS " + SALTCHARS.length());
        //String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < tailleMessageAChiffrer) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	static char calculChiffrementVernam(char letterMessageAchiffrer,char letterMasque,char letterTab[],char letterMasqueTab[]) {
		char myChar = 0;
		int resultat = 0, indiceI, indiceJ;
		if (searchCharInCharArrayAndReturnIndice(letterMessageAchiffrer, letterTab) >= 0) {
			if (searchCharInCharArrayAndReturnIndice(letterMasque, letterMasqueTab) >= 0) {
				indiceI = searchCharInCharArrayAndReturnIndice(letterMessageAchiffrer, letterTab);
				indiceJ = searchCharInCharArrayAndReturnIndice(letterMasque, letterMasqueTab);
				
				System.out.println("Indice I " + indiceI);
				System.out.println("Indice J " + indiceJ);
				
				resultat = indiceI + indiceJ;
				if (resultat > letterMasqueTab.length) {
					resultat = resultat - (letterMasqueTab.length + 1);
					myChar = letterMasqueTab[resultat];
				}else {
					myChar = letterMasqueTab[resultat];
				}
			}
		}
        
        return myChar;
	}
	
	static char calculChiffrementVernam2(char letterMessageAchiffrer,char letterMasque,char letterTab[],char letterMasqueTab[]) {
		char myChar = 0;
		int resultat = 0;
        for (int i = 0; i < letterTab.length; i++) {
        	System.out.println(letterMessageAchiffrer);
			if (letterMessageAchiffrer == letterTab[i] ) {
				System.out.println(i + " , " + letterTab[i]);
				System.out.println(letterMessageAchiffrer);
				for (int j = 0; j < letterMasqueTab.length; j++) {
					if (letterMasque == letterMasqueTab[j]) {
						System.out.println(i + " , " + letterMasqueTab[j]);
						resultat = i + j;
						if (resultat > letterMasqueTab.length) {
							resultat = resultat - (letterMasqueTab.length + 1);
							myChar = letterMasqueTab[resultat];
						}else {
							myChar = letterMasqueTab[resultat];
						}
						break;
					}
				}
				break;
			}
		}
        return myChar;
	}
	
	static String chiffrementMessageAvecVernamOLD(String messageAChiffrer) {
		char lettreAlphabetTableau[] = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'à', 'é', 'è', 'ç', 'â', '-', '.', 'ê', ',', ' ', '\''};
		char messageAChiffrerTab[];
		messageAChiffrerTab = messageAChiffrer.toCharArray();
		for (int i = 0; i < lettreAlphabetTableau.length; i++) {
			System.out.println(i);
		}
		System.out.println("Random des caractères  en fonction de la taille " + messageAChiffrer.length());
		String messageGenerer = getSaltString(messageAChiffrer.length());
		System.out.println("Message générer " + messageGenerer);
		System.out.println("Taille message générer " + messageGenerer.length());
		return null;
	}
	
	static int searchCharInCharArrayAndReturnIndice(char c, char letterTab[]) {
		boolean contains = false;
		int indice = -1;
		for (int i = 0; i < letterTab.length; i++) {
			if (letterTab[i] == c) {
		        contains = true;
		        indice = i;
		        break;
		    }
		}
		return indice;
	}
	
	static void displayAlphabetWithIndiceInArray(char alphabetArray[]) {
		for (int i = 0; i < alphabetArray.length; i++) {
			System.out.print(i + "(" + alphabetArray[i] + ") , ");
		}
	}

}
