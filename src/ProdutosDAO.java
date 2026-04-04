import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        //Instrução SQL
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";

        try {
            //Controlando e executando a Instrução SQL
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());//Alocando dado na primeira interrogação que equivale ao nome
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            //Enviando para o MySQL
            prep.executeUpdate();

            //Mensagem de confirmação
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (SQLException ex) { //Coleta os possíveis erros e exibe através do getMessage()
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
        } finally {

        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        conn = new conectaDAO().connectDB(); 

        String sql = "SELECT * FROM produtos";

        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        try {
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));     
                p.setStatus(rs.getString("status")); 

                lista.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "ERRO. Não foi possível consultar os dados: " + e.getMessage());
        }

        return lista;
    }

}
