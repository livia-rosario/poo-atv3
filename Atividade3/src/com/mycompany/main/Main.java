package com.mycompany.main;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE CONCESSIONÁRIA DE VEÍCULOS");
        System.out.println("========================================");
        System.out.println();
        
        Entrada io = new Entrada();
        Sistema s = new Sistema();
        
        s.carregarDados();

        int op = io.menu();

        while (op != 0) {
            switch(op) {
                case 1:
                    io.cadCliente(s); 
                    break;
                case 2:
                    io.cadVendedor(s); 
                    break;
                case 3:
                    io.cadGerente(s); 
                    break;
                case 4:
                    io.cadVeiculo(s); 
                    break;
                case 5:
                    io.cadVenda(s); 
                    break;
                case 6:
                    io.gerarRelatorioMensal(s); 
                    break;
                case 7:
                    io.gerarRelatorioAnual(s); 
                    break;
                case 8:
                    io.gerarRelatorioVendedor(s); 
                    break;
            }

            op = io.menu();
        }
        
        System.out.println();
        System.out.println("Encerrando sistema...");
        s.salvarDados();
        System.out.println("Dados salvos com sucesso!");
        System.out.println("Até logo!");
    }
}