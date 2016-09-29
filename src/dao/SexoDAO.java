
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Sexo;

public class SexoDAO {
    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    
    public SexoDAO(){
        db = new DataBase();
    }
    
     public boolean insert(Sexo sexo) throws SQLException{
         if (db.connect()){
             sql = "INSERT INTO sexo (nome, sigla) VALUES(?)";
             try {
                 ps = db.connection.prepareStatement(sql);
                 ps.setString(1, sexo.getNome());
                 ps.setString(2, sexo.getSigla());
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
    
     public boolean delete(Sexo sexo){
        if (db.connect()){
            sql = "DELETE FROM sexo WHERE id = ?";
                try {
                    ps = db.connection.prepareStatement(sql);
                    ps.setInt(1, sexo.getId());
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
            
     public boolean update(Sexo sexo){
         if (db.connect()){
            sql = "UPDATE sexo SET nome = ?, sigla = ? WHERE id = ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, sexo.getNome());
                ps.setString(2, sexo.getSigla());
                ps.setInt(3, sexo.getId());
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
     
     public List<Sexo> selecTAll(){
         if (db.connect()){
             List<Sexo> sexos = new ArrayList();
             sql = "SELECT * FROM sexo";
                 try {
                     ps = db.connection.prepareStatement(sql);
                     rs = ps.executeQuery();
                     while (rs.next()){
                         Sexo sexo = new Sexo();
                         sexo.setId(rs.getInt(1));
                         sexo.setNome(rs.getString(2));
                         sexo.setSigla(rs.getString(3));
                         sexos.add(sexo); 
                     }
                     rs.close();
                     ps.close();
                     db.disconnection();
                     return sexos;
                     
                 } catch (SQLException error) {
                     System.out.println("ERRO: " + error.toString());
                    } 
                 }
                 db.disconnection();
                 return null;         
             }
    
    public Sexo selectById(int id){
        if (db.connect()){
            Sexo sexo = new Sexo();
            sql = "SELECT * FROM sexo WHERE id =?";
            try{
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                    sexo.setId(rs.getInt(1));
                    sexo.setNome(rs.getString(2));
                    sexo.setSigla(rs.getString(3));
                    rs.close();
                    ps.close();
                    db.disconnection();
                    return sexo;            
                }  
            }catch(SQLException error){
                System.out.println("ERRO: " + error.toString());
            }
        }
        db.disconnection();
        return null;   
        
    }
      
     
    public Sexo selectByFilter(String filter){
         if (db.connect()){
            List<Sexo> sexos = new ArrayList();
            String filtro = "%" + filter + "%";
            sql = "SELECT * FROM sexo WHERE nome LIKE ? OR sigla LIKE ?";
            try{
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while(rs.next()){
                    Sexo sexo = new Sexo();
                    sexo.setId(rs.getInt(1));
                    sexo.setNome(rs.getString(2));
                    sexo.setSigla(rs.getString(3));
                    sexos.add(sexo);             
                }
                rs.close();
                ps.close();
                db.disconnection();
                return (Sexo) sexos;
                             
                
            }catch(SQLException error){
                System.out.println("ERRO: " + error.toString());
            }
          }
        db.disconnection();
        return null;     
        }
        
    }
        
    
    
    
    
    
    

