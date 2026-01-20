package poo;

import javax.swing.JOptionPane;

public class Main {
    private static Loja criarLoja() { //Método de criação da loja
        String nome = JOptionPane.showInputDialog("Nome da Loja:");
        String cnpj = JOptionPane.showInputDialog("CNPJ (14 dígitos):");
        String cep = JOptionPane.showInputDialog("CEP:");
        String estado = JOptionPane.showInputDialog("Estado:");
        String cidade = JOptionPane.showInputDialog("Cidade:");
        String bairro = JOptionPane.showInputDialog("Bairro:");
        String rua = JOptionPane.showInputDialog("Rua:");
        String numero = JOptionPane.showInputDialog("Número:");
        Loja.Endereco endereco = new Loja.Endereco(rua, numero, bairro, cidade, estado, cep);
        String telefone = JOptionPane.showInputDialog("Telefone:");
        String email = JOptionPane.showInputDialog("E-mail:");

        return new Loja(nome, cnpj, endereco, telefone, email);
    }

    public static void main(String[] args) {

        ArmazenamentoDados dados = new ArmazenamentoDados(); //Objeto da classe ArmazenamentoDados
        Loja loja = null; //Loja não existe ainda
        int opcao;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("[1] Banco\n[2] Loja\n[3] Sair")); //Menu inicial

            switch (opcao) {
                case 1 -> { //Banco
                    JOptionPane.showMessageDialog(null,"Sistema bancário em execução...");
                    SistemaBanco banco = new SistemaBanco();
                    banco.executar(dados);
                }

                case 2 -> { //Loja
                    if (loja == null) {
                        JOptionPane.showMessageDialog(null,"Nenhuma loja cadastrada. Vamos criar uma!");
                        loja = criarLoja();
                    }
                    loja.executar(dados);
                }
                case 3 -> JOptionPane.showMessageDialog(null,"Encerrando sistema..."); //Saída
                default -> JOptionPane.showMessageDialog(null,"Opção inválida!"); //Opção inválida
            }
        } while (opcao != 3);
    }
}
