package poo;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Loja {

    public enum StatusLoja { //Status da loja
        ATIVA, INATIVA, BLOQUEADA
    }

    public static class Endereco {
        private String rua, numero, bairro, cidade, estado, cep;

        public Endereco(String rua, String numero, String bairro,
                        String cidade, String estado, String cep) {
            this.rua = rua;
            this.numero = numero;
            this.bairro = bairro;
            this.cidade = cidade;
            this.estado = estado;
            this.cep = cep;
        }

        @Override
        public String toString() {
            return rua + ", " + numero + " - " + bairro + "\n" + cidade + "/" + estado + " - CEP: " + cep;
        }
    }

    private String nomeLoja, motivoStatus, cnpj, telefone, email;
    private StatusLoja status;        
    private Endereco endereco;    
    private double caixa;
    private int operacoesSemSaldo;
    private List<String> historicoCaixa;
    private List<Produto> produtos;
    private List<Venda> historicoVendas;

public Loja(String nomeLoja, String cnpj, Endereco endereco, String telefone, String email) { //Construtor da Loja
    this.nomeLoja = nomeLoja;
    this.cnpj = cnpj;           
    this.endereco = endereco;   
    this.telefone = telefone;    
    this.email = email;          
    this.status = StatusLoja.ATIVA;
    this.caixa = 0.0;
    this.operacoesSemSaldo = 0;
    this.historicoCaixa = new ArrayList<>();
    this.produtos = new ArrayList<>();
    this.historicoVendas = new ArrayList<>();
}

    public void configurarLoja() { 
        this.nomeLoja = JOptionPane.showInputDialog("Nome da loja:");
        this.status = StatusLoja.ATIVA;

        JOptionPane.showMessageDialog(null, "Loja configurada com sucesso!");
    }

    public double getCaixa() {
        return caixa;
    }

    private void registrarMovimentoCaixa(String desc) {
        historicoCaixa.add(desc);
    }

    public void creditar(double valor) { //Atualiza o valor do caixa
        caixa += valor;
        operacoesSemSaldo = 0;
        registrarMovimentoCaixa("Entrada: R$ " + String.format("%.2f", valor));
    }

    public boolean debitar(double valor) { //Atualiza a saída do caixa
        if (caixa >= valor) {
            caixa -= valor;
            registrarMovimentoCaixa("Saída: R$ " + String.format("%.2f", valor));
            return true;
        } else {
            operacoesSemSaldo++;
            if (operacoesSemSaldo >= 3) {
                status = StatusLoja.BLOQUEADA;
                motivoStatus = "Muitas operações sem saldo";
            }
            return false;
        }
    }

    public void listarHistoricoCaixa() { //Método para listar histórico
        if (historicoCaixa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma movimentação.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String h : historicoCaixa) {
            sb.append(h).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(),
                "Histórico do Caixa", JOptionPane.INFORMATION_MESSAGE);
    }


    public void adicionarProduto(Produto produto) { //Adiciona produto
        produtos.add(produto);
    }

    public Produto buscarProdutoPorCodigo(String codigo) { //Busca produto
        for (Produto p : produtos) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Produto p : produtos) {
            sb.append(p).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(),"Produtos", JOptionPane.INFORMATION_MESSAGE);
    }


    public void comprarDoFornecedor() { //Método de compra do fornecedor

        if (status != StatusLoja.ATIVA) {
            JOptionPane.showMessageDialog(null, "Loja não está ativa!");
            return;
        }

        String codigo = JOptionPane.showInputDialog("Crie um código para o produto:");
        String nome = JOptionPane.showInputDialog("Nome do produto:");
        double precoFornecedor = Double.parseDouble(
        JOptionPane.showInputDialog("Preço do fornecedor:"));
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade comprada:"));
        double custoTotal = precoFornecedor * quantidade;

        
        if (!produtos.isEmpty()) {// Primeira compra é gratuita
            if (!debitar(custoTotal)) {
                JOptionPane.showMessageDialog(null, "Caixa insuficiente!");
                return;
            }
        }

        Produto produto = buscarProdutoPorCodigo(codigo);

        if (produto == null) {
            produto = new Produto(codigo, nome, precoFornecedor, quantidade);
            adicionarProduto(produto);
        } else {
            produto.adicionarEstoque(quantidade);
        }

        registrarMovimentoCaixa("Compra do fornecedor | Produto: " + nome +" | Total: R$ " + String.format("%.2f", custoTotal));

        JOptionPane.showMessageDialog(null,
                "Compra realizada com sucesso!");
    }


    public boolean realizarVenda(String codigoProduto, int qtd, Cliente cliente) { //Venda para o cliente

        if (status != StatusLoja.ATIVA) {
            JOptionPane.showMessageDialog(null, "Loja não está ativa!");
            return false;
        }

        Produto produto = buscarProdutoPorCodigo(codigoProduto);
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return false;
        }

        if (produto.getQuantidadeEstoque() < qtd) {
            JOptionPane.showMessageDialog(null, "Estoque insuficiente!");
            return false;
        }

        double total = produto.getPrecoVenda() * qtd;

        if (cliente.getConta().getSaldo() < total) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
            return false;
        }

        cliente.getConta().sacar(total);
        cliente.getConta().registrarHistorico(
                "Compra na loja " + nomeLoja + " - R$ " + total);

        creditar(total);
        produto.baixarEstoque(qtd);
        historicoVendas.add(new Venda(produto, qtd, cliente.getNome()));

        JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
        return true;
    }

    public void listarVendas() { //Lista as vendas feitas
        if (historicoVendas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma venda registrada.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Venda v : historicoVendas) {
            sb.append(v).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(),
                "Histórico de Vendas", JOptionPane.INFORMATION_MESSAGE);
    }

    public void executar(ArmazenamentoDados dados) {

        if (status == StatusLoja.INATIVA) {
            configurarLoja();
        }

        int opcao;

        //Menu da Loja
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(" [1] Comprar Produto\n [2] Listar Produtos\n [3] Comprar do Fornecedor\n [4] Consultar Caixa\n [5] Histórico do Caixa\n [6] Sair da Loja"));

            switch (opcao) {
                case 1 -> {
                    String codigo = JOptionPane.showInputDialog("Código do produto:");
                    int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));
                    String numeroConta = JOptionPane.showInputDialog("Conta do cliente:");
                    ContaBancaria conta = dados.buscarConta(numeroConta);

                    if (conta != null) {
                        Cliente cliente = new Cliente(conta.getTitular(), conta);
                        realizarVenda(codigo, qtd, cliente);
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                }
                case 2 -> listarProdutos();
                case 3 -> comprarDoFornecedor();
                case 4 -> JOptionPane.showMessageDialog(
                            null, "Caixa: R$ " + String.format("%.2f", caixa));
                case 5 -> listarHistoricoCaixa();
                case 6 -> JOptionPane.showMessageDialog(null, "Saindo da loja...");
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } while (opcao != 6);
    }
}