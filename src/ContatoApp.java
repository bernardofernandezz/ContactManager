import java.util.Scanner;

public class ContatoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContatoDAO contatoDAO = new ContatoDAO();

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Listar contatos");
            System.out.println("3. Atualizar contato");
            System.out.println("4. Deletar contato");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    String ddi;
                    do {
                        System.out.print("DDI (ex: +55): ");
                        ddi = scanner.nextLine();
                    } while (!isDdiValido(ddi));  // Valida o DDI

                    String ddd;
                    do {
                        System.out.print("DDD (ex: 71): ");
                        ddd = scanner.nextLine();
                    } while (!isDddValido(ddd));  // Valida o DDD

                    String telefone;
                    do {
                        System.out.print("Telefone: ");
                        telefone = scanner.nextLine();
                    } while (!isTelefoneValido(telefone));  // Valida o telefone

                    String email;
                    do {
                        System.out.print("Email: ");
                        email = scanner.nextLine();
                        if (!isEmailValido(email)) {
                            System.out.println("Email inválido. Por favor, insira um email válido.");
                        }
                    } while (!isEmailValido(email));  // Valida o email

                    contatoDAO.adicionarContato(nome, telefone, email);
                    break;

                case 2:
                    contatoDAO.listarContatos();
                    break;

                case 3:
                    System.out.print("ID do contato a ser atualizado: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();

                    String novoDdi;
                    do {
                        System.out.print("Novo DDI (ex: +55): ");
                        novoDdi = scanner.nextLine();
                    } while (!isDdiValido(novoDdi));  // Valida o DDI

                    String novoDdd;
                    do {
                        System.out.print("Novo DDD (ex: 71): ");
                        novoDdd = scanner.nextLine();
                    } while (!isDddValido(novoDdd));  // Valida o DDD

                    String novoTelefone;
                    do {
                        System.out.print("Novo Telefone: ");
                        novoTelefone = scanner.nextLine();
                    } while (!isTelefoneValido(novoTelefone));  // Valida o telefone

                    String novoEmail;
                    do {
                        System.out.print("Novo Email: ");
                        novoEmail = scanner.nextLine();
                        if (!isEmailValido(novoEmail)) {
                            System.out.println("Email inválido. Por favor, insira um email válido.");
                        }
                    } while (!isEmailValido(novoEmail));  // Valida o email

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
        }
    }

    // Método para validar o DDI
    private static boolean isDdiValido(String ddi) {
        return ddi.matches("\\+\\d{1,3}");  // Valida DDI (ex: +55, +1)
    }

    // Método para validar o DDD
    private static boolean isDddValido(String ddd) {
        return ddd.matches("\\d{2}");  // Valida DDD (ex: 11, 71)
    }

    // Método para validar o número de telefone
    private static boolean isTelefoneValido(String telefone) {
        return telefone.matches("\\d{8,9}");  // Verifica se o telefone tem 8 ou 9 dígitos
    }

    // Método para validar o email
    private static boolean isEmailValido(String email) {
        // Expressão regular para validação de email
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
