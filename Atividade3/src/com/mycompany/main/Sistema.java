package com.mycompany.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sistema {
    private List<Cliente> clientes;
    private List<Veiculo> veiculos;
    private List<Vendedor> vendedores;
    private List<Gerente> gerentes;
    
    public Sistema() {
        this.clientes = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.gerentes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
    
    public List<Vendedor> getVendedores() {
        return vendedores;
    }
    
    public List<Gerente> getGerentes() {
        return gerentes;
    }

    public void adicionar(Cliente cliente) {
        this.clientes.add(cliente);
    }
    
    public void adicionar(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }
    
    public void adicionar(Vendedor vendedor) {
        this.vendedores.add(vendedor);
    }
    
    public void adicionar(Gerente gerente) {
        this.gerentes.add(gerente);
    }

    public void listarClientes() {
        System.out.println("Clientes cadastrados:");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado");
        }
        else {
            for (Cliente c : this.clientes) {
                System.out.println(c);
            }
        }
    }
    
    public void listarVeiculos() {
        System.out.println("Veículos cadastrados:");

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado");
        }
        else {
            for (Veiculo v : this.veiculos) {
                System.out.println(v);
            }
        }
    }
    
    public void listarVendedores() {
        System.out.println("Vendedores cadastrados:");

        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado");
        }
        else {
            for (Vendedor v : this.vendedores) {
                System.out.println(v);
            }
        }
    }
    
    public void listarGerentes() {
        System.out.println("Gerentes cadastrados:");

        if (gerentes.isEmpty()) {
            System.out.println("Nenhum gerente cadastrado");
        }
        else {
            for (Gerente g : this.gerentes) {
                System.out.println(g);
            }
        }
    }

    public Cliente localizarCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }   
    
    public Gerente localizarGerente(String cpf) {
        for (Gerente g : this.gerentes) {
            if (g.getCpf().equals(cpf)) {
                return g;
            }
        }
        return null;
    }
    
    public Vendedor localizarVendedor(String cpf) {
        for (Vendedor v : this.vendedores) {
            if (v.getCpf().equals(cpf)) {
                return v;
            }
        }
        return null;
    }
    
    public Venda localizarChassi(String chassi) {
        for (Vendedor v : this.vendedores) {
            for (Venda venda : v.getVendidos()) {
                if (venda.getChassi().equals(chassi)) {
                    return venda;
                }
            }
        }
        return null;
    }
    
    public boolean cpfJaExiste(String cpf) {
        if (this.localizarCliente(cpf) != null) {
            return true;
        }
        if (this.localizarVendedor(cpf) != null) {
            return true;
        }
        if (this.localizarGerente(cpf) != null) {
            return true;
        }
        return false;
    }
    
    public void atribuirVendaVendedor(Venda venda, Vendedor vendedor) {
        vendedor.addVenda(venda);
    }
    
    public void relatorio(int mes, int ano) {
        System.out.println("*** RELATÓRIO DE VENDAS MENSAL DE " + mes + "/" + ano + " ***");
        double total = 0;
        
        for (Vendedor v: vendedores) {
            for (Venda venda : v.getVendidos()) {
                if (venda.getData().getAno() == ano && venda.getData().getMes() == mes) {
                    System.out.println("Vendedor " + v.getNome() + "(Salário "
                            + "neste mês: R$" + v.getSalario(mes, ano) + ")");
                    System.out.println(venda);
                    System.out.println("***************************************");
                    total = total + (venda.valor());
                }
            }
        }
        System.out.println("Total: R$ " + total);
    }
    
    public void relatorio(int ano) {
        System.out.println("*** RELATÓRIO DE VENDAS ANUAL DE " + ano + " ***");
        double total = 0;
        
        List<Venda> todasVendas = new ArrayList<>();
        
        for (Vendedor v: vendedores) {
            for (Venda venda: v.getVendidos()) {
                if (venda.getData().getAno() == ano) {
                    todasVendas.add(venda);
                }
            }
        }
        
        Collections.sort(todasVendas, new ComparadorVenda());
        
        for (Venda venda : todasVendas) {
            System.out.println("Vendedor: " + venda.getVendedor().getNome());
            System.out.println(venda);
            System.out.println("***************************************");
            total = total + (venda.valor());
        }
        
        System.out.println("Total: R$ " + total);
    }
    
    public void relatorio(Vendedor vendedor) {
        System.out.println("*** RELATÓRIO DE VENDAS DO VENDEDOR ***");
        System.out.println("Vendas do vendedor " + vendedor.getNome() + " :");
        double total = 0;
        
        for (Venda venda: vendedor.getVendidos()) {
            System.out.println(venda);
            System.out.println("***************************************");
            total = total + (venda.valor());
        }
        
        System.out.println("Total: R$" + total);
    }
    
    public void salvarDados() {
        BufferedWriter buff = null;
        try {
            FileWriter f = new FileWriter("dados.txt");
            buff = new BufferedWriter(f);
            
            buff.write(clientes.size() + "\n");
            for (Cliente c : clientes) {
                buff.write(c.getNome() + "\n");
                buff.write(c.getCpf() + "\n");
                buff.write(c.getNasc().getDia() + "\n");
                buff.write(c.getNasc().getMes() + "\n");
                buff.write(c.getNasc().getAno() + "\n");
                buff.write(c.getEmail() + "\n");
            }
            
            buff.write(gerentes.size() + "\n");
            for (Gerente g : gerentes) {
                buff.write(g.getNome() + "\n");
                buff.write(g.getCpf() + "\n");
                buff.write(g.getNasc().getDia() + "\n");
                buff.write(g.getNasc().getMes() + "\n");
                buff.write(g.getNasc().getAno() + "\n");
                buff.write(g.getSalario(1, 2025) + "\n");
                buff.write(g.getSenha() + "\n");
            }
            
            buff.write(vendedores.size() + "\n");
            for (Vendedor v : vendedores) {
                buff.write(v.getNome() + "\n");
                buff.write(v.getCpf() + "\n");
                buff.write(v.getNasc().getDia() + "\n");
                buff.write(v.getNasc().getMes() + "\n");
                buff.write(v.getNasc().getAno() + "\n");
                buff.write(v.getSalario(1, 2025) - v.comissaoTotal(1, 2025) + "\n");
                buff.write(v.getComissao() + "\n");
            }
            
            buff.write(veiculos.size() + "\n");
            for (Veiculo vei : veiculos) {
                if (vei instanceof Eletrico) {
                    buff.write("ELETRICO\n");
                    Eletrico e = (Eletrico) vei;
                    buff.write(e.getMarca() + "\n");
                    buff.write(e.getModelo() + "\n");
                    buff.write(e.getAnoFab() + "\n");
                    buff.write(e.getMesFab() + "\n");
                    buff.write(e.getAnoMod() + "\n");
                    buff.write(e.getValor() + "\n");
                    buff.write(e.getAutonomiaBat() + "\n");
                    buff.write(e.getCapacidadeBat() + "\n");
                } else if (vei instanceof Combustao) {
                    buff.write("COMBUSTAO\n");
                    Combustao c = (Combustao) vei;
                    buff.write(c.getMarca() + "\n");
                    buff.write(c.getModelo() + "\n");
                    buff.write(c.getAnoFab() + "\n");
                    buff.write(c.getMesFab() + "\n");
                    buff.write(c.getAnoMod() + "\n");
                    buff.write(c.getValor() + "\n");
                    buff.write(c.getAutonomiaComb() + "\n");
                    buff.write(c.getCapacidadeComb() + "\n");
                } else if (vei instanceof Hibrido) {
                    buff.write("HIBRIDO\n");
                    Hibrido h = (Hibrido) vei;
                    buff.write(h.getMarca() + "\n");
                    buff.write(h.getModelo() + "\n");
                    buff.write(h.getAnoFab() + "\n");
                    buff.write(h.getMesFab() + "\n");
                    buff.write(h.getAnoMod() + "\n");
                    buff.write(h.getValor() + "\n");
                    buff.write(h.getAutonomiaComb() + "\n");
                    buff.write(h.getCapacidadeComb() + "\n");
                    buff.write(h.getAutonomiaBat() + "\n");
                    buff.write(h.getCapacidadeBat() + "\n");
                }
            }
            
            int totalVendas = 0;
            for (Vendedor v : vendedores) {
                totalVendas += v.getVendidos().size();
            }
            
            buff.write(totalVendas + "\n");
            for (Vendedor v : vendedores) {
                for (Venda venda : v.getVendidos()) {
                    buff.write(v.getCpf() + "\n");
                    buff.write(venda.getCliente().getCpf() + "\n");
                    
                    int indiceVeiculo = veiculos.indexOf(venda.getVeiculo());
                    buff.write(indiceVeiculo + "\n");
                    
                    buff.write(venda.getDesconto() + "\n");
                    buff.write(venda.getData().getDia() + "\n");
                    buff.write(venda.getData().getMes() + "\n");
                    buff.write(venda.getData().getAno() + "\n");
                    buff.write(venda.getChassi() + "\n");
                }
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo: " + e.getMessage());
                }
            }
        }
    }
    
    public void carregarDados() {
        BufferedReader buff = null;
        try {
            FileReader f = new FileReader("dados.txt");
            buff = new BufferedReader(f);
            
            int numClientes = Integer.parseInt(buff.readLine());
            for (int i = 0; i < numClientes; i++) {
                String nome = buff.readLine();
                String cpf = buff.readLine();
                int dia = Integer.parseInt(buff.readLine());
                int mes = Integer.parseInt(buff.readLine());
                int ano = Integer.parseInt(buff.readLine());
                String email = buff.readLine();
                
                Cliente c = new Cliente(nome, cpf, dia, mes, ano, email);
                clientes.add(c);
            }
            
            int numGerentes = Integer.parseInt(buff.readLine());
            for (int i = 0; i < numGerentes; i++) {
                String nome = buff.readLine();
                String cpf = buff.readLine();
                int dia = Integer.parseInt(buff.readLine());
                int mes = Integer.parseInt(buff.readLine());
                int ano = Integer.parseInt(buff.readLine());
                double salario = Double.parseDouble(buff.readLine());
                String senha = buff.readLine();
                
                Gerente g = new Gerente(nome, cpf, dia, mes, ano, salario, senha);
                gerentes.add(g);
            }
            
            int numVendedores = Integer.parseInt(buff.readLine());
            for (int i = 0; i < numVendedores; i++) {
                String nome = buff.readLine();
                String cpf = buff.readLine();
                int dia = Integer.parseInt(buff.readLine());
                int mes = Integer.parseInt(buff.readLine());
                int ano = Integer.parseInt(buff.readLine());
                double salario = Double.parseDouble(buff.readLine());
                double comissao = Double.parseDouble(buff.readLine());
                
                Vendedor v = new Vendedor(nome, cpf, dia, mes, ano, salario, comissao);
                vendedores.add(v);
            }
            
            int numVeiculos = Integer.parseInt(buff.readLine());
            for (int i = 0; i < numVeiculos; i++) {
                String tipo = buff.readLine();
                String marca = buff.readLine();
                String modelo = buff.readLine();
                int anoFab = Integer.parseInt(buff.readLine());
                int mesFab = Integer.parseInt(buff.readLine());
                int anoMod = Integer.parseInt(buff.readLine());
                double valor = Double.parseDouble(buff.readLine());
                
                if (tipo.equals("ELETRICO")) {
                    double autonomiaBat = Double.parseDouble(buff.readLine());
                    double capacidadeBat = Double.parseDouble(buff.readLine());
                    Eletrico e = new Eletrico(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaBat, capacidadeBat);
                    veiculos.add(e);
                } else if (tipo.equals("COMBUSTAO")) {
                    double autonomiaComb = Double.parseDouble(buff.readLine());
                    double capacidadeComb = Double.parseDouble(buff.readLine());
                    Combustao c = new Combustao(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaComb, capacidadeComb);
                    veiculos.add(c);
                } else if (tipo.equals("HIBRIDO")) {
                    double autonomiaComb = Double.parseDouble(buff.readLine());
                    double capacidadeComb = Double.parseDouble(buff.readLine());
                    double autonomiaBat = Double.parseDouble(buff.readLine());
                    double capacidadeBat = Double.parseDouble(buff.readLine());
                    Hibrido h = new Hibrido(marca, modelo, anoFab, mesFab, anoMod, valor, autonomiaComb, capacidadeComb, autonomiaBat, capacidadeBat);
                    veiculos.add(h);
                }
            }
            
            int numVendas = Integer.parseInt(buff.readLine());
            for (int i = 0; i < numVendas; i++) {
                String cpfVendedor = buff.readLine();
                String cpfCliente = buff.readLine();
                int indiceVeiculo = Integer.parseInt(buff.readLine());
                double desconto = Double.parseDouble(buff.readLine());
                int dia = Integer.parseInt(buff.readLine());
                int mes = Integer.parseInt(buff.readLine());
                int ano = Integer.parseInt(buff.readLine());
                String chassi = buff.readLine();
                
                Vendedor vend = localizarVendedor(cpfVendedor);
                Cliente cli = localizarCliente(cpfCliente);
                Veiculo vei = veiculos.get(indiceVeiculo);
                
                Venda venda = new Venda(vei, cli, desconto, new Data(dia, mes, ano), chassi, vend);
                vend.addVenda(venda);
            }
            
        } catch (IOException e) {
            System.out.println("Nenhum dado anterior encontrado. Iniciando sistema vazio.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao ler dados: formato inválido.");
        } catch (NullPointerException e) {
            System.out.println("Erro ao ler dados: referência nula encontrada.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro ao ler dados: índice inválido.");
        } finally {
            if (buff != null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo: " + e.getMessage());
                }
            }
        }
    }
}

class ComparadorVenda implements Comparator<Venda> {
    @Override
    public int compare(Venda v1, Venda v2) {
        int comparaNome = v1.getVendedor().getNome().compareTo(v2.getVendedor().getNome());
        if (comparaNome != 0) {
            return comparaNome;
        }
        
        if (v1.valor() > v2.valor()) {
            return -1;
        }
        if (v1.valor() < v2.valor()) {
            return 1;
        }
        
        int comparaData = v2.getData().compareTo(v1.getData());
        if (comparaData != 0) {
            return comparaData;
        }
        
        return v1.getCliente().getCpf().compareTo(v2.getCliente().getCpf());
    }
}
