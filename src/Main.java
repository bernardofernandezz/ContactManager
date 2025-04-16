import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---- Agenda de Contatos ----");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Listar Contatos");
            System.out.println("3. Atualizar Contato");
            System.out.println("4. Deletar Contato");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha



            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    String telefone;
                    do {
                        System.out.print("Telefone (10 a 13 dígitos): ");
                        telefone = scanner.nextLine();
                        if (isTelefoneValido(telefone)) {
                            System.out.println("Número de telefone inválido. Digite 10 a 13 dígitos para números internacionais.");
                        }
                    } while (isTelefoneValido(telefone));

                    telefone = formateTelefone(telefone);

                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    contatoDAO.adicionarContato(nome, telefone, email);
                    break;

                case 2:
                    contatoDAO.listarContatos();
                    List<Contato> contatos = contatoDAO.listarContatos();
                    for (Contato contato : contatos) {
                        System.out.println("ID: " + contato.getId());
                        System.out.println("Nome: " + contato.getNome());
                        System.out.println("Telefone: " + contato.getTelefoneFormatado());
                        System.out.println("Email: " + contato.getEmailFormatado());
                        System.out.println();
                    }
                    break;


                case 3:
                    System.out.print("ID do contato a ser atualizado: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();

                    String novoTelefone;
                    do {
                        System.out.print("Novo Telefone (Ex. +5511912345678): ");
                        novoTelefone = scanner.nextLine();
                        if (isTelefoneValido(novoTelefone)) {
                            System.out.println("Número de telefone inválido. Digite 10 a 13 dígitos para números internacionais.");
                        }
                    } while (isTelefoneValido(novoTelefone));

                    novoTelefone = formateTelefone(novoTelefone);

                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();
                    contatoDAO.atualizarContato(idAtualizar, novoNome, novoTelefone, novoEmail);
                    break;

                case 4:
                    System.out.print("ID do contato a ser deletado: ");
                    int idDeletar = scanner.nextInt();
                    contatoDAO.deletarContato(idDeletar);
                    break;

                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
                /* Este Switch determina o que acontecerá após o usuario digitar um numero */
        }
    }


    // Método para validar o número de telefone
    @org.jetbrains.annotations.Contract(pure = true)
    public static boolean isTelefoneValido(@NotNull String telefone) {
        // Verifica se o telefone tem entre 10 e 13 dígitos
        return !telefone.matches("\\d{10,14}");
    }


    // Método para formatar o número de telefone
    private static @NotNull String formateTelefone(String telefone) {
        // Exemplo: "5511912345678" -> "+55 11 91234-5678"
        String ddi = telefone.substring(0, 3);            // Código do país
        String ddd = telefone.substring(2, 4);            // Código de área
        String parte1 = telefone.substring(4, 9);         // Primeiros 5 dígitos
        String parte2 = telefone.substring(9);            // Últimos 4 dígitos

        return String.format("+%s %s %s-%s", ddi, ddd, parte1, parte2);
    }
}
