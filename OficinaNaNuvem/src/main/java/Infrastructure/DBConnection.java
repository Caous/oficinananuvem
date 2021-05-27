package Infrastructure;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBConnection {

    public Statement st;
    public ResultSet rs;

    private static final String CAMINHO = "jdbc:mysql://mysql.oficinananuvem.com.br:3306/oficinananuvem?zeroDateTimeBehavior=convertToNull&useSSL=false";//Caminho de produ��o

    //private static final String CAMINHO = "jdbc:mysql://localhost:3306/bdprojeto3?zeroDateTimeBehavior=convertToNull";
    //private static final String CAMINHO = "jdbc:mysql://localhost:3306/oficinananuvem?useTimezone=true&serverTimezone=UTC";
    
     //userbd local
    //private static final String USER = "root";
    //private static final String SENHA = "";
   
    //userbd produção
    private static final String USER = "oficinananuvem";
    private static final String SENHA = "bdOf3c3n4";

    public static java.sql.Connection obterConexao()
            throws ClassNotFoundException, SQLException {
// 1) Declarar o driver JDBC de acordo com o Banco de dados usado
        Class.forName("com.mysql.jdbc.Driver");
// 2) Abrir a conexão
        java.sql.Connection conn = DriverManager.getConnection(CAMINHO, USER, SENHA); // Usu�rio de conex�o no BD"SENHA"); // Senha
        return conn;
    }
}
