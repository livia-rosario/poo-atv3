package com.mycompany.main;

public class Eletrico extends Veiculo {
    private double autonomiaBat, capacidadeBat;
    
    public Eletrico(String marca, String modelo, int anoFab, int mesFab, 
            int anoMod, double valor, double autonomiaBat, double capacidadeBat) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaBat = autonomiaBat;
        this.capacidadeBat = capacidadeBat;
    }
    
    public double getAutonomiaBat() {
        return autonomiaBat;
    }
    
    public double getCapacidadeBat() {
        return capacidadeBat;
    }
    
    @Override
    public int getAutonomia() {
        return (int)Math.round(autonomiaBat);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Elétrico)";
    }
}
