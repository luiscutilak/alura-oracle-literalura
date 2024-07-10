package br.com.alura.literalura.model;

public enum Linguagem {
    PT("pt"),
    EN("en"),
    ES("es"),
    FR("fr");

    private String idioma;

    Linguagem(String idioma) {
        this.idioma = idioma;
    }

    public static Linguagem fromString(String text) {
        for (Linguagem linguagem : Linguagem.values()){
            if(linguagem.idioma.equalsIgnoreCase(text)){
                return linguagem;

            }
        }
        throw new IllegalArgumentException("Linguagem ou Idioma não dísponivel: " + text);
    }

    public String getIdioma() {
        return this.idioma;
    }
}
