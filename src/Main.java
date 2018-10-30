import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static List<Token> tokens = new ArrayList<>();
    private static String factor = "";

    public static void main(String[] args) {

        FileReader fileReader = null;
        try {
            File file = new File("file.txt");
            fileReader = new FileReader(file);

            BufferedReader reader = new BufferedReader(fileReader);
            char first = 0, second = 0; //有些符号如==/!=，是由两个符号组成
            String tmp = "";
            while (true) {
                int a = reader.read();
                if (first == '=' && second != 0) {
                    recognize(factor);
                    tokens.add(new Token("ASSIGN", "="));
                    factor = "";
                    first = 0;
                }
                if (a != -1) {
                    char c = (char) a;

                    //  System.out.print(c);
                    if (!isWhiteSpace(c)) {

                        switch (c) {
                            case '{':
                                recognize(factor);
                                tokens.add(new Token("LB", "{"));
                                factor = "";
                                break;
                            case '}':
                                recognize(factor);
                                tokens.add(new Token("RB", "}"));
                                factor = "";
                                break;
                            case '(':
                                recognize(factor);
                                tokens.add(new Token("LP", "("));
                                factor = "";
                                break;
                            case ')':
                                recognize(factor);
                                tokens.add(new Token("RP", ")"));
                                factor = "";
                                break;
                            case '+':
                                recognize(factor);
                                tokens.add(new Token("ADD", "+"));
                                factor = "";
                                break;
                            case '-':
                                recognize(factor);
                                tokens.add(new Token("SUB", "-"));
                                factor = "";
                                break;
                            case '*':
                                recognize(factor);
                                tokens.add(new Token("MUL", "*"));
                                factor = "";
                                break;
                            case '/':
                                recognize(factor);
                                tokens.add(new Token("DIV", "/"));
                                factor = "";
                                break;
                            case '%':
                                recognize(factor);
                                tokens.add(new Token("MOD", "%"));
                                factor = "";
                                break;
                            case ';':
                                recognize(factor);
                                tokens.add(new Token("SM", ";"));
                                factor = "";
                                break;
                            case ',':
                                recognize(factor);
                                tokens.add(new Token("CM", ","));
                                factor = "";
                                break;
                            case '>':
                                recognize(factor);
                                tokens.add(new Token("GT", ">"));
                                factor = "";
                                break;
                            case '<':
                                recognize(factor);
                                tokens.add(new Token("LT", "<"));
                                factor = "";
                                break;
                            case '=':
                                if (first == 0) {
                                    first = c;
                                } else if (first == '=') {
                                    recognize(factor);
                                    tokens.add(new Token("EQ", "=="));
                                    factor = "";
                                    first = 0;
                                    second = '=';
                                } else if (first == '!') {
                                    tokens.add(new Token("NE", "!="));
                                    factor = "";
                                    first = 0;
                                    second = '=';
                                }
                                break;

                            default:
                                factor += c;
                                break;
                        }
                    } else {
                        recognize(factor);
                        factor = "";
                    }
                } else {
                    break;
                }

            }
            if (factor.length() > 0) {
                recognize(factor);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Token token : tokens)
            System.out.println(token.toString());
    }

    public static void recognize(String factor) {
        factor = factor.trim();
        if (factor.length() < 1) {

        } else if (Keyword.contains(factor))
            tokens.add(new Token("Keyword", factor));
        else if (isInt(factor))
            tokens.add(new Token("INT", factor));
        else if (isFloat(factor))
            tokens.add(new Token("FLOAT", factor));
        else if (factor.equals("false") || factor.equals("true"))
            tokens.add(new Token("BOOLEAN", factor));
        else {
            tokens.add(new Token("ID", factor));
        }
    }

    public static boolean isWhiteSpace(char c) {
        char[] whites = new char[]{'\n', '\t', '\r', ' ', '\b', '\f'};
        for (char d : whites) {
            if (c == d)
                return true;
        }
        return false;
    }

    public static boolean isInt(String s) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static boolean isFloat(String s) {
        Pattern pattern = Pattern.compile("[0-9]*\\.*[0-9]+");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

}
