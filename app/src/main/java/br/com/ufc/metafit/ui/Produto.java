package br.com.ufc.metafit.ui;
public class Produto {
    private String nome;
    private String imageUrl;

    public Produto() {
        // Construtor padrão necessário para o Firebase Realtime Database
    }

    public Produto(String nome, String imageUrl) {
        this.nome = nome;
        this.imageUrl = imageUrl;
    }

    public String getNome() {
        return nome;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
