package com.labs1904;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SecretRecipeDecoder {
    private static Map<String, String> ENCODING = new HashMap<String, String>() {
        {
            put("y", "a");
            put("h", "b");
            put("v", "c");
            put("x", "d");
            put("k", "e");
            put("p", "f");
            put("z", "g");
            put("s", "h");
            put("a", "i");
            put("b", "j");
            put("e", "k");
            put("w", "l");
            put("u", "m");
            put("q", "n");
            put("n", "o");
            put("l", "p");
            put("m", "q");
            put("f", "r");
            put("o", "s");
            put("i", "t");
            put("g", "u");
            put("j", "v");
            put("t", "w");
            put("d", "x");
            put("r", "y");
            put("c", "z");
            put("3", "0");
            put("8", "1");
            put("4", "2");
            put("0", "3");
            put("2", "4");
            put("7", "5");
            put("5", "6");
            put("9", "7");
            put("1", "8");
            put("6", "9");
        }
    };

    /**
     * Given a string named str, use the Caesar encoding above to return the decoded string.
     * @param str
     * @return
     */
    public static String decodeString(String str) {
        String encodedString = str; //changing for readability
        StringBuilder decodedString = new StringBuilder(str.length()+1);

        for (int i = 0; i < encodedString.length(); i++) {
            //loop thru string, then swap for correct letter.
            String encodedLetter = String.valueOf(encodedString.charAt(i)); //loops thru the letters in string
            String decodedLetter;

            if(ENCODING.containsKey(encodedLetter)){ //behavior should just pass letter along if its not in cypher
                decodedLetter = ENCODING.get(encodedLetter);
                //System.out.println("if: " + decodedLetter);
            } else {
                decodedLetter = encodedLetter;
                //System.out.println("else: " + decodedLetter);
            }
            decodedString.append(decodedLetter);
        }

        //System.out.println(decodedString);
        return decodedString.toString();
    }

    /**
     * Given an ingredient, decode the amount and description, and return a new Ingredient
     * @param line
     * @return
     */
    public static Ingredient decodeIngredient(String line) {
        // TODO: implement reading from text line maybe? need to look at table because data has #'s in it, but focus on getting basic functionality first then iron this out
        String[] splitLine = line.split("#");

        //Ingredient x = new Ingredient("8 vgl", "hgiikf"); //use for test
         //use for test

        String y = decodeString(splitLine[0]);
        String z = decodeString(splitLine[1]);

//        System.out.println(y);
//        System.out.println(z);

        return new Ingredient(y,z);
    }

    public static void main(String[] args) {
        // TODO: implement me
        //Ingredient secretrecipe = new Ingredient("8 vgl", "#hgiikf");  //use this for testing, need to implement reading this from file
        //System.out.println(decodeString("8 vgl"));
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\bmxca\\GitHub\\de-hours-with-experts\\java\\src\\main\\resources\\secret_recipe.txt")); //hardcoding this path is probably not good
            String line;
            String decodedAmount;
            String decodedIngredient;
            StringBuilder secretRecipe = new StringBuilder();

            System.out.println("--- Ingredient List ---");
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                decodedAmount = decodeIngredient(line).getAmount();
                decodedIngredient = decodeIngredient(line).getDescription();

                secretRecipe.append(decodedAmount + " of " + decodedIngredient + "\n");

                //System.out.println(decodedAmount + " of " + decodedIngredient);
            }
            System.out.println(secretRecipe);

            try {
                Path path = Paths.get("C:\\Users\\bmxca\\GitHub\\de-hours-with-experts\\java\\src\\main\\resources\\decoded_recipe.txt");
                Files.writeString(path, secretRecipe, StandardCharsets.UTF_8);
            }
            catch (IOException ex){
                System.out.println("Invalid Write Location");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
