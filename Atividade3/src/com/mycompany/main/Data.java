package com.mycompany.main;

public class Data implements Comparable<Data> {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }
    
    public static boolean validarData(int dia, int mes, int ano) {
        if (mes < 1 || mes > 12) {
            return false;
        }
        
        if (dia < 1) {
            return false;
        }
        
        int diasNoMes = obterDiasNoMes(mes, ano);
        
        if (dia > diasNoMes) {
            return false;
        }
        
        return true;
    }
    
    public static int obterDiasNoMes(int mes, int ano) {
        if (mes == 2) {
            if (ehBissexto(ano)) {
                return 29;
            } else {
                return 28;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        } else {
            return 31;
        }
    }
    
    public static boolean ehBissexto(int ano) {
        if (ano % 400 == 0) {
            return true;
        }
        if (ano % 100 == 0) {
            return false;
        }
        if (ano % 4 == 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public int compareTo(Data d) {
        if (this.ano < d.getAno()) return -1;
        if (this.ano > d.getAno()) return 1;
        
        if (this.mes < d.getMes()) return -1;
        if (this.mes > d.getMes()) return 1;
        
        if (this.dia < d.getDia()) return -1;
        if (this.dia > d.getDia()) return 1;
        
        return 0;
    }
    
    @Override
    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.ano;
    }
}
