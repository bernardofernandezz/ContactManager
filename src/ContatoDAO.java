import java.sql.*;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;


public class ContatoDAO {

    // Método para adicionar um contato
    public void adicionarContato(String nome, String telefone, String email) {
        // Formatar o telefone e extrair DDI, DDD e número
        String[] telefoneFormatado = formatarTelefone(telefone);
        String ddi = telefoneFormatado[0];
        String ddd = telefoneFormatado[1];
        String numero = telefoneFormatado[2];

        String sql = "INSERT INTO contatos (nome, DDI, DDD, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = ConexaoDB.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            if (conexao != null) {
                pstmt.setString(1, nome);
                pstmt.setString(2, ddi);
                pstmt.setString(3, ddd);
                pstmt.setString(4, numero);
                pstmt.setString(5, email);
                pstmt.executeUpdate();
                System.out.println("Contato adicionado com sucesso!");
            } else {
                System.out.println("Falha na conexão com o banco de dados.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar contato: " + e.getMessage());
            System.out.println("Código de erro SQL: " + e.getErrorCode());
            System.out.println("SQLState: " + e.getSQLState());
        }
    }

    // Método para listar todos os contatos
    public List<Contato> listarContatos() {
        String sql = "SELECT * FROM contatos";

        try (Connection conexao = ConexaoDB.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("DDI: " + rs.getString("ddi"));
                System.out.println("DDD: " + rs.getString("ddd"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar contatos: " + e.getMessage());
        }
        return null;
    }

    // Método para atualizar um contato
    public void atualizarContato(int id, String novoNome, String telefone, String novoEmail) {
        // Formatar o telefone e extrair DDI, DDD e número
        String[] telefoneFormatado = formatarTelefone(telefone);
        String ddi = telefoneFormatado[0];
        String ddd = telefoneFormatado[1];
        String numero = telefoneFormatado[2];

        String sql = "UPDATE contatos SET nome = ?, ddi = ?, ddd = ?, telefone = ?, email = ? WHERE id = ?";

        try (Connection conexao = ConexaoDB.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, novoNome);
            pstmt.setString(2, ddi);
            pstmt.setString(3, ddd);
            pstmt.setString(4, numero);
            pstmt.setString(5, novoEmail);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("Contato atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar contato: " + e.getMessage());
        }
    }

    // Método para deletar um contato
    public void deletarContato(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";

        try (Connection conexao = ConexaoDB.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contato deletado com sucesso!");
            } else {
                System.out.println("Nenhum contato encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar contato: " + e.getMessage());
        }
    }

    // Método para formatar o número de telefone e extrair DDI, DDD e número
    private String[] formatarTelefone(String telefone) {
        // Exemplo de entrada: "5511912345678" -> {"55", "11", "91234-5678"}
        String ddi = telefone.substring(0, 2);   // Código do país
        String ddd = telefone.substring(2, 4);   // Código de área
        String numero = telefone.substring(4, 9) + "-" + telefone.substring(9);  // Número formatado

        return new String[] {ddi, ddd, numero};
    }
}
