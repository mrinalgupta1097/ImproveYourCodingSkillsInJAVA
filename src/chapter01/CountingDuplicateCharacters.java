package chapter01;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CountingDuplicateCharacters {
  /*WAP to count duplicate characters from given String*/
  public static Map<Character, Integer> countDuplicates(String input) {
    Map<Character, Integer> result = new HashMap<>();
    for (char c : input.toCharArray()) {
      int g = input.indexOf(c);
      result.compute(c, (k, v) -> (v == null) ? 1 : ++v);
    }
    return result;
  }

  // Functional Programming Approach
  public static Map<Character, Long> countDuplicatesFunc(String input) {
    return input
        .codePoints()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
  }

  public static void main(String[] args) {
    System.out.println("--------Declarative Style--------");
    System.out.println(CountingDuplicateCharacters.countDuplicates("geeks for geeks") + "\n");

    System.out.println("--------Functional Style--------");
    System.out.println(CountingDuplicateCharacters.countDuplicatesFunc("geeks for geeks"));
  }
}
