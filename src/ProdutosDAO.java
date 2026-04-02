/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
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
        }finally{
            
        }    
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

