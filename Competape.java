package elementsRallye;

public class Competape{

    String id ;
    String idcomp;
    int distance ;

    public Competape(String i , String idc , int d){
        id=i ;
        idcomp=idc;
        distance=d;
    }

    public Competape(){

    }

     public String getID(){
        return id ;
    }

    public String getIDCOMP(){
        return idcomp ;
    }

    public int getDISTANCE(){
        return distance ;
    }   

    public void setID(String nov){
        id=nov ;
    }

    public void setICOMP(String nov){
        idcomp=nov ;
    }

    public void setDISTANCE(int nov){
        distance=nov ;
    }
}