import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDeContatos {

    private final List<Contato> contatos;

    public GerenciadorDeContatos() {
        this.contatos = new ArrayList<>();
    }

    // Método para adicionar contato
    public void adicionarContato(Contato contato) {
        contatos.add(contato);
        System.out.println("Contato adicionado com sucesso!");
    }

    // Método para remover contato
    public void removerContato(String nome) {
        Contato contatoParaRemover = null;
        for (Contato contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                contatoParaRemover = contato;
                break;
            }
        }
        if (contatoParaRemover != null) {
            contatos.remove(contatoParaRemover);
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    // Método para listar contatos
    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
        } else {
            for (Contato contato : contatos) {
                System.out.println(contato);
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeContatos gerenciador = new GerenciadorDeContatos();
        int opcao;

        do {
            System.out.println("\nGerenciador de Contatos");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Remover contato");
            System.out.println("3. Listar contatos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Contato contato = new Contato(nome, telefone, email);

                    gerenciador.adicionarContato(contato);
                    break;

                case 2:
                    System.out.print("Nome do contato a remover: ");
                    String nomeParaRemover = scanner.nextLine();

                    gerenciador.removerContato(nomeParaRemover);
                    break;

                case 3:
                    gerenciador.listarContatos();
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}