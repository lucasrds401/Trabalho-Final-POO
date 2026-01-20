package poo;

public class Produto {

    private String nome, codigo;
    private double precoFornecedor, precoVenda;
    private int quantidadeEstoque;

    public Produto(String codigo, String nome, double precoFornecedor, int quantidadeEstoque) {

        this.codigo = codigo;
        this.nome = nome;
        this.precoFornecedor = precoFornecedor;
        this.precoVenda = precoFornecedor; // começa igual ao fornecedor
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean baixarEstoque(int quantidade) { //Método de dar baixa no estoque
        if (quantidade <= quantidadeEstoque) {
            quantidadeEstoque -= quantidade;
            return true;
        }
        return false;
    }

    public void adicionarEstoque(int quantidade) {
        quantidadeEstoque += quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoFornecedor() {
        return precoFornecedor;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome +" | Compra: R$ " + String.format("%.2f", precoFornecedor) +" | Venda: R$ " + String.format("%.2f", precoVenda) +" | Estoque: " + quantidadeEstoque;
    }
}
