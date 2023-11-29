package br.com.ufc.metafit.ui;

public class Destinos {

    private Double latitud;
    private Double longitud;
    private String codigo;
    private String bilhete;
    private String telefone;

    public Destinos() {
        super();
    }

    public Destinos(Double latitud, Double longitud, String codigo, String bilhete, String telefone) {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBilhete() {
        return bilhete;
    }

    public void setBilhete(String bilhete) {
        this.bilhete = bilhete;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
