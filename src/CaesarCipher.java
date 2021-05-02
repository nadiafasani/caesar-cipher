/**
 * @author Nadia Fasani
 * @version 01.05.2021
*/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CaesarCipher{

    private boolean isUnsingEncrypt = false;

    /**
     * Il metodo encrypt cripta i messaggi con il cifrario di Cesare.
     * @param input il testo da codificare
     * @param shift il numero di spostamenti della posizione delle lettere
     * @return il testo codificato
    */
    public static String encrypt(String input, int shift){
        input = toRomanNumerals(input);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(Character.isLetter(c) && !Character.isWhitespace(c)){
                if (shift < 0) {
                    shift = 26 - (-shift % 26);
                }

                char startLetter = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)((c - startLetter + shift) %26 + startLetter);
            }
            result.append(c);
        }
        
        return result.toString();
    }

    public static String decrypt(String input, int shift){
        return encrypt(input, -shift);
    }

    public boolean getIsUsingEncrypt(){
        return isUnsingEncrypt;
    }

    public void setIsUsingEncrypt(boolean isUnsingEncrypt){
        this.isUnsingEncrypt = isUnsingEncrypt;
    }

    /**
     * Il metodo toRomanNumerals trasforma i numeri presenti
     * all'interno di un testo in numeri romani.
     * @param text il testo da modificare
     * @return il testo modificato
    */
    public static String toRomanNumerals(String text){
        Pattern p = Pattern.compile("([0-9])+");
        Matcher m = p.matcher(text);

        while (m.find()) {
            text = m.replaceFirst(intToRomanNumerals(m.group()));
            m = p.matcher(text);
        }
        
        return text;
    }
    
    /**
     * Il metodo integerToRomanNumerals trasforma un
     * numero in input in un numero romano.
     * @param input numero da trasformare
     * @return numero romano
    */
    private static String intToRomanNumerals(String text) {
        int input = Integer.parseInt(text);

        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;
        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }    
        return s;
    }
}
