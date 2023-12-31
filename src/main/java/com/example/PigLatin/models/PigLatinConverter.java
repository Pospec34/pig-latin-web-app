package com.example.PigLatin.models;

public class PigLatinConverter {

    public String convertToPigLatin(String text){
        if (isEmpty(text)){
            return "";
        }

        text = text.toLowerCase();
        String[] words = text.split(" ");
        String pigSentence = "";

        for (String word : words){
            String punctuation = getPunctuation(word);
            word = word.replaceAll("[^a-zA-Z1-9]+", "");
            if (startsWithVowel(word)){
                pigSentence += word + "ay" + punctuation + " ";
            } else if (isANumber(word)){
                pigSentence += word + punctuation + " ";
            } else {
                pigSentence += swapConsonants(word) + punctuation + " ";
            }
        }
        pigSentence = capitalizeFirstLetter(pigSentence);
        return pigSentence;
    }

    /**
     * Checks if the text starts with a vowel
     *
     * @param text The input text to be checked
     * @return returns true if text starts with vowel, otherwise returns false
     */
    private boolean startsWithVowel(String text){
        return text.matches("^[aeiouAEIOU].*");
    }

    private boolean isEmpty(String text){
        return text.equals("");
    }

    private boolean isANumber(String text){
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Swaps the consonants in a word according to Pig Latin rules
     *
     * @param text The input text to be changed
     * @return returns the text with swapped consonants and added "ay" at the end
     */
    private String swapConsonants(String text){
        String beginning = text.replaceAll(".*?([aeiouAEIOU].*)", "$1");
        String end = text.replaceAll("([^aeiouAEIOU]*).*", "$1");
        return beginning + end + "ay";
    }

    /**
     * This method splits the pig sentence into an array and then capitalizes the first letter of each sentence
     * @param text The input text to be changed
     * @return returns the same text it has received but capitalized
     */
    private String capitalizeFirstLetter(String text){
        boolean newSentence = true;
        String result = "";
        String words[] = text.split(" ");

        for (String word : words){
            if (newSentence){
                result += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
            } else {
                result += word + " ";
            }
            newSentence = isNewSentence(word);
        }

        return result.trim();
    }

    /**
     * Checks if the word ends with a punctuation mark indicating a new sentence
     * @param word The input word to be checked
     * @return returns true if the word ends with either question mark, exclamation mark or full stop
     */
    private boolean isNewSentence(String word){
        return word.matches(".*[.!?]+$");
    }

    /**
     * Retrieves the punctuation marks (.,?!) at the end of the text.
     * @param text The input text to be checked for punctuation
     * @return returns either the punctuation marks at the end of the text or an empty string
     */
    private String getPunctuation(String text){
        if (text.matches(".*[.,!?]+$")){
            return text.replaceAll(".*([.,!?]+$)", "$1");
        } else {
            return "";
        }
    }
}
