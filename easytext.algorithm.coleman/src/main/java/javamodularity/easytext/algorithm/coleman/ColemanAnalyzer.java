package javamodularity.easytext.algorithm.coleman;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javamodularity.easytext.algorithm.api.Analyzer;

public class ColemanAnalyzer implements Analyzer {
   
   public String getName() {
      return "Coleman-Liau";
   }

   public double analyze(List<List<String>> sentences) {
      float totalsentences = sentences.size();
      float words = sentences.stream().mapToInt(List::size).sum();
      float letters = sentences.stream().flatMapToInt(sentence -> sentence.stream().mapToInt(String::length)).sum();
      try {
         TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      return 0.0588 * (letters / (words / 100)) - 0.296 * (totalsentences / (words / 100)) - 15.8;
   }
}