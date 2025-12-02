package com.mycompany.main;

public class Main {
    public static void main(String[] args) {
        Entrada io = new Entrada();
        Sistema s = new Sistema();
        
        s.carregarDados();
        System.out.println("Dados carregados com sucesso!");

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
        
        s.salvarDados();
        System.out.println("Dados salvos com sucesso!");
        System.out.println("Sistema encerrado.");
    }
}
