package poo;

public class Cliente {

    private String nome;
    private ContaBancaria conta;

    public Cliente(String nome) {
        this.nome = nome;
        this.conta = null;
    }

    public Cliente(String nome, ContaBancaria conta) { //Construtor do Cliente
        this.nome = nome;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }
}
