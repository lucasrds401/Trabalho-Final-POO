package poo;

import javax.swing.JOptionPane;

public class SistemaBanco {

    public void executar(ArmazenamentoDados dados) {

        Gerente gerente = new Gerente("Gerente", "1234", dados); //Nome e senha do user gerente
        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("[1] Gerente\n[2] Pessoa Física\n[3] Voltar"));

            switch (opcao) {

                case 1: // Gerente
                    if (gerente.autenticar()) {
                        gerente.criarConta();
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta!");
                    }
                    break;

                case 2: // Pessoa física
                    menuPessoaFisica(dados);
                    break;

                case 3: //Sair
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 3);
    }

    private void menuPessoaFisica(ArmazenamentoDados dados) {

        String numero = JOptionPane.showInputDialog("Digite o número da sua conta:");
        ContaBancaria conta = dados.buscarConta(numero);

        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Conta não encontrada!");
            return;
        }

        int opcao;
        double valor;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("[1] Sacar\n [2] Depositar\n [3] Transferir\n [4] Histórico\n [5] Informações\n [6] Voltar"));

            switch (opcao) {

                case 1: //Saque
                        valor = Double.parseDouble(
                                JOptionPane.showInputDialog("Valor do saque:"));
                        conta.sacar(valor);
                    break;

                case 2: //Depósito
                        valor = Double.parseDouble(JOptionPane.showInputDialog("Valor do depósito:"));
                        conta.depositar(valor);
                    break;

                case 3: //Transferência
                    String destino = JOptionPane.showInputDialog("Conta destino:");
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));
                    dados.transferir(conta.getNumeroConta(), destino, valor);
                    break;

                case 4: //Histórico
                    conta.historicoTransferencias();
                    break;

                case 5: //Exibir informações
                    conta.exibirInformacoes();
                    break;

                case 6: //Sair
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 6);
    }
}
