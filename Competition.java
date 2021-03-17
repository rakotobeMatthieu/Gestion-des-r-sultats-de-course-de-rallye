package elementsRallye;

public class Competition{

    String id ;
    String nom ;
    String date ;
    int coef ;

    public Competition(String i , String n , String d , int c){
        id=i ;
        nom=n ;
        date=d;
        coef=c;
    }

    public Competition(){

    }

    public String getID(){
        return id ;
    }

    public String getNOM(){
        return nom ;
    }

    public String getDATECOMP(){
        return date ;
    }

    public int getCOEF(){
        return coef ;
    }


    public void setID(String nov){
        id=nov ;
    }

    public void setNOM(String nov){
        nom=nov ;
    }

    public void setDATECOMP(String nov){
        date=nov ;
    }

    public void setCOEF(int nov){
        coef=nov ;
    }
}