import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    // Método para validar números de telefone
    public static boolean validarTelefone(String telefone) {
        // Regex para telefone com código de país e DDD local
        String regexTelefone = "^\\+\\d{1,3}\\s\\d{2}\\s\\d{8,9}$";
        Pattern pattern = Pattern.compile(regexTelefone);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }

    // Método para validar e-mails
    public static boolean validarEmail(String email) {
        // Regex para validar e-mails
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,6}$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
