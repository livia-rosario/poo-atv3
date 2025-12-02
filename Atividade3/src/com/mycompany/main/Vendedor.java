package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Funcionario {
    private double comissao;
    private List<Venda> vendidos;
    
    public Vendedor(String nome, String cpf, int dia, int mes, int ano, double salario, double comissao) {
        super(nome, cpf, dia, mes, ano, salario);
        this.comissao = comissao;
        this.vendidos = new ArrayList<>();
    }
    
    public void addVenda(Venda v) {
        vendidos.add(v);
    }
    
    public double comissaoTotal(int mes, int ano) {
        double total = 0;
        for (Venda v: vendidos) {
            if (v.getData().getMes() == mes && v.getData().getAno() == ano) {
                total += v.getVeiculo().getValor() * (comissao/100);
            }
        }
        return total;
    }
    
    public double comissaoTotal(int ano) {
        double total = 0;
        for (Venda v: vendidos) {
            if (v.getData().getAno() == ano) {
                total += v.getVeiculo().getValor() * (comissao/100);
            }
        }
        return total;
    }
    
    @Override
    public double getSalario(int mes, int ano) {
        return salario + comissaoTotal(mes, ano);
    }
    
    public double getComissao() {
        return comissao;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public List<Venda> getVendidos() {
        return vendidos;
    }
}
