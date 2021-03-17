package ilainaRallye;
import connectionRallye.*;
import java.sql.*;
import java.util.*;
import java.util.Scanner;
import java.util.Vector;
import java.lang.reflect.*;
public class Function 
{
	Statement stat;
    int i=0;
	
	public Function(Statement s)throws Exception
	{
		this.stat = s;
	}
	
    public void valider() throws Exception
	{
		this.stat.executeQuery("Commit");
	}

 	public void afficher(String table)throws Exception{  
		ResultSet res= this.stat.executeQuery("SELECT * FROM "+table);
        ResultSetMetaData result = res.getMetaData();
		while(res.next()){
			for(int i=1; i<result.getColumnCount()-1; i++)
			{
				for(int j=1 ; j<result.getColumnCount()+1;j++)
				{
				System.out.println(result.getColumnName(j)+": "+res.getString(j)+"\n");
				}

			}
			
		}

    }
	public void afficher(ResultSet res)throws Exception{

        ResultSetMetaData result = res.getMetaData();
		while(res.next()){
			for(int i=1; i<=result.getColumnCount(); i++)
			{
				System.out.println(result.getColumnName(i)+": "+res.getObject(i).toString()+"\n");
			}
		}

    }
	
	public Colonne[] getStructure(String nt)throws Exception{		//OBTENIR NOM ET TYPE DE COLONNE

	    ResultSet res= this.stat.executeQuery("SELECT * FROM "+nt);
		ResultSetMetaData val = res.getMetaData();
		int nbCol=val.getColumnCount();
		Colonne[] tab=new Colonne[nbCol];
		for(int j=0 ; j<nbCol ; j++)
		{
			tab[j]=new Colonne(val.getColumnName(j+1),val.getColumnTypeName(j+1),"0");
		}
		return tab ;
	}

	public String[] getNomcol(Object aup)throws Exception{ 		//OBTENIR NOM DE COLONNE

		String naup = aup.getClass().getName();
		String[] vali=new String[2];
		vali=naup.split("\\.");
		String part=vali[1];
		Colonne[] colonne ;
		colonne=getStructure(part);
		int longe=colonne.length;
		String[] val=new String[longe];
		for(int i=0 ; i<longe ; i++)
		{
			val[i]=colonne[i].getNm();
			System.out.println(val[i]);
		}
		return val;
	}

	public int update(String table , String id , Object aup)throws Exception{		//MISE A JOUR 

		Colonne[] colonnes ;
		colonnes=getStructure(table);
		String[] str =getNomcol(aup);
		int longa = colonnes.length;

		Object obj[]=new Object[longa];
		for(int i=0 ; i<longa ; i++){
			obj[i]=aup.getClass().getMethod("get"+str[i]).invoke(aup);
		}

		ResultSet[] resp=new ResultSet[longa];
		int compte=0 ;
		for(int j=1 ; j<longa ; j++){
			System.out.println("UPDATE "+table+" SET "+str[j]+"='"+obj[j]+"' WHERE "+str[0]+"="+id);
			resp[j]=this.stat.executeQuery("UPDATE "+table+" SET "+str[j]+"='"+obj[j]+"' WHERE "+str[0]+"='"+id+"'");
			valider();
		}
		//afficher(table);
		return 5;
	} 

	public int create(Object ac)throws Exception{		// CREATION D UN NOUVEL ELEMENT
		String nom = ac.getClass().getName();
		
		String[] str=getNomcol(ac);
		String[] vali=new String[2];
		vali=nom.split("\\.");
		String part=vali[1];
		Object obj;
		obj=ac.getClass().getMethod("get"+str[0]).invoke(ac);
		ResultSet val;
		val=this.stat.executeQuery("INSERT INTO "+part+"("+str[0]+") values('"+obj+"')");
		valider();

		String id=obj.toString();
		int valiny= update(part , id , ac);
		valider();
		return valiny;
	}


	public int compte_non_null(Object comp)throws Exception{		//COMPTER NOMBRE D ELEMENT NON NULL

		String cmp=comp.getClass().getName();
		String[] vali=new String[2];
		vali=cmp.split("\\.");
		String part=vali[1];
		ResultSet res = this.stat.executeQuery("Select*from "+part);
		ResultSetMetaData result=res.getMetaData();
		int valiny=7 ;
		while(res.next()){
			for(int i=1; i<=result.getColumnCount(); i++)
			{
				if(res.getString(i)=="Null")
				{
					valiny++;
				}
			}
		}
		return valiny;
	}


	public int getnbconc()throws Exception{		//Compter nombre de concurrent

		ResultSet res= this.stat.executeQuery("select count(id) as compte from concurrent");
		ResultSetMetaData val = res.getMetaData();
		int rep=0;
		while(res.next()){
			rep=res.getInt("compte");
		}
		res.close();
		return rep ;
	}

	public int getnbconccateg(String nc)throws Exception{
		ResultSet res;
		res=this.stat.executeQuery("select prenom , sum(temps) as somme from temprealise join CONCURRENT ON TEMPREALISE.idconc=CONCURRENT.id join COMPETCATEG ON CONCURRENT.id=COMPETCATEG.idcon where COMPETCATEG.idcat='"+nc+"' group by prenom order by somme asc ");
		int rep=0;
		while(res.next()){
			rep=res.getRow();
		}
		res.close();
		return rep ;	
	}

	public int getnbcateg()throws Exception{		//Compter nombre de categorie

		ResultSet res= this.stat.executeQuery("select count(id) as compte from categorie");
		ResultSetMetaData val = res.getMetaData();
		int rep=0;
		while(res.next()){
			rep=res.getInt("compte");
		}
		res.close();
		return rep ;
	}

	public int getnbcompet()throws Exception{		//Compter nombre de competition

		ResultSet res= this.stat.executeQuery("select count(id) as compte from competition");
		ResultSetMetaData val = res.getMetaData();
		int rep=0;
		while(res.next()){
			rep=res.getInt("compte");
		}
		res.close();
		return rep ;
	}

	public int getnbetape()throws Exception{		//Compter nombre d'etape

		ResultSet res= this.stat.executeQuery("select count(id) as compte from competape");
		ResultSetMetaData val = res.getMetaData();
		int rep=0;
		while(res.next()){
			rep=res.getInt("compte");
		}
		res.close();
		return rep ;
	}
	public String[] prenom()throws Exception{
		int nbr=getnbconc();		
		ResultSet val;
		val=this.stat.executeQuery("select prenom , sum(temps) as somme from temprealise join CONCURRENT ON TEMPREALISE.idconc=CONCURRENT.id group by prenom order by somme asc ");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}



		public int[] temps()throws Exception{
		int nbr=getnbconc();		
		ResultSet val;
		val=this.stat.executeQuery("select prenom , sum(temps) as somme from temprealise join CONCURRENT ON TEMPREALISE.idconc=CONCURRENT.id group by prenom order by somme asc ");
		ResultSetMetaData tem=val.getMetaData();
		int[] nomm=new int[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getInt(2);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String[] idconc()throws Exception{		//Avoir l'id de tous les concurrent
		int nbr=getnbconc();		
		ResultSet val;
		val=this.stat.executeQuery("select id from concurrent");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String[] nomconc()throws Exception{		//Avoir le nom de tous les concurrent
		int nbr=getnbconc();		
		ResultSet val;
		val=this.stat.executeQuery("select prenom from concurrent ");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String avoirid()throws Exception{		//Avoir id du prochain <<Sequence>>

		ResultSet val;
		val=this.stat.executeQuery("select id.nextval from dual ");
		int rep=0;
		while(val.next()){
			rep=val.getInt(1);
		}
		val.close();
		String reponse;
		reponse="TPR0"+rep;
		return reponse;
		}


	public String[] idetape()throws Exception{		//Avoir id de toutes les etapes
		int nbr=getnbetape();		
		ResultSet val;
		val=this.stat.executeQuery("select id from competape");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String[] idcateg()throws Exception{		//Avoir id de toutes les categorie
		int nbr=getnbetape();		
		ResultSet val;
		val=this.stat.executeQuery("select id from categorie");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String[] idcompet()throws Exception{		//Avoir id de toutes les competition
		int nbr=getnbetape();		
		ResultSet val;
		val=this.stat.executeQuery("select id from competition");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String[] nomcateg()throws Exception{		//Avoir le nom de toutes les categories
		int nbr=getnbetape();		
		ResultSet val;
		val=this.stat.executeQuery("select nom from categorie");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String[] nomcompet()throws Exception{		//Avoir le nom de toutes les competitions
		int nbr=getnbetape();		
		ResultSet val;
		val=this.stat.executeQuery("select nom from competition");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}


	public Dateheure transform(int at){		//Transofrmer int en dateheure 
		Dateheure dt=new Dateheure();;
		
		int minute=0 ;
		int heure=0 ;
		int seconde=0 ;
		while(at>=60){
			minute++;
			at=at-60;
			if(minute==60){
				heure++;
				minute=0;
			}
		}
		dt.setMinute(minute);
		dt.setHeure(heure);
		dt.setSeconde(at);
	//	System.out.println("Heure :"+dt.getHeure()+" Minute :"+dt.getMinute()+" Seconde : "+dt.getSeconde());
		return dt ;
	} 

		public String[] prenomcateg(String nc)throws Exception{		//Prenom par categorie
		int nbr=getnbconccateg(nc);		
		ResultSet val;
		val=this.stat.executeQuery("select prenom , sum(temps) as somme from temprealise join CONCURRENT ON TEMPREALISE.idconc=CONCURRENT.id join COMPETCATEG ON CONCURRENT.id=COMPETCATEG.idcon where COMPETCATEG.idcat='"+nc+"' group by prenom order by somme asc ");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public int[] tempscateg(String nc)throws Exception{		//Temp par categorie
		int nbr=getnbconccateg(nc);		
		ResultSet val;
		val=this.stat.executeQuery("select prenom , sum(temps) as somme from temprealise join CONCURRENT ON TEMPREALISE.idconc=CONCURRENT.id join COMPETCATEG ON CONCURRENT.id=COMPETCATEG.idcon where COMPETCATEG.idcat='"+nc+"' group by prenom order by somme asc ");
		ResultSetMetaData tem=val.getMetaData();
		int[] nomm=new int[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getInt(2);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public String[] prenomcompet(String nc)throws Exception{		//Prenom par competition
		int nbr=getnbconc();		
		ResultSet val;
		val=this.stat.executeQuery("select prenom , sum(temps) as somme from temprealise join CONCURRENT ON TEMPREALISE.idconc=CONCURRENT.id join COMPETAPE ON TEMPREALISE.idcompetape=COMPETAPE.id join COMPETITION ON COMPETAPE.idcomp=COMPETITION.id where COMPETITION.id='"+nc+"' group by prenom order by somme asc ");
		ResultSetMetaData tem=val.getMetaData();
		String[] nomm=new String[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getString(1);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}

		public int[] tempcompet(String nc)throws Exception{		//Temp par competition
		int nbr=getnbconc();		
		ResultSet val;
		val=this.stat.executeQuery("select prenom , sum(temps) as somme from temprealise join CONCURRENT ON TEMPREALISE.idconc=CONCURRENT.id join COMPETAPE ON TEMPREALISE.idcompetape=COMPETAPE.id join COMPETITION ON COMPETAPE.idcomp=COMPETITION.id where COMPETITION.id='"+nc+"' group by prenom order by somme asc ");
		ResultSetMetaData tem=val.getMetaData();
		int[] nomm=new int[nbr];
		int i=0 ;
		while(val.next())
		{
					nomm[i]=val.getInt(2);	 
					
					System.out.println(nomm[i]);
					i++;
		}

		return nomm ;
	}	

		public int[] voirpoint(String compet)throws Exception{		//Avoir les points

			ResultSet val;
			String[] idcomp=idcompet();
			int nbr=getnbetape();
			int[] point=new int[nbr];
			val=this.stat.executeQuery("select point from resultpoint where idcomp='"+compet+"'");
			int i=0;
			while(val.next()){

					point[i]=val.getInt(1);	 
					
					System.out.println(+point[i]);
					i++;
			}
			return point;
		}

		public int voirrang(String concurrent , String nc)throws Exception{		//Connaitre le rang d'un concurrent'

		ResultSet val;
		val=this.stat.executeQuery("select c.id , sum(temps) as somme from temprealise join CONCURRENT c ON TEMPREALISE.idconc=c.id join COMPETAPE ON TEMPREALISE.idcompetape=COMPETAPE.id join COMPETITION ON COMPETAPE.idcomp=COMPETITION.id where COMPETITION.id='"+nc+"' group by c.id order by somme asc");			
		int ii=1;
		while(val.next())
		{
					if(concurrent.compareTo(val.getString(1))==0)
					{
						break;
					}
					ii=ii+1;
		}
		System.out.println(ii);
		return ii;
		}

		public int voirpoint(String compet , int rang)throws Exception{		//Voir les points pour chaque competition

			ResultSet val;
			String[] idcomp=idcompet();
			int nbr=getnbetape();
			int point=0;
			val=this.stat.executeQuery("select point from resultpoint where idcomp='"+compet+"' and rang='"+rang+"'");
			int i=0;
			while(val.next()){

					point=val.getInt(1);	 
					
					System.out.println(point);
					i++;
			}
			return point;
		}

		public int totalpoint(String conc)throws Exception{		//Total des points par concurrent
			int totale=0;
			String[] idcomp=idcompet();
			for(int i=0 ; i<idcomp.length ; i++){
				int point=voirpoint(idcomp[i],voirrang(conc,idcomp[i]));
				totale=totale+point;
			}

			System.out.println("totale="+totale);
			return totale;
		}
}