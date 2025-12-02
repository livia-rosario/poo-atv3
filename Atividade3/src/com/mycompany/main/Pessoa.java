package com.mycompany.main;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected Data nasc;

    public Pessoa(String nome, String cpf, int dia, int mes, int ano) {
        this.nome = nome;
        this.cpf = cpf;
        this.nasc = new Data(dia, mes, ano);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Data getNasc() {
        return nasc;
    }
    
    @Override
    public String toString() {
        String resultado;
        resultado = getNome() + " - CPF: " + getCpf();
        return resultado;
    }
    
    public int getIdade(Data hoje) {
        int idade = hoje.getAno() - nasc.getAno();
        
        if (hoje.getMes() < nasc.getMes() ||
        (hoje.getMes() == nasc.getMes() && hoje.getDia() < nasc.getDia())) {
        idade = idade - 1;
        }
        return idade;
    }
}
