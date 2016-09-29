
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Cliente;
import model.Produto;
import model.Sexo;

public class ProdutoDAO {
    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    
    public ProdutoDAO(){
        db = new DataBase();
    }
    
    public boolean insert(Produto produto){
         sql = "INSERT INTO produto (nome, valor, id_categoria) VALUES(?,?,?)";
             try {
                 ps = db.connection.prepareStatement(sql);
                 ps.setString(1, produto.getNome());
                 ps.setFloat(2, produto.getValor());
                 ps.setInt(3, produto.getCategoria().getId());
                 if (ps.executeUpdate() == 1){
                    
                     return true;           
                 }
                 
             }catch (SQLException error) {
                 System.out.println("ERRO: " + error.toString());
             }
       
         return false;
        }
       
     
    public boolean delete(Produto produto) {
         sql = "DELETE FROM produto WHERE id = ?";
           try{
               ps = db.connection.prepareStatement(sql);
               ps.setInt(1,produto.getId());
               if (ps.executeUpdate()==1){
                   return true;
               }  
                                                 
           }catch (SQLException error){    
               System.out.println("ERRO: " + error.toString());
           }
               return false;
           }   
    
        
    public boolean update(Produto produto){
        sql = "update cliente set nome = ?, valor = ? , id_categoria = ? WHERE id = ?";
             try {
                 ps = db.connection.prepareStatement(sql);
                 ps.setString(1, produto.getNome());
                 ps.setFloat(2, produto.getValor());                 
                 ps.setInt(3, produto.getCategoria().getId());
                 if (ps.executeUpdate() == 1){
                    
                     return true;           
                 }
                 
             }catch (SQLException error) {
                 System.out.println("ERRO: " + error.toString());
             }
       
         return false;
        }
    
     
     
    public List<Produto> selecTAll(){
        List<Produto> produtos = new ArrayList();
        sql = "SELECT * FROM produto JOIN categoria on id_categoria = prod_cat_id";
            try {
                ps = db.connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()){
                    Produto produto = new Produto();
                    Categoria categoria = new Categoria();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getFloat("valor"));
                    categoria.setId(rs.getInt("id"));
                    categoria.setNome(rs.getString("nome"));
                    produto.setCategoria(categoria);
                    produtos.add(produto);
                }
            
                return produtos;
                
            }catch (SQLException error) {
         
                return null;
             }
    }
            
            
    public Produto selectById(int id){
       Produto produto = new Produto();
        sql = "SELECT * FROM produto WHERE id=?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                 
                    CategoriaDAO dao = new CategoriaDAO();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getFloat("valor"));
                    produto.setCategoria(dao.selectById(rs.getInt("prod_cat_id")));  
                }
                
                return produto;
                
            
                
            } catch (SQLException error) {
                return null;
               
            }       
            
        }  
     
   public Produto selectByFilter(String filter){
        List<Produto> produtos = new ArrayList();
        String filtro = "%" + filter + "%";
        Produto produto = new Produto();
        sql = "SELECT * FROM produto WHERE nome LIKE ? OR valor LIKE ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while (rs.next()){
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getFloat("valor"));
                    produtos.add(produto);
                }
            
                return produto;
                
            }catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
                
            }
                return null;
        }
   }

