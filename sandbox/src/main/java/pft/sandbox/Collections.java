package pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {

      String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");


      int i = 0;
      for (String l : languages) {
          System.out.println("Я хочу выучить " + l);
        }
    }
}
