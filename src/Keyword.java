import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Keyword {
    private static String[] strs = new String[]{"public", "private", "class", "while", "do", "if", "else",
            "return", "static", "int", "float", "boolean", "void", "try", "catch"};

    public static boolean contains(String s) {
        for (String str : strs) {
            if (s.equals(str))
                return true;
        }
        return false;
    }
}
