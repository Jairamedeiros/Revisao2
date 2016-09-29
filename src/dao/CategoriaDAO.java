
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

public class CategoriaDAO {
    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    
    public CategoriaDAO(){
        db = new DataBase();
    }
    
     public boolean insert(Categoria categoria) throws SQLException {
         if (db.connect()){
             sql = "INSERT INTO categoria (nome) VALUES(?)";
             try {
                 ps = db.connection.prepareStatement(sql);
                 ps.setString(1, categoria.getNome());
                 if (ps.executeUpdate() == 1){
                     ps.close();
                     db.disconnection();
                     return true;
                             
                 }
              
             }catch (SQLException error) {
                 System.out.println("ERRO: " + error.toString());
             }
         }
         db.disconnection();
         return false;
     }
    
     public boolean delete(Categoria categoria){
        if (db.connect()){
            sql = "DELETE FROM categoria WHERE id = ?";
                try {
                    ps = db.connection.prepareStatement(sql);
                    ps.setInt(1, categoria.getId());
                    if (ps.executeUpdate() == 1){
                        ps.close();
                        db.disconnection();
                        return true;
                    }
                } catch (SQLException error) {
                    System.out.println("ERRO: " + error.toString());
                }
            }
            db.disconnection();
            return false;     
        } 
            
     public boolean update(Categoria categoria){
         if (db.connect()){
            sql = "UPDATE categoria SET snome = ? WHERE id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, categoria.getNome());
                ps.setInt(2, categoria.getId());
                 if (ps.executeUpdate() == 1){
                     ps.close();
                     db.disconnection();
                     return true;
                    }
            }catch(SQLException error) {
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.disconnection();
        return false;
     }
     
     public List<Categoria> selecTAll(){
         if (db.connect()){
             List<Categoria> categorias = new ArrayList();
             sql = "SELECT * FROM categoria";
                 try {
                     ps = db.connection.prepareStatement(sql);
                     rs = ps.executeQuery();
                     while (rs.next()){
                         Categoria categoria = new Categoria();
                         categoria.setId(rs.getInt(1));
                         categoria.setNome(rs.getString(2));
                         categoria.add(categoria); 
                     }
                     rs.close();
                     ps.close();
                     db.disconnection();
                     return categorias;
                     
                 } catch (SQLException error) {
                     System.out.println("ERRO: " + error.toString());
                    } 
                 }
                 db.disconnection();
                 return null;         
             }
    
    public Categoria selectById(int id){
        if (db.connect()){
            Categoria categoria = new Categoria();
            sql = "SELECT * FROM tb_contatos WHERE con_id =?";
            try{
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                    categoria.setId(rs.getInt(1));
                    categoria.setNome(rs.getString(2));
                    rs.close();
                    ps.close();
                    db.disconnection();
                    return categoria;            
                }  
            }catch(SQLException error){
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.disconnection();
        return null;   
        
    }
      
     
    public Categoria selectByFilter(String filter){
         if (db.connect()){
            List<Categoria> categorias = new ArrayList();
            String filtro = "%" + filter + "%";
            sql = "SELECT * FROM tb_sexos WHERE sex_nome LIKE ? OR sex_sigla LIKE ?";
            try{
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while(rs.next()){
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt(1));
                    categoria.setNome(rs.getString(2));
                    categorias.add(categoria);             
                }
                rs.close();
                ps.close();
                db.disconnection();
                return (Categoria) categorias; 
                             
                
            }catch(SQLException error){
                System.out.println("ERRO: " + error.toString());
            }
          }
        db.disconnection();
        return null;     
        }
        
    }
 

