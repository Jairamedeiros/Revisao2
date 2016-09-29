
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Sexo;

public class ClienteDAO {
    private DataBase db;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
    
    
    public ClienteDAO(){
        db = new DataBase();
    }
    
    public boolean insert(Cliente cliente){
         sql = "INSERT INTO cliente (nome, cpf, id_sexo) VALUES(?,?,?)";
             try {
                 ps = db.connection.prepareStatement(sql);
                 ps.setString(1, cliente.getNome());
                 ps.setString(2, cliente.getcpf());
                 ps.setInt(3, cliente.getSexo().getId());
                 if (ps.executeUpdate() == 1){
                    
                     return true;           
                 }
                 
             }catch (SQLException error) {
                 System.out.println("ERRO: " + error.toString());
             }
       
         return false;
        }
       
     
    public boolean delete(Cliente cliente) {
         sql = "DELETE FROM cliente WHERE id = ?";
           try{
               ps = db.connection.prepareStatement(sql);
               ps.setInt(1,cliente.getId());
               if (ps.executeUpdate()==1){
                   return true;
               }  
                                                 
           }catch (SQLException error){    
               System.out.println("ERRO: " + error.toString());
           }
               return false;
           }   
    
        
    public boolean update(Cliente cliente){
        sql = "update cliente set nome = ?, cpf = ?, id_sexo = ? WHERE id = ?";
             try {
                 ps = db.connection.prepareStatement(sql);
                 ps.setString(1, cliente.getNome());
                 ps.setString(2, cliente.getcpf());
                 ps.setInt(3, cliente.getSexo().getId());
                 if (ps.executeUpdate() == 1){
                    
                     return true;           
                 }
                 
             }catch (SQLException error) {
                 System.out.println("ERRO: " + error.toString());
             }
       
         return false;
        }
    
     
     
    public List<Cliente> selecTAll(){
        List<Cliente> clientes = new ArrayList();
        sql = "SELECT * FROM cliente JOIN sexo on id_sexo = cli_sex_id";
            try {
                ps = db.connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()){
                    Cliente cliente = new Cliente();
                    Sexo sexo = new Sexo();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    sexo.setId(rs.getInt("id"));
                    sexo.setNome(rs.getString("nome"));
                    sexo.setSigla(rs.getString("sigla"));
                    cliente.setSexo(sexo);
                    clientes.add(cliente);
                }
            
                return clientes;
                
            }catch (SQLException error) {
         
                return null;
             }
    }
            
            
    public Cliente selectById(int id){
        Cliente cliente = new Cliente();
        sql = "SELECT * FROM cliente WHERE id=?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                 
                    SexoDAO dao = new SexoDAO();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setSexo(dao.selectById(rs.getInt("cli_sex_id")));  
                }
                
                return cliente;
                
            
                
            } catch (SQLException error) {
                return null;
               
            }       
            
        }  
     
   public Cliente selectByFilter(String filter){
        List<Cliente> clientes = new ArrayList();
        String filtro = "%" + filter + "%";
        Cliente cliente = new Cliente();
        sql = "SELECT * FROM cliente WHERE nome LIKE ? OR cpf LIKE ?";
            try {
                ps = db.connection.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                rs = ps.executeQuery();
                while (rs.next()){
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    clientes.add(cliente);
                }
            
                return cliente;
                
            }catch (SQLException error) {
                System.out.println("ERRO: " + error.toString());
                
            }
                return null;
        }
   }

    
    
    
    

