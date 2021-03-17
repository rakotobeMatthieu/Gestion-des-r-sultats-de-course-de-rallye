package connectionRallye;
import ilainaRallye.*;
import java.sql.*;
import java.util.Scanner;


public class Connect  {

  Function f;

  public Function getFonction()
  {
    return f;
  }

  

 public void connection(){     

    try {

      Class.forName("oracle.jdbc.OracleDriver");

      System.out.println("Driver O.K.");


//connection
      Connection cnx= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","rallye", "rallye");

      System.out.println("Connexion effective !");      

      Statement stmt=cnx.createStatement();  
      f = new Function(stmt);
      
  

    } catch (Exception e) {

      e.printStackTrace();

    }      

  }

}
