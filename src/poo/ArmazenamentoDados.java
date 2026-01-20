package poo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArmazenamentoDados {
    private ArrayList<ContaBancaria> contas = new ArrayList<>(); //Guarda as contas criadas
    private int numeroCt = 1900; //Número da conta começam em 1900

    public ContaBancaria buscarConta(String numero) { //Método para buscar contas
        for (ContaBancaria c : contas) { //"For" para todas as contas criadas
            if (c.getNumeroConta().equals(numero)) { //Se a conta for = ao número dito
                return c;
            }
        }
        return null;
    }

    public String criarConta(String titular, String cpf, String email, String endereco) { //Método para criar conta
        String numeroGerado = String.valueOf(numeroCt);
        numeroCt++; //Adiciona o número da conta, começando no 1900

        contas.add(new ContaBancaria(titular, cpf, email, endereco, numeroGerado)); //Cria a conta com todos
        return numeroGerado; //Retorna o número da conta
    }

    public boolean transferir(String origemNum, String destinoNum, double valor) {//Método de transferência

    ContaBancaria origem = buscarConta(origemNum);
    ContaBancaria destino = buscarConta(destinoNum);

    if (origem == null || destino == null) { //Procurta a conta de origem
        JOptionPane.showMessageDialog(null, "Conta de origem ou destino não encontrada!");
        return false;
    }

    if (!origem.isAtiva() || !destino.isAtiva()) {
        JOptionPane.showMessageDialog(null, "Conta inativa!");
        return false;
    }

    if (origem.getSaldo() < valor) {
        JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        return false;
    }

    
    origem.setSaldo(origem.getSaldo() - valor);// movimentação
    destino.setSaldo(destino.getSaldo() + valor);

    origem.registrarHistorico("Transferência enviada para conta " + destinoNum +" - R$ " + String.format("%.2f", valor));

    destino.registrarHistorico("Transferência recebida da conta " + origemNum +" - R$ " + String.format("%.2f", valor));

    JOptionPane.showMessageDialog(null,"Transferência concluída!\n" +"Origem: " + origemNum + "\n" +"Destino: " + destinoNum + "\n" + "Valor: R$ " + String.format("%.2f", valor),"SUCESSO",JOptionPane.INFORMATION_MESSAGE);

    return true;
}

    public void listarContas() { //Método para mostrar contas cadastradas
        if (contas.isEmpty()) { //Verifica se há contas cadastradas
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada!");
            return;
        }

        String mensagem = "CONTAS CADASTRADAS\n";

        for (ContaBancaria c : contas) { //Percorre todas as contas cadastradas
            mensagem += "\nTitular: " + c.getTitular() +"\nConta: " + c.getNumeroConta() + "\nCPF: " + c.getCpf() + "\n";
        }

        JOptionPane.showMessageDialog(null, mensagem);
    }
}
