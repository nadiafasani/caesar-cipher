/**
 * @author Nadia Fasani
 * @version 26.04.2021
*/

public class CaesarCipher{

    /**
     * Il metodo encrypt cripta i messaggi con il cifrario di Cesare.
     * @param input il testo da codificare
     * @param shift il numero di spostamenti della posizione delle lettere
     * @return il testo codificato
    */
    public static String encrypt(String input, int shift){
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(Character.isLetter(c) && !Character.isWhitespace(c)){
                char startLetter = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)((c - startLetter + shift) %26 + startLetter);
            }
            result.append(c);
        }

        result = new StringBuilder(toRomanNumerals(result.toString()));

        return result.toString();
    }

    /**
     * Il metodo toRomanNumerals trasforma i numeri presenti
     * all'interno di un testo in numeri romani.
     * @param text il testo da modificare
     * @return il testo modificato
    */
    public static String toRomanNumerals(String text){
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String number = "";
            if(Character.isDigit(c)){
                number += Character.toString(c);

                if((i == text.length()-1 && Character.isDigit(c)) || Character.isLetter(text.charAt(i+1))){
                    result.append(integerToRomanNumerals(Integer.parseInt(number)));
                    System.out.println(number);
                    number = "";
                }else{
                    result.append(c);
                }
                System.out.println(result);
            }
        }

        return result.toString();
    }

    /**
     * Il metodo containsNumbers controlla se la stringa
     * text contiene numeri o no.
     * @param text il testo da controllare
     * @return true se text contiene numeri
    */
    public static boolean containsNumbers(String text){
        for (int i = 0; i < text.length(); i++) {
            if(Character.isDigit(text.charAt(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * Il metodo integerToRomanNumerals trasforma un
     * numero in input in un numero romano.
     * @param input numero da trasformare
     * @return numero romano/**
     * Il metodo toRomanNumerals trasforma i numeri presenti
     * all'interno di un testo in numeri romani.
     * @param text il testo da modificare
     * @return il testo modificato
    */
    public static String integerToRomanNumerals(int input) {
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