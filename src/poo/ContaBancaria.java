package poo;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContaBancaria {
    private String titular, numeroConta, cpf, email, endereco;
    private String senhagerente = "1234";
    private String[] historico = new String[50];
    private int nmHistorico = 0;
    private Double saldo;

    public ContaBancaria(String titular, String cpf, String email, String endereco, String numeroConta){
        this.titular = titular;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.numeroConta = numeroConta;
        this.saldo = 0.0;

    }
    
    private void validarPositivo(double valor, String operacao){ //Função para verificar se o valor é positivo
        if(valor <= 0){ 
            throw new ValorInvalidoException("O valor da operação de " + operacao + " deve ser maior que zero!");
        }
    }

    private void validarSaldo(double valor, String operacao){ //Função para verificar se o valor é maior que o saque
        if(valor > this.saldo){
            throw new SaldoInsuficienteException(String.format("Saldo insuficiente para %s! saldo atual: R$%.2f", operacao,  this.saldo));
        }
    }

    public void sacar(double valor){ //Método de saque
        validarPositivo(valor, "saque");
        validarSaldo(valor, "saque");
        this.saldo -= valor;   
        JOptionPane.showMessageDialog(null,String.format("Saldo atual: R$%.2f", this.saldo), "OPERAÇÃO REALIZADA",JOptionPane.INFORMATION_MESSAGE);
    }

    public void depositar(double valor){ //Método de depósito
      validarPositivo(valor, "depósito");
        this.saldo += valor;
        JOptionPane.showMessageDialog(null, String.format("Saldo atual: R$%.2f", this.saldo), "OPERAÇÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
}

    public void registrarHistorico(String mensagem){ //Salva a mensagem no histórico
        historico[nmHistorico] = mensagem;
        nmHistorico++;
    }

    public void historicoTransferencias(){
        if(nmHistorico == 0){
            JOptionPane.showMessageDialog(null, "Nenhuma Transferência realizada!");
        }

        String mensagem = ""; //Mostra uma transferência por vez
        for(int i = 0; i < nmHistorico; i++){
            mensagem = historico[i];

            JOptionPane.showMessageDialog(null, mensagem, (i+1) +"º TRANSFERÊNCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void exibirInformacoes(){ //Método de exibir informações da conta
        JOptionPane.showMessageDialog(null, "Nome do titular: "+ titular + "\nNúmero da Conta: "+ numeroConta + "\nSaldo atual: R$" + saldo, "INFORMAÇÕES", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroConta(){
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

    public String[] getHistorico() {
        return historico;
    }

    public void setHistorico(String[] historico) {
        this.historico = historico;
    }

    public int getNmHistorico() {
        return nmHistorico;
    }

    public void setNmHistorico(int nmHistorico) {
        this.nmHistorico = nmHistorico;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    
}
