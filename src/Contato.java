import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contato [Nome=" + nome + ", Telefone=" + telefone + ", Email=" + email + "]";
    }



    public String getTelefoneFormatado() {
        // Exemplo: "+55 11 91234-5678"
        String ddi = telefone.substring(0, 3);            // Código do país
        String ddd = telefone.substring(2, 4);            // Código de área
        String parte1 = telefone.substring(4, 9);         // Primeiros 5 dígitos
        String parte2 = telefone.substring(9);            // Últimos 4 dígitos

        return String.format("+%s %s %s-%s", ddi, ddd, parte1, parte2);
    }


    public String getEmailFormatado() {
        // Exemplo: johndoe@gmail.com
        String emailFormatado = email.replace("@", "|");
        String parte1 = emailFormatado.substring(0, emailFormatado.indexOf("|"));
        String parte2 = emailFormatado.substring(emailFormatado.indexOf("|") + 1);

        return String.format("%s||%s", parte1, parte2);

    }
    // Method to validate international phone numbers
    public static boolean isTelefoneValidoInternacional(String telefone) {
        // Check if the phone number has between 10 and 13 digits
        // and if it starts with a '+' followed by a country code
        return telefone.matches("^\\+\\d{1,3}\\d{10,13}$");
    }

    // Method to format international phone numbers
    public String getTelefoneFormatadoInternacional() {
        // Example: "+5511912345678" -> "+55 11 91234-5678"
        if (isTelefoneValidoInternacional(telefone)) {
            String ddi = telefone.substring(0, 3);            // Country code
            String ddd = telefone.substring(3, 5);            // Area code
            String parte1 = telefone.substring(5, 10);         // First 5 digits
            String parte2 = telefone.substring(10);            // Last 4 digits

            return String.format("%s %s %s-%s", ddi, ddd, parte1, parte2);
        } else {
            return "Telefone inválido";
        }
    }


//    public boolean isValidEmail() {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//        Pattern pat = Pattern.compile(emailRegex);
//        if (email == null)
//            return false;
//        return pat.matcher(email).matches();
//    }
//
//    public boolean isValidTelefone() {
//        String telefoneRegex = "^\\+\\d{2} \\d{2} \\d{5}-\\d{4}$";
//        Pattern pat = Pattern.compile(telefoneRegex);
//        if (telefone == null)
//            return false;
//        return pat.matcher(telefone).matches();
//    }

}