package com.mycompany.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Entrada {
    public Scanner input;
    private boolean lendoDeArquivo;

    public Entrada() {
        try {
            this.input = new Scanner(new FileInputStream("input.txt"));
            this.lendoDeArquivo = true;
        } catch (FileNotFoundException e) {
            this.input = new Scanner(System.in);
            this.lendoDeArquivo = false;
        }
    }

private String lerLinha(String msg) {

    
    System.out.print(msg);

    
    if (!input.hasNextLine()) {
        if (lendoDeArquivo) {
            input.close();
            input = new Scanner(System.in);
            lendoDeArquivo = false;
        }
    }

    String linha = input.nextLine();

    // Ignora linhas que começam com '#'
    while (linha != null && linha.trim().startsWith("#")) {
        if (!input.hasNextLine()) return "";
        linha = input.nextLine();
    }

    return linha;
}


    private int lerInteiro(String msg) {
        while (true) {
            try {
                String linha = this.lerLinha(msg);
                if (linha.isEmpty() && !input.hasNextLine()) {
                    return 0;
                }
                int valor = Integer.parseInt(linha);
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: número inválido. Tente novamente.");
            }
        }
    }
    
    private int lerInteiroPositivo(String msg) {
        while (true) {
            try {
                String linha = this.lerLinha(msg);
                if (linha.isEmpty() && !input.hasNextLine()) {
                    return 1;
                }
                int valor = Integer.parseInt(linha);
                if (valor <= 0) {
                    System.out.println("Erro: o valor deve ser positivo. Tente novamente.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: número inválido. Tente novamente.");
            }
        }
    }

    private double lerDouble(String msg) {
        while (true) {
            try {
                String linha = this.lerLinha(msg);
                if (linha.isEmpty() && !input.hasNextLine()) {
                    return 0.0;
                }
                double valor = Double.parseDouble(linha);
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: número inválido. Tente novamente.");
            }
        }
    }
    
    private double lerDoublePositivo(String msg) {
        while (true) {
            try {
                String linha = this.lerLinha(msg);
                if (linha.isEmpty() && !input.hasNextLine()) {
                    return 0.0;
                }
                double valor = Double.parseDouble(linha);
                if (valor < 0) {
                    System.out.println("Erro: o valor não pode ser negativo. Tente novamente.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: número inválido. Tente novamente.");
            }
        }
    }
    
    private String lerLinhaObrigatoria(String msg) {
        while (true) {
            String linha = this.lerLinha(msg);
            if (linha == null || linha.trim().isEmpty()) {
                if (!input.hasNextLine()) {
                    return "";
                }
                System.out.println("Erro: este campo não pode estar vazio. Tente novamente.");
                continue;
            }
            return linha;
        }
    }
    
    private String lerCPF(String msg) {
        while (true) {
            String cpf = this.lerLinhaObrigatoria(msg);
            
            if (cpf.isEmpty()) {
                return "00000000000";
            }
            
            String cpfLimpo = cpf.replaceAll("[^0-9]", "");
            
            if (cpfLimpo.length() != 11) {
                System.out.println("Erro: CPF deve conter 11 dígitos. Tente novamente.");
                continue;
            }
            
            boolean todosIguais = true;
            for (int i = 1; i < cpfLimpo.length(); i++) {
                if (cpfLimpo.charAt(i) != cpfLimpo.charAt(0)) {
                    todosIguais = false;
                    break;
                }
            }
            
            if (todosIguais) {
                System.out.println("Erro: CPF inválido. Tente novamente.");
                continue;
            }
            
            return cpf;
        }
    }
    
    private String lerEmail(String msg) {
        while (true) {
            String email = this.lerLinhaObrigatoria(msg);
            
            if (email.isEmpty()) {
                return "email@exemplo.com";
            }
            
            boolean temArroba = false;
            int posArroba = -1;
            
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@') {
                    temArroba = true;
                    posArroba = i;
                    break;
                }
            }
            
            if (!temArroba) {
                System.out.println("Erro: email deve conter @. Tente novamente.");
                continue;
            }
            
            if (posArroba == 0 || posArroba == email.length() - 1) {
                System.out.println("Erro: email inválido. Tente novamente.");
                continue;
            }
            
            String depoisArroba = email.substring(posArroba + 1);
            boolean temPonto = false;
            
            for (int i = 0; i < depoisArroba.length(); i++) {
                if (depoisArroba.charAt(i) == '.') {
                    temPonto = true;
                    break;
                }
            }
            
            if (!temPonto) {
                System.out.println("Erro: email deve conter domínio válido (ex: @dominio.com). Tente novamente.");
                continue;
            }
            
            return email;
        }
    }
    
    private Data obterDataAtual() {
        LocalDate hoje = LocalDate.now();
        return new Data(hoje.getDayOfMonth(), hoje.getMonthValue(), hoje.getYear());
    }

    public int menu() {
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar Cliente\n" +
                "2) Cadastrar Vendedor\n" +
                "3) Cadastrar Gerente\n" +
                "4) Cadastrar Veículo\n" +
                "5) Cadastrar Venda\n" +
                "6) Relatório de Vendas Mensal\n" +
                "7) Relatório de Vendas Anual\n" +
                "8) Relatório de Vendas do Vendedor:\n" +
                "0) Sair\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 8) {
            System.out.println("Opção inválida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        return op;
    }

    public void cadCliente(Sistema s) {
        try {
            s.listarClientes();

            String nome = this.lerLinhaObrigatoria("Digite o nome do cliente: ");
            String cpf = this.lerCPF("Digite o cpf do cliente: ");
            
            int dia = this.lerInteiroPositivo("Digite o dia do nascimento do cliente: ");
            int mes = this.lerInteiroPositivo("Digite o mês do nascimento do cliente: ");
            int ano = this.lerInteiroPositivo("Digite o ano do nascimento do cliente: ");
            
            while (!Data.validarData(dia, mes, ano)) {
                System.out.println("Erro: data inválida. Tente novamente.");
                dia = this.lerInteiroPositivo("Digite o dia do nascimento do cliente: ");
                mes = this.lerInteiroPositivo("Digite o mês do nascimento do cliente: ");
                ano = this.lerInteiroPositivo("Digite o ano do nascimento do cliente: ");
            }
            
            int anoAtual = LocalDate.now().getYear();
            while (ano < 1900 || ano > anoAtual) {
                System.out.println("Erro: ano inválido (deve estar entre 1900 e " + anoAtual + ").");
                ano = this.lerInteiroPositivo("Digite o ano do nascimento do cliente: ");
            }
            
            Data nascimento = new Data(dia, mes, ano);
            Data hoje = obterDataAtual();
            
            if (nascimento.compareTo(hoje) > 0) {
                System.out.println("Erro: data de nascimento não pode ser futura!");
                return;
            }
            
            String email = this.lerEmail("Digite o email do cliente: ");

            if (s.cpfJaExiste(cpf)) {
                System.out.println("Erro: CPF já cadastrado no sistema. Cliente não adicionado.");
            }
            else {
                Cliente c = new Cliente(nome, cpf, dia, mes, ano, email);
                s.adicionar(c);
                System.out.println("Cliente cadastrado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
    
    public void cadVendedor(Sistema s) {
        try {
            s.listarVendedores();

            String nome = this.lerLinhaObrigatoria("Digite o nome do vendedor: ");
            String cpf = this.lerCPF("Digite o cpf do vendedor: ");
            
            int dia = this.lerInteiroPositivo("Digite o dia do nascimento do vendedor: ");
            int mes = this.lerInteiroPositivo("Digite o mês do nascimento do vendedor: ");
            int ano = this.lerInteiroPositivo("Digite o ano do nascimento do vendedor: ");
            
            while (!Data.validarData(dia, mes, ano)) {
                System.out.println("Erro: data inválida. Tente novamente.");
                dia = this.lerInteiroPositivo("Digite o dia do nascimento do vendedor: ");
                mes = this.lerInteiroPositivo("Digite o mês do nascimento do vendedor: ");
                ano = this.lerInteiroPositivo("Digite o ano do nascimento do vendedor: ");
            }
            
            int anoAtual = LocalDate.now().getYear();
            while (ano < 1900 || ano > anoAtual) {
                System.out.println("Erro: ano inválido (deve estar entre 1900 e " + anoAtual + ").");
                ano = this.lerInteiroPositivo("Digite o ano do nascimento do vendedor: ");
            }
            
            Data nascimento = new Data(dia, mes, ano);
            Data hoje = obterDataAtual();
            
            if (nascimento.compareTo(hoje) > 0) {
                System.out.println("Erro: data de nascimento não pode ser futura!");
                return;
            }
            
            double salario = this.lerDoublePositivo("Digite o salário mensal fixo do vendedor: ");
            double comissao = this.lerDoublePositivo("Digite o percentual de comissão deste vendedor: ");
            
            while (comissao > 100) {
                System.out.println("Erro: comissão não pode ser maior que 100%.");
                comissao = this.lerDoublePositivo("Digite o percentual de comissão deste vendedor: ");
            }

            if (s.cpfJaExiste(cpf)) {
                System.out.println("Erro: CPF já cadastrado no sistema. Vendedor não adicionado.");
            }
            else {
                Vendedor v = new Vendedor(nome, cpf, dia, mes, ano, salario, comissao);
                s.adicionar(v);
                System.out.println("Vendedor cadastrado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar vendedor: " + e.getMessage());
        }
    }
    
    public void cadGerente(Sistema s) {
        try {
            s.listarGerentes();

            String nome = this.lerLinhaObrigatoria("Digite o nome do gerente: ");
            String cpf = this.lerCPF("Digite o cpf do gerente: ");
            
            int dia = this.lerInteiroPositivo("Digite o dia do nascimento do gerente: ");
            int mes = this.lerInteiroPositivo("Digite o mês do nascimento do gerente: ");
            int ano = this.lerInteiroPositivo("Digite o ano do nascimento do gerente: ");
            
            while (!Data.validarData(dia, mes, ano)) {
                System.out.println("Erro: data inválida. Tente novamente.");
                dia = this.lerInteiroPositivo("Digite o dia do nascimento do gerente: ");
                mes = this.lerInteiroPositivo("Digite o mês do nascimento do gerente: ");
                ano = this.lerInteiroPositivo("Digite o ano do nascimento do gerente: ");
            }
            
            int anoAtual = LocalDate.now().getYear();
            while (ano < 1900 || ano > anoAtual) {
                System.out.println("Erro: ano inválido (deve estar entre 1900 e " + anoAtual + ").");
                ano = this.lerInteiroPositivo("Digite o ano do nascimento do gerente: ");
            }
            
            Data nascimento = new Data(dia, mes, ano);
            Data hoje = obterDataAtual();
            
            if (nascimento.compareTo(hoje) > 0) {
                System.out.println("Erro: data de nascimento não pode ser futura!");
                return;
            }
            
            double salario = this.lerDoublePositivo("Digite o salário mensal fixo do gerente: ");
            String senha = this.lerLinhaObrigatoria("Digite a senha do gerente: ");
            
            if (senha.length() < 4) {
                System.out.println("Aviso: senha muito curta. Recomenda-se no mínimo 4 caracteres.");
            }

            if (s.cpfJaExiste(cpf)) {
                System.out.println("Erro: CPF já cadastrado no sistema. Gerente não adicionado.");
            }
            else {
                Gerente g = new Gerente(nome, cpf, dia, mes, ano, salario, senha);
                s.adicionar(g);
                System.out.println("Gerente cadastrado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar gerente: " + e.getMessage());
        }
    }
    
    public void cadVeiculo(Sistema s) {
        try {
            s.listarVeiculos();

            String marca = this.lerLinhaObrigatoria("Digite a Marca do veículo: ");
            String modelo = this.lerLinhaObrigatoria("Digite o Modelo do veículo: ");
            
            int anoAtual = LocalDate.now().getYear();
            int mesAtual = LocalDate.now().getMonthValue();
            
            int anoFab = this.lerInteiroPositivo("Digite o ano de fabricação do veículo: ");
            while (anoFab < 1900 || anoFab > anoAtual) {
                System.out.println("Erro: ano de fabricação inválido (deve estar entre 1900 e " + anoAtual + ").");
                anoFab = this.lerInteiroPositivo("Digite o ano de fabricação do veículo: ");
            }
            
            int mesFab = this.lerInteiroPositivo("Digite o mês de fabricação do veículo: ");
            while (mesFab < 1 || mesFab > 12) {
                System.out.println("Erro: mês inválido (deve estar entre 1 e 12).");
                mesFab = this.lerInteiroPositivo("Digite o mês de fabricação do veículo: ");
            }
            
            if (anoFab == anoAtual && mesFab > mesAtual) {
                System.out.println("Erro: mês de fabricação não pode ser futuro!");
                return;
            }
            
            int anoMod = this.lerInteiroPositivo("Digite o ano do modelo do veículo: ");
            while (anoMod < anoFab || anoMod > (anoFab + 1)) {
                System.out.println("Erro: ano do modelo deve ser igual ao ano de fabricação ou no máximo 1 ano posterior.");
                anoMod = this.lerInteiroPositivo("Digite o ano do modelo do veículo: ");
            }
            
            double valor = this.lerDoublePositivo("Digite o valor do veículo: ");

            int tipo = this.lerInteiro(
                "Escolha o tipo do veículo: \n" +
                "1) Elétrico\n" +
                "2) Combustão\n" +
                "3) Híbrido:\n"
            );
            
            while (tipo < 1 || tipo > 3) {
                System.out.println("Erro: opção inválida.");
                tipo = this.lerInteiro(
                    "Escolha o tipo do veículo: \n" +
                    "1) Elétrico\n" +
                    "2) Combustão\n" +
                    "3) Híbrido:\n"
                );
            }

            Veiculo v = null;

            switch (tipo) {
                case 1:
                    double autonomiaBat = this.lerDoublePositivo("Digite a autonomia da bateria (em km): ");
                    double capacidadeBat = this.lerDoublePositivo("Digite a capacidade da bateria (em kWh): ");
                    v = new Eletrico(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaBat, capacidadeBat);
                    s.adicionar(v);
                    System.out.println("Veículo cadastrado com sucesso!");
                    break;
                case 2:
                    double autonomiaComb = this.lerDoublePositivo("Digite a autonomia do motor (em km): ");
                    double capacidadeComb = this.lerDoublePositivo("Digite a capacidade do motor (em L): ");
                    v = new Combustao(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaComb, capacidadeComb);
                    s.adicionar(v);
                    System.out.println("Veículo cadastrado com sucesso!");
                    break;
                case 3:
                    double autComb = this.lerDoublePositivo("Digite a autonomia do motor (em km): ");
                    double capComb = this.lerDoublePositivo("Digite a capacidade do motor (em L): ");
                    double autBat = this.lerDoublePositivo("Digite a autonomia da bateria (em km): ");
                    double capBat = this.lerDoublePositivo("Digite a capacidade da bateria (em kWh): ");
                    v = new Hibrido(marca, modelo, anoFab, mesFab, anoMod, valor, autComb, capComb, autBat, capBat);
                    s.adicionar(v);
                    System.out.println("Veículo cadastrado com sucesso!");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar veículo: " + e.getMessage());
        }
    }

    public void cadVenda(Sistema s) {
        try {
            if (s.getVendedores().isEmpty()) {
                System.out.println("Erro: não há vendedores cadastrados!");
                return;
            }
            
            if (s.getVeiculos().isEmpty()) {
                System.out.println("Erro: não há veículos cadastrados!");
                return;
            }
            
            if (s.getClientes().isEmpty()) {
                System.out.println("Erro: não há clientes cadastrados!");
                return;
            }
            
            System.out.println("Vendedores cadastrados:");
            for (Vendedor v : s.getVendedores()) {
                System.out.println(v.getNome() + " - CPF: " + v.getCpf());
            }

            String cpfVend = this.lerLinhaObrigatoria("Digite o CPF do vendedor: ");
            Vendedor vend = s.localizarVendedor(cpfVend);
            if (vend == null) {
                System.out.println("Vendedor não encontrado!");
                return;
            }

            System.out.println("Veiculos cadastrados:");
            for (int i = 0; i < s.getVeiculos().size(); i++) {
                Veiculo vx = s.getVeiculos().get(i);
                System.out.println((i + 1) + ") " + vx);
            }

            int opc = this.lerInteiroPositivo("Escolha um veículo pelo número: ");
            while (opc < 1 || opc > s.getVeiculos().size()) {
                System.out.println("Opção de veículo inválida!");
                opc = this.lerInteiroPositivo("Escolha um veículo pelo número: ");
            }
            Veiculo vei = s.getVeiculos().get(opc - 1);

            System.out.println("Clientes cadastrados:");
            for (Cliente c : s.getClientes()) {
                System.out.println(c);
            }

            String cpfCli = this.lerLinhaObrigatoria("Digite o CPF do cliente: ");
            Cliente cli = s.localizarCliente(cpfCli);
            if (cli == null) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            double desconto = this.lerDoublePositivo("Digite o desconto (em R$): ");
            
            while (desconto > vei.getValor()) {
                System.out.println("Erro: desconto não pode ser maior que o valor do veículo!");
                desconto = this.lerDoublePositivo("Digite o desconto (em R$): ");
            }
            
            int dia = this.lerInteiroPositivo("Digite o dia da venda: ");
            int mes = this.lerInteiroPositivo("Digite o mês da venda: ");
            int ano = this.lerInteiroPositivo("Digite o ano da venda: ");
            
            while (!Data.validarData(dia, mes, ano)) {
                System.out.println("Erro: data inválida. Tente novamente.");
                dia = this.lerInteiroPositivo("Digite o dia da venda: ");
                mes = this.lerInteiroPositivo("Digite o mês da venda: ");
                ano = this.lerInteiroPositivo("Digite o ano da venda: ");
            }
            
            int anoAtual = LocalDate.now().getYear();
            while (ano < 2000 || ano > anoAtual) {
                System.out.println("Erro: ano inválido (deve estar entre 2000 e " + anoAtual + ").");
                ano = this.lerInteiroPositivo("Digite o ano da venda: ");
            }
            
            Data dataVenda = new Data(dia, mes, ano);
            Data hoje = obterDataAtual();
            
            if (dataVenda.compareTo(hoje) > 0) {
                System.out.println("Erro: data de venda não pode ser futura!");
                return;
            }
            
            Data dataFabricacao = new Data(1, vei.getMesFab(), vei.getAnoFab());
            
            if (dataVenda.compareTo(dataFabricacao) < 0) {
                System.out.println("Erro: data de venda não pode ser anterior à fabricação do veículo!");
                return;
            }
            
            if (dataVenda.compareTo(cli.getNasc()) <= 0) {
                System.out.println("Erro: data de venda não pode ser anterior ou igual ao nascimento do cliente!");
                return;
            }
            
            if (dataVenda.compareTo(vend.getNasc()) <= 0) {
                System.out.println("Erro: data de venda não pode ser anterior ou igual ao nascimento do vendedor!");
                return;
            }
            
            String chassi = this.lerLinhaObrigatoria("Digite o chassi do veículo: ");
            
            if (s.localizarChassi(chassi) != null) {
                System.out.println("Erro: chassi já foi vendido anteriormente!");
                return;
            }

            Venda v = new Venda(vei, cli, desconto, dataVenda, chassi, vend);
            s.atribuirVendaVendedor(v, vend);
            System.out.println("Venda cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar venda: " + e.getMessage());
        }
    }

    private void relatorioMensal(Sistema s) {
        try {
            int mes = this.lerInteiroPositivo("Digite o mês: ");
            while (mes < 1 || mes > 12) {
                System.out.println("Erro: mês inválido (deve estar entre 1 e 12).");
                mes = this.lerInteiroPositivo("Digite o mês: ");
            }
            
            int anoAtual = LocalDate.now().getYear();
            int ano = this.lerInteiroPositivo("Digite o ano: ");
            while (ano < 2000 || ano > anoAtual) {
                System.out.println("Erro: ano inválido (deve estar entre 2000 e " + anoAtual + ").");
                ano = this.lerInteiroPositivo("Digite o ano: ");
            }
            
            Data dataConsulta = new Data(1, mes, ano);
            Data hoje = obterDataAtual();
            
            if (dataConsulta.compareTo(hoje) > 0) {
                System.out.println("Aviso: período solicitado é futuro. Relatório pode estar vazio.");
            }
            
            s.relatorio(mes, ano);
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioAnual(Sistema s) {
        try {
            int anoAtual = LocalDate.now().getYear();
            int ano = this.lerInteiroPositivo("Digite o ano: ");
            while (ano < 2000 || ano > anoAtual) {
                System.out.println("Erro: ano inválido (deve estar entre 2000 e " + anoAtual + ").");
                ano = this.lerInteiroPositivo("Digite o ano: ");
            }
            
            s.relatorio(ano);
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioVendedor(Sistema s) {
        try {
            if (s.getVendedores().isEmpty()) {
                System.out.println("Erro: não há vendedores cadastrados!");
                return;
            }
            
            String cpf = this.lerLinhaObrigatoria("Digite o CPF do vendedor: ");
            Vendedor vend = s.localizarVendedor(cpf);
            if (vend == null) {
                System.out.println("Vendedor não encontrado!");
                return;
            }
            s.relatorio(vend);
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
    
    public void gerarRelatorioMensal(Sistema s) {
        relatorioMensal(s);
    }
    
    public void gerarRelatorioAnual(Sistema s) {
        relatorioAnual(s);
    }
    
    public void gerarRelatorioVendedor(Sistema s) {
        relatorioVendedor(s);
    }
}