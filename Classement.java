package ilainaRallye;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import java.lang.*;

public class Classement
{
    String prenom ;
    int temp ;

    public Classement(String n , int t){

        prenom=n ;
        temp=t;

    }

    public void afficher(){
        System.out.println("Nom="+prenom+" Type="+temp+"\n");
    }

}