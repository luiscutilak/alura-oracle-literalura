package br.com.alura.literalura.model;

public enum Idioma {
    PT("pt");
    EN("en");
    ES("es");
    FR("fr");

    private String idioma;

    Idioma(String idioma) {
        this.idioma = idioma;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()){
            if(idioma.idioma.equalsIgnoreCase(text)){
                return idioma;

            }
        }
        throw new IllegalArgumentException("Linguagem não dísponivel: " + text);
    }

    public String getIdioma() {
        return this.idioma;
    }
}
