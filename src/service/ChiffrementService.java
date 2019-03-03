package service;

public class ChiffrementService {
	
	/*
	 * Pour le chiffrement des dix caractères spéciaux que j'ai choisie à é è ç â - . ê , espace, '
	 * Je les chiffre en entier de 0 à 9 mais de façon inversé sans logique
	 * 0123456789 => 9180726354 dont à est 9 et é est 1 ainsi de suite...
	 * le caractère ' est µ
	 */
	private char lettreAlphabetTableau[] = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'à', 'é', 'è', 'ç', 'â', '-', '.', 'ê', ',', ' ', '\''};
    private char lettreAlphabetTableauPermuter[] = {'t', 'g', 'j', 'p', 'o', 'i', 'z', 's', 'k', 'b', 'v', 'm', 'd', 'h', 'a', 'y', 'w', 'f', 'c', 'q', 'u', 'x', 'n', 'r', 'l', 'e', '9', '1', '8', '0', '7', '2', '6', '3', '5', '4', 'µ'};
    private char motAChiffrerTab[];
    
    public String algoChiffrementTexte(String textAChiffrer) {
    	
    	motAChiffrerTab = textAChiffrer.toCharArray();
    	StringBuilder monMotChiffrerBuilder = new StringBuilder();
    	
        for (char l : motAChiffrerTab){
            char a = charISPresentChiffrement(l,lettreAlphabetTableau,lettreAlphabetTableauPermuter);
            monMotChiffrerBuilder.append(a);
        }
        System.out.println(monMotChiffrerBuilder.toString());
        
    	return monMotChiffrerBuilder.toString();
    }
    
    public String algoDechiffrementTexte(String texteADechiffrer) {
    	motAChiffrerTab = texteADechiffrer.toCharArray();
    	StringBuilder monMotDechiffrerBuilder = new StringBuilder();
    	
        for (char l : motAChiffrerTab){
            char a = charISPresentDechiffrement(l,lettreAlphabetTableauPermuter,lettreAlphabetTableau);
            monMotDechiffrerBuilder.append(a);
        }
        System.out.println(monMotDechiffrerBuilder.toString());
        
    	return monMotDechiffrerBuilder.toString();
    }
	
	private char charISPresentChiffrement(char letter,char letterTab[],char letterPermuteTab[]){
        char myChar = 0;
        for (int i = 0; i < letterTab.length; i++) {
            if (letterTab[i] == letter) {
                for (int j = 0;j < letterPermuteTab.length;j++){
                    j = i;
                    myChar = letterPermuteTab[j];
                    System.out.println("My char est " + myChar);
                    System.out.println(letterTab[i] + " permuter donne " + letterPermuteTab[j]);
                    break;
                }
                break;
            }
        }
        return myChar;
    } 
	
	private char charISPresentDechiffrement(char letter,char letterPermuteTab[],char letterTab[]){
        char myChar = 0;
        for (int i = 0; i < letterPermuteTab.length; i++) {
            if (letterPermuteTab[i] == letter) {
                for (int j = 0;j < letterTab.length;j++){
                    j = i;
                    myChar = letterTab[j];
                    System.out.println("My char est " + myChar);
                    System.out.println(letterPermuteTab[i] + " dechiffrer donne " + letterTab[j]);
                    break;
                }
                break;
            }
        }
        return myChar;
    }
}
