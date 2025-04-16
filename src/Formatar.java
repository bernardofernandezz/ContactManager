public class Formatar {

    public static String formatarTelefone(String telefone) {
        // Remover espaços e formatações existentes
        telefone = telefone.replaceAll("[^\\d]", "");
        if (telefone.length() == 13) {
            return String.format("+%s %s %s-%s",
                    telefone.substring(0, 2),
                    telefone.substring(2, 4),
                    telefone.substring(4, 9),
                    telefone.substring(9));
        } else if (telefone.length() == 14) {
            return String.format("+%s %s %s-%s",
                    telefone.substring(0, 2),
                    telefone.substring(2, 4),
                    telefone.substring(4, 10),
                    telefone.substring(10));
        } else {
            return telefone;  // Retorna sem formatação se o tamanho for inesperado
        }
    }
}
