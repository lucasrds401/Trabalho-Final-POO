package poo;

import javax.swing.JOptionPane;

public class ContaBancaria {
    private String titular, numeroConta, cpf, email, endereco;
    private String senhagerente = "1234";
    private String[] historico = new String[50];
    private int nmHistorico = 0;
    private double saldo;
    private boolean ativa = true;

    public ContaBancaria(String titular, String cpf, String email, String endereco, String numeroConta) { //Construtor da Conta Bancária
        this.titular = titular;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
    }

    public boolean isAtiva() { //retorna se a conta está ativa
        return ativa;
    }

    public void bloquearConta() { //desativa a conta.
        this.ativa = false;
    }

    public void ativarConta() { //ativa novamente a conta.
        this.ativa = true;
    }


    private boolean validarPositivo(double valor, String operacao) { //Verifica se a operação é positiva
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null,"O valor da operação de  + operacao +  deve ser maior que zero!","ERRO",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarSaldo(double valor, String operacao) { //verifica se há saldo suficiente para as operações
        if (valor > this.saldo) {
            JOptionPane.showMessageDialog(null,String.format("Saldo insuficiente para %s! Saldo atual: R$%.2f", operacao, this.saldo),"ERRO",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    public void sacar(double valor) {
        if (!validarPositivo(valor, "saque")) return; //Verifica se é positivo
        if (!validarSaldo(valor, "saque")) return; //Verifica se está disponível

        this.saldo -= valor; //Subtração do saldo pelo valor retirado

        JOptionPane.showMessageDialog(null,String.format("Saldo atual: R$%.2f", this.saldo),"OPERAÇÃO REALIZADA",JOptionPane.INFORMATION_MESSAGE);
    }

    public void depositar(double valor) { //Método de depósito
        if (!validarPositivo(valor, "depósito")) return;

        this.saldo += valor; //Soma o valor atribuído ao saldo
        JOptionPane.showMessageDialog(null,String.format("Saldo atual: R$%.2f", this.saldo),"OPERAÇÃO REALIZADA",JOptionPane.INFORMATION_MESSAGE);
    }

    public void registrarHistorico(String mensagem) { //Método para registar Histórico de Transações
        if (nmHistorico < historico.length) {
            historico[nmHistorico] = mensagem;
            nmHistorico++;
        }
    }

    public void historicoTransferencias() { //mostra todas as transferências registradas
        if (nmHistorico == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma transferência realizada!");
            return;
        }

        for (int i = 0; i < nmHistorico; i++) {
            JOptionPane.showMessageDialog(null,historico[i], (i + 1) + "ª TRANSFERÊNCIA",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void exibirInformacoes() { //Exibe informações da conta
        JOptionPane.showMessageDialog(null,"Nome do titular: " + titular +"\nNúmero da Conta: " + numeroConta +"\nSaldo atual: R$" + String.format("%.2f", saldo),"INFORMAÇÕES",JOptionPane.INFORMATION_MESSAGE);
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenhagerente() {
        return senhagerente;
    }

    public void setSenhagerente(String senhagerente) {
        this.senhagerente = senhagerente;
    }
    public void setSaldo(double saldo) {
    this.saldo = saldo;
}
}
