package poo;

import javax.swing.JOptionPane;

public class Banco {
    public static void main(String[] args) {
        ContaBancaria x = new ContaBancaria();
        int opcao;

        x.informacoes();

        do{
        opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "[1] Sacar \n[2] Depositar \n[3] Transferir \n[4] Histórico de Transferências \n[5] Exibir Informações da conta \n[6] Sair", "MENU DE OPÇÕES", JOptionPane.QUESTION_MESSAGE));       
        switch (opcao) {
            case 1: //Saque
                try{
                    x.sacar();
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case 2: //Deposito
                try{
                    x.depositar();
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 3: //Transferência
                try{
                    x.transferir();
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.WARNING_MESSAGE);
                }   
                break;
            case 4: //Histórico de transferências
                x.historicoTransferencias();
                break;

            case 5: //Exibir informações da conta
                x.exibirInformacoes();
                break;

            case 6: //sair
                JOptionPane.showMessageDialog(null, "ENCERRANDO O PROGRAMA...", "FIM DO PROGRAMA", JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Número inválido, digite entre 1 e 4!", "Aviso",JOptionPane.WARNING_MESSAGE);
                break;
        }

    }while(opcao != 6); //Loop até o usuário digitar [6] para sair
}
    }
