package poo;

import javax.swing.JOptionPane;

public class Banco {
    public static void main(String[] args) {

        ArmazenamentoDados x = new ArmazenamentoDados(); //Criação do "Banco de dados"
        int opcao1;
        
        Gerente gerente = new Gerente("Gerente", "1234", x);

        do {
            opcao1 = Integer.parseInt(JOptionPane.showInputDialog( null, "[1] Gerente \n[2] Pessoa Física \n[3] Sair", "ESCOLHA UMA DAS ALTERNATIVAS:", JOptionPane.QUESTION_MESSAGE));
            switch (opcao1) {
                case 1: //Gerente
                    if (gerente.autenticar()) {
                        gerente.criarConta();
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta!");
                    }
                    break;

                case 2: //Pessoa física
                    String numero = JOptionPane.showInputDialog("Digite o número da sua conta: ");
                    ContaBancaria conta = x.buscarContaPublic(numero);

                    if (conta == null) {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                        break;
                    }

                    int opcao2;
                    double valor;

                    do {
                        opcao2 = Integer.parseInt(JOptionPane.showInputDialog(
                            "[1] Sacar \n[2] Depositar \n[3] Transferir \n[4] Histórico \n[5] Informações da conta \n[6] Sair"));

                        switch (opcao2) {

                            case 1: //Sacar
                                try {
                                    valor = Double.parseDouble(
                                        JOptionPane.showInputDialog("Digite o valor do saque: ")); 
                                        conta.sacar(valor);
                                        } catch (ValorInvalidoException | SaldoInsuficienteException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                        } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Digite um valor numérico válido!");
                                }
                                break;

                            case 2: //Depositar
                                    try {
                                        valor = Double.parseDouble(
                                        JOptionPane.showInputDialog("Digite o valor do depósito: "));
                                        conta.depositar(valor);
                                        } catch (ValorInvalidoException e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage());
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Digite um valor numérico válido!");
                                }
                                break;

                            case 3: //Transferir
                                String destino = JOptionPane.showInputDialog("Conta destino: ");
                                ContaBancaria cDestino = x.buscarContaPublic(destino);

                                if (cDestino == null) {
                                    JOptionPane.showMessageDialog(null, "Conta destino não encontrada!");
                                    break;
                                }

                                valor = Double.parseDouble(JOptionPane.showInputDialog("Valor da transferência: "));
                                x.transferir(conta.getNumeroConta(), destino, valor);
                                break;

                            case 4: //Histórico de Transferências
                                conta.historicoTransferencias();
                                break;

                            case 5: //Informações da conta
                                conta.exibirInformacoes();
                                break;

                            case 6: //Sair da conta
                                break;

                            default: //Opção errada
                                JOptionPane.showMessageDialog(null, "Opção inválida!"); 
                                break;
                        }

                    } while (opcao2 != 6);

                    break;

                              case 3: //Sair
                    JOptionPane.showMessageDialog(null, "Encerrando programa...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }

        } while (opcao1 != 3);  
    }
}
