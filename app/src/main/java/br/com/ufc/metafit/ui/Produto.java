package br.com.ufc.metafit.ui;

import androidx.annotation.NonNull;

public class Produto {
    private String nome;
    private String imageUrl;

    public Produto() {
        
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

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
