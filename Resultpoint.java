package elementsRallye;

public class Resultpoint{
    String id ;
    String idcomp ;
    String idconc ;
    int point;

    public Resultpoint(String i ,String b , String c , int p ){
        id=i ;
        idcomp=b ; 
        idconc=c ;
        point=p ;
    }

    public Resultpoint(){

    }

    public String getID(){
        return id ;
    }

    public String getIDCOMP(){
        return idcomp ;
    }

    public String getIDCONC(){
        return idconc ;
    }

    public int getPOINT(){
        return point ;
    }



    public void setID(String nov){
        id=nov ;
    }

    public void setIDCOMP(String nov){
        idcomp=nov ;
    }

    public void setIDCONC(String nov){
        idconc=nov ;
    }

    public void setID(int nov){
        point=nov ;
    }
}