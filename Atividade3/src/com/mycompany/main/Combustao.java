package com.mycompany.main;

public class Combustao extends Veiculo {
    private double autonomiaComb, capacidadeComb;
    
    public Combustao(String marca, String modelo, int anoFab, int mesFab, 
            int anoMod, double valor, double autonomiaComb, 
            double capacidadeComb) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaComb = autonomiaComb;
        this.capacidadeComb = capacidadeComb;
    }
    
    public double getAutonomiaComb() {
        return autonomiaComb;
    }
    
    public double getCapacidadeComb() {
        return capacidadeComb;
    }
    
    @Override
    public int getAutonomia() {
        return (int)Math.round(autonomiaComb);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Combustão)";
    }
}
