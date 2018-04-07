package javamodularity.easytext.algorithm.api;

import java.util.ArrayList;
import java.util.List;

public class Preprocessing {

   public static List<List<String>> toSentences(String text) {
      var removedBreaks = text.replaceAll("\\r?\\n", " ");
      ArrayList<List<String>> sentences = new ArrayList<>();
      for(var rawSentence: removedBreaks.split("[\\.\\?\\!]")) {
         var words = toWords(rawSentence);
         if(words.size() > 0) {
            sentences.add(words);
         }
      }
      
      return sentences;
   }
   
   private static List<String> toWords(String sentence) {
      var rawWords = sentence.split("\\s+");
      List<String> words = new ArrayList<>();
      for(var rawWord: rawWords) {
         var word = rawWord.replaceAll("\\W", "");
         if(word.length() > 0) {
            words.add(word);
         }
      }
      
      return words;
   }

}