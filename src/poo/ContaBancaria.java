package poo;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContaBancaria {
    private String titular, numeroConta;
    private String[] historico = new String[50];
    private int nmHistorico = 0;
    private Double saldo;

    public void informacoes(){ //Add informações da conta principal
        this.titular = JOptionPane.showInputDialog(null, "Digite o nome do titular: ", "NOME", JOptionPane.QUESTION_MESSAGE);
        this.numeroConta = JOptionPane.showInputDialog(null, "Digite o número da conta: ", "NÚMERO DA CONTA", JOptionPane.QUESTION_MESSAGE);
        this.saldo = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor do saldo: ", "NÚMERO DA CONTA", JOptionPane.QUESTION_MESSAGE));
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

    public void sacar(){ //Método de saque
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque: "));
        validarPositivo(valor, "saque");
        validarSaldo(valor, "saque");
        this.saldo -= valor;   
        JOptionPane.showMessageDialog(null,String.format("Saldo atual: R$%.2f", this.saldo), "OPERAÇÃO REALIZADA",JOptionPane.INFORMATION_MESSAGE
);
    }

    public void depositar(){ //Método de depósito
      double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor do depósito: ", "DEPÓSITO", JOptionPane.QUESTION_MESSAGE));
      validarPositivo(valor, "depósito");

        this.saldo += valor;
        JOptionPane.showMessageDialog(null, String.format("Saldo atual: R$%.2f", this.saldo), "OPERAÇÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE);
}

    public void transferir(){ //Método de Transferência
        String ContaTransferir = JOptionPane.showInputDialog(null, "Nome do titular para transferir: ", "CONTA DESTINO", JOptionPane.QUESTION_MESSAGE);
        double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor da transferência: ", "TRANSFERÊNCIA", JOptionPane.QUESTION_MESSAGE)); 
        validarPositivo(valor, "transferência");
        validarSaldo(valor, "Transferência");
        this.saldo -= valor;

        LocalDateTime hoje = LocalDateTime.now(); // Criando a data atual
        DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String DateTimeFormatada = hoje.format(format);

        historico[nmHistorico] = "valor: R$" + valor + "\nDestino: "+ ContaTransferir + "\nData: " + DateTimeFormatada;
        nmHistorico++;

        JOptionPane.showMessageDialog(null, String.format("Transferência realizada para %s, agora seu saldo é: R$%.2f", ContaTransferir, this.saldo, "OPERAÇÃO REALIZADA", JOptionPane.INFORMATION_MESSAGE));
    }

    public void historicoTransferencias(){
        if(nmHistorico == 0){
            JOptionPane.showMessageDialog(null, "Nenhuma Transferência realizada!");
        }

        String mensagem = "";
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
}
