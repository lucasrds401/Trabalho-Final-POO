package poo;

import java.util.HashMap;
import java.util.Map;

public class Compra {

    private String notaFiscal;
    private Map<Produto, Integer> itens; //Relaciona os produtos comprados com a quantidade
    private double valorTotal;

    public Compra(String notaFiscal) { //Inicializa a compra
        this.notaFiscal = notaFiscal;
        this.itens = new HashMap<>();
        this.valorTotal = 0.0;
    }

    public void adicionarProduto(Produto produto, int quantidade, double precoCompra) {
        itens.put(produto, quantidade);// adiciona produto à compra

        double precoVenda = precoCompra * 1.3;// define preço de venda com 30% de lucro
        produto.setPrecoVenda(precoVenda);

        produto.adicionarEstoque(quantidade);// adiciona ao estoque

        valorTotal += precoCompra * quantidade;// atualiza valor total da compra
    }
    
    public double getValorTotal() {
        return valorTotal;
    }

    public Map<Produto, Integer> getItens() {
        return itens;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }
}
