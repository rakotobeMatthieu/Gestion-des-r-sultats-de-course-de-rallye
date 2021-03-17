package ilainaRallye;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import java.lang.*;

public class Colonne
{
    String nom ;
    String type ;
    String taille ;

    public Colonne(String n , String t , String tail){

        nom=n ;
        type=t;
        taille=tail;
    }

    public void afficher(){
        System.out.println("Nom="+nom+" Type="+type+"\n");
    }

    public String getNm(){
        return nom;
    }
}