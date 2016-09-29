
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    public Connection connection;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE = "revisao_db";
    private final String URL = "jdbc:mysql://localhost:3306/"+DATABASE;
    private final String USER = "root";
    private final String PASSWORD = "328932";
    
    
public boolean connect(){
        try{
           Class.forName(DRIVER);
           connection = DriverManager.getConnection(URL,USER,PASSWORD);
           System.out.println("CONNECT");
            return true;
                       
        }catch(SQLException | ClassNotFoundException error){
            System.out.println("DRIVER NOT FOUND");
            System.out.println("ERRO: " + error.toString());
        }
        return false;
       
    }
    
    public boolean disconnection(){
        try{
            connection.close();
            System.out.println("DISCONNECT");
            return true;
                       
            
        }catch(SQLException error){
            System.out.println("ERRO: " + error.toString());
      
        }
        return false;
    }
    
}
