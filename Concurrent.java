package elementsRallye ;

public class Concurrent{
    String id;
    String voiture;
    String prenom ;

    public Concurrent(String i , String voi , String pre){
        id=i;
        voiture=voi;
        prenom=pre;
    }

    public Concurrent(){

    }

    public String getID(){
        return id ;
    }

    public String getVOITURE(){
        return voiture ;
    }

    public String getPRENOM(){
        return prenom;
    }



    public void setID(String nov){
        id=nov ;
    }

    public void setVOITURE(String nov){
        voiture=nov ;
    }

    public void setPRENOM(String nov){
        prenom=nov ;
    }
}