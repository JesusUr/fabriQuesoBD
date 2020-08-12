package logic;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionSQL extends Thread {
	public static Connection conexion = null;
    private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url="jdbc:sqlserver://localhost:49817;databaseName=DBfabriQueso;integratedSecurity=true";
    private static Connection connect = null;
    
    public static Connection getConnectionSQL(){      
        try {
            Class.forName(driver);
        	connect = DriverManager.getConnection(url);
        	if (connect != null) { 
        		System.out.println("Conectado a la base de datos.");
			}
        } catch (Exception e) {
           System.out.println("Hubo un error en la conexión a la base de datos.");
        }
        return connect;
    }  
}