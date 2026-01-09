package poo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ArmazenamentoDados {
    private ArrayList<ContaBancaria> contas = new ArrayList<>(); //Guarda objetos do Tipo conta bancária
    private int numeroCt = 1900; //Gera número de conta, contando apartir do 1900

    private ContaBancaria buscarConta(String numero){
        for(ContaBancaria c : contas){ //Procura todas as contas armazenadas
            if(c.getNumeroConta().equals(numero)){ //Compara a conta procurada com o número informado
                return c;
            }
        }
        return null;
    }

  public ContaBancaria buscarContaPublic(String numeroConta){
    return buscarConta(numeroConta); 
}

    public String criarConta(String titular, String cpf, String email, String endereco){
        String numerogerado = String.valueOf(numeroCt);
        numeroCt++; //Cria uma nova conta bancária

        contas.add(new ContaBancaria(titular, cpf, email, endereco, numerogerado)); //Cria o objeto e adiciona na lista de contas

        return numerogerado; 
    }

    public boolean transferir(String origemNum, String destinoNum, double valor){ //Método de transferência
        ContaBancaria origem = buscarConta(origemNum);
        ContaBancaria destino = buscarConta(destinoNum);

        if(origem == null || destino == null){ //Validação da conta
            JOptionPane.showMessageDialog(null, "Conta inválida!");
            return false;
        }

        try{
            origem.sacar(valor); //Tenta sacar o valor da conta
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage()); //Aparece se o saldo for insuficiente ou ocorrer outro erro
            return false;
        }

        destino.setSaldo(destino.getSaldo() + valor); //Soma o saldo com o valor atual da conta

        JOptionPane.showMessageDialog(null, "Transferência Concluída! \nOrigem:" + origemNum + "\nDestino: " + destinoNum + "\nValor: R$: " + valor);
        return true;
    }

    public void listarContas(){
        if(contas.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada!"); //Quando não tiver nenhuma conta
            return;
        }

        String mensagem = "CONTAS CADASTRADAS\n";
        for(ContaBancaria c : contas){
            mensagem += "\nTitular: " + c.getTitular() + "\nConta: " + c.getNumeroConta() +"\nCPF: " + c.getCpf();
        }

        JOptionPane.showMessageDialog(null, mensagem);
        }
    }
