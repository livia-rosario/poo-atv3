package com.mycompany.main;

public abstract class Veiculo {
    protected String marca, modelo;
    protected int anoFab, mesFab, anoMod;
    protected double valor;
    
    public Veiculo(String marca, String modelo, int anoFab, int mesFab, int anoMod, double valor) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoFab = anoFab; 
        this.mesFab = mesFab;
        this.anoMod = anoMod;
        this.valor = valor;
    }
    
    public String getMarca() {
        return this.marca;
    }
    
    public String getModelo() {
        return this.modelo;
    }
    
    public int getAnoFab() {
        return this.anoFab;
    }
    
    public int getMesFab() {
        return this.mesFab;
    }
    
    public int getAnoMod() {
        return this.anoMod;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public abstract int getAutonomia();
    
    @Override
    public String toString() {
        return getMarca() + " " + getModelo() + " " + getAnoFab() + "/" +
               getAnoMod() + " - Autonomia: " + getAutonomia() + "km";
    }
}
