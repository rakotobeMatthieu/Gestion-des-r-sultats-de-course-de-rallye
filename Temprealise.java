package elementsRallye ;

public class Temprealise{

    String id ;
    String idconc ;
    String idcompetape ;
    int temps ;
    String remarque ;

    public Temprealise(String i , String b , String c , int t , String r){
        id=i;
        idconc=b ;
        idcompetape=c ;
        temps = t ;
        remarque=r ;
    }

    public Temprealise(){

    }

    public String getID(){
        return id ;
    }

    public String getIDCONC(){
        return idconc ;
    }

    public String getIDCOMPETAPE(){
        return idcompetape ;
    }

    public int getTEMPS(){
        return temps ;
    }

    public String getREMARQUE(){
        return remarque ;
    }


    public void setID(String nov){
        id=nov ;
    }

    public void setIDCONC(String nov){
        idconc=nov ;
    }

    public void setIDCOMPETAPE(String nov){
        idcompetape=nov ;
    }

    public void setTEMPS(int nov){
        temps=nov ;
    }

    public void setREMARQUE(String nov){
        remarque=nov ;
    }
}