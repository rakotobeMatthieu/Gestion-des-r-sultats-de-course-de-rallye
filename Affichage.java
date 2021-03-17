package affRallye;
import ilainaRallye.*;
import elementsRallye.* ;
import connectionRallye.* ;
import java.util.*;
import java.sql.*;
import java.lang.*;
import java.util.Scanner;


public class Affichage  {

  public static void main(String[] args) throws Exception {      

    Connect connex=new Connect();
    Object[] objet= new Object[100];
    Statement t;
    connex.connection();
/*Temprealise tp = new Temprealise("TPR070", "CCR001" , "CPE005" ,200," " );
Dateheure dt =connex.getFonction().transform(4059);
		System.out.println("Heure :"+dt.getHeure()+" Minute :"+dt.getMinute()+" Seconde : "+dt.getSeconde());*/

connex.getFonction().voirrang("CCR002","CPT001");

  }

}
