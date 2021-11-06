package chapter01;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingCharacter {
  // method 1 - without data structure
  public static Character firstUniqueMethod1(String input) {
    for (char c : input.toCharArray()) {
      if (input.indexOf(c) == input.lastIndexOf(c)) {
        return c;
      }
    }
    return Character.MIN_VALUE;
  }
  //  method 2 - with data structure
  public static Character firstUniqueMethod2(String input) {
    HashMap<Character, Integer> map = new LinkedHashMap<>();
    for (char c : input.toCharArray()) {
      map.compute(c, (k, v) -> (v == null) ? 1 : ++v);
    }
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        return entry.getKey();
      }
    }
    return Character.MIN_VALUE;
  }

  //  method 3 - with functional programming(Java 8)
  public static String findFirstUniqueMethod3(String input) {
    LinkedHashMap<Integer, Long> mapCount =
        input
            .codePoints()
            .boxed()
            .collect(
                Collectors.groupingBy(
                    Function.identity(), LinkedHashMap::new, Collectors.counting()));
    Integer result =
        mapCount.entrySet().stream()
            .filter(e -> e.getValue() == 1L)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse((int) Character.MIN_VALUE);
    return String.valueOf(Character.toChars(result));
  }

  public static void main(String[] args) {
    System.out.println("-----Method 1 : Without data structure-----");
    System.out.println(firstUniqueMethod1("raeer"));
    System.out.println("-----Method 2 : With data structure-----");
    System.out.println(firstUniqueMethod2("raeer"));
    System.out.println("-----Method 3 : With functional programming(Java 8)-----");
    System.out.println(findFirstUniqueMethod3("raeer"));
  }
}
