package br.com.ufc.metafit.ui;

public class Destinos {

    private Double latitud;
    private Double longitud;
    private Double codigo;
    private Double bilhete;
    private Double telefone;

    public Destinos() {
        super();
    }

    public Destinos(Double latitud, Double longitud, Double codigo, Double bilhete, Double telefone) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigo = codigo;
        this.bilhete = bilhete;
        this.telefone = telefone;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getCodigo() {
        return codigo;
    }

    public void setCodigo(Double codigo) {
        this.codigo = codigo;
    }

    public Double getBilhete() {
        return bilhete;
    }

    public void setBilhete(Double bilhete) {
        this.bilhete = bilhete;
    }

    public Double getTelefone() {
        return telefone;
    }

    public void setTelefone(Double telefone) {
        this.telefone = telefone;
    }
}
