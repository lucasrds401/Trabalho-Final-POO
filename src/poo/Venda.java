package poo;

import java.time.LocalDateTime;

public class Venda {

    private Produto produto;
    private int quantidade;
    private double valorTotal;
    private LocalDateTime dataHora;
    private String nomeCliente;

    public Venda(Produto produto, int quantidade, String nomeCliente) { //Construtor de Venda
        this.produto = produto;
        this.quantidade = quantidade;
        this.nomeCliente = nomeCliente;
        this.valorTotal = produto.getPrecoVenda() * quantidade;
        this.dataHora = LocalDateTime.now();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() { //Informações sobre a venda
        return dataHora +"- Cliente: " + nomeCliente + "- Produto: " + produto.getCodigo() +" - Quantidade: " + quantidade +" - Total: R$ " + valorTotal;
    }
}
