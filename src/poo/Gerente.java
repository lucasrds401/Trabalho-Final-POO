package poo;
import javax.swing.JOptionPane;

public class Gerente {
    private String nome, senha;
    private ArmazenamentoDados banco;
    
    public Gerente(String nome, String senha, ArmazenamentoDados banco){
        this.nome = nome;
        this.senha = senha;
        this.banco = banco;
    }

    public boolean autenticar(){ //Método de autenticação
        String entrada = JOptionPane.showInputDialog(null,"Digite a senha do Gerente: ", "GERENTE", JOptionPane.QUESTION_MESSAGE);
    
        if(entrada == null){ //Caso o user clique em cancelar
            return false;
        }
        return entrada.equals(this.senha); //Compara a senha
    }    

    public void criarConta(){ //Método de criar conta
        String titular = JOptionPane.showInputDialog("Digite o nome do Titular: ");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do Titular: ");
        String email = JOptionPane.showInputDialog("digite o email do Titular: ");
        String endereco = JOptionPane.showInputDialog("Digite o endereço do Titular: ");

    if(titular == null || cpf == null || email == null || endereco ==  null){ //Validação se algum campo for cancelado
        JOptionPane.showMessageDialog(null, "Operação cancelada!");
        return;
    }

    String numGerado = banco.criarConta(titular, cpf, email, endereco); //Criação da conta
    JOptionPane.showMessageDialog(null, "Numero da Conta: " + numGerado, "CONTA CRIADA", JOptionPane.INFORMATION_MESSAGE);
    } 
    
    public void listarContas(){
        banco.listarContas();
    }

    public String getnome() {
        return nome;
    }

}