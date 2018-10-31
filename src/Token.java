public class Token {
    String token;   //token类型
    String lexeme;  //单词

    public Token(String token, String lexeme) {
        this.token = token;
        this.lexeme = lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "<" + token + ", " + lexeme + ">";
    }
}
