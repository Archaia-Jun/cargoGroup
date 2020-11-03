package devlog;

import javax.swing.*;
import java.io.*;
import java.sql.Time;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * <p>BCC : </p>
 * <p>logiciel gestion : </p>
 * <p>Copyright : Copyright (c) 2020</p>
 * <p>Société : Web Afrique</p>
 * @author JSekpon
 * @version 1.0
 */

public class LireEtEcrire extends Thread{
  String mot;
  public LireEtEcrire() {
    start();
  }

  public void run(){
    ListeLecture(mot);
  }
  //ecriture
  public void ecrire(String texte,String nomfichier){
    java.sql.Date dt = new java.sql.Date(new Date().getTime());
    Time temps= new Time(new Date().getTime());
    System.out.println(temps);
    try
      {
	FileWriter fw = new FileWriter(nomfichier, true);
	BufferedWriter output = new BufferedWriter(fw);
	output.write(texte+"\t"+dt+"\t"+temps+"\n");
	output.flush();
	output.close();
      }
    catch(IOException ioe){System.out.println("erreur : " + ioe );}
  }

  //lecture
  public void lire(String nomFichier){
    String nom;
    try
      {
	FileReader fr = new FileReader(nomFichier);
	BufferedReader br = new BufferedReader(fr);
	String texte=" "+":";
	int a = 0;
	do{
	  String lignelue=br.readLine();
	  if (lignelue==null) break;
	  StringTokenizer tok=new StringTokenizer(lignelue,texte);
	  nom=br.readLine();
	  //texte =br.readLine() + "\n";
	  a++;
	  //if (texte!=null) System.out.println(texte);
	}
	while(br.readLine()!=null);
	br.close();
	//System.out.println(texte + "contient"+ heure + minute + second);
      }
    catch(IOException ioe){System.out.println("erreur : " + ioe);}
  }

  // lecture retournant un String

  public String Lecture(String nomFichier){
    String nom="";
    try{
      FileReader fr = new FileReader(nomFichier);
      BufferedReader br = new BufferedReader(fr);
      String separateur="\t";
      int a = 0;
      do{
	String lignelue=br.readLine();
	if (lignelue==null) break;
	StringTokenizer tok=new StringTokenizer(lignelue,separateur);
	nom=tok.nextToken();
	a++;
      }
      while(br.readLine()!=null);
      br.close();
      //System.out.println(texte + "contient"+ heure + minute + second);
    }
    catch(IOException ioe){System.out.println("erreur : " + ioe);}
    return nom;
  }

  //liste de lecture
  public Vector ListeLecture(String nomFichier){
    String nom="";
    Vector v=new Vector();

    try{
      FileReader fr = new FileReader(nomFichier);
      BufferedReader br = new BufferedReader(fr);
      String separateur=" ";
      int a = 0;
      do{
	String lignelue=br.readLine();
	if (lignelue==null) break;
	StringTokenizer tok=new StringTokenizer(lignelue,separateur);
	 Vector v0=new Vector();
	for(int i=0;i<tok.countTokens();i++){
	  nom=tok.nextToken();
	  v0.addElement(nom);
	  System.out.print(nom+"\t");
	  System.out.println();
	  v.addElement(v0);
	}

	a++;
      }
      while(br.readLine()!=null);
      br.close();
      //System.out.println(texte + "contient"+ heure + minute + second);
    }
    catch(IOException ioe){System.out.println("erreur : " + ioe);}
    return v;
  }



  //  //liste de lecture
  public JTable tableLecture(String nomFichier, Vector v1){
    String nom="";
    Vector v=new Vector();
    JTable Table=new JTable();;
    try{
      FileReader fr = new FileReader(nomFichier);
      BufferedReader br = new BufferedReader(fr);
      String separateur="\t ";
      do{
	String lignelue=br.readLine();
	if (lignelue==null) break;
	StringTokenizer tok=new StringTokenizer(lignelue,separateur);
	Vector v0=new Vector();
	System.out.println("nombre de mot "+tok.countTokens());
	for(int i=0;i<tok.countTokens();i++){
	  nom=tok.nextToken();
	  v0.addElement(nom);
	  System.out.print(nom+"\t");
	  System.out.println();	 System.out.println();
	}
	v.addElement(v0);
      }
      while(br.readLine()!=null);
      Table = new JTable(v,v1);
      br.close();

    }
    catch(IOException ioe){System.out.println("erreur : " + ioe);}
    return Table;
  }


  //argument d'un ficher
  public void UneLecture(String nomFichier, int i, JComboBox combo){
    String mot="";
    try{
      FileReader fr = new FileReader(nomFichier);
      BufferedReader br = new BufferedReader(fr);
      String separateur=" ";
      int a = 0;
      do{
	String lignelue=br.readLine();
	if (lignelue==null) break;
	StringTokenizer tok=new StringTokenizer(lignelue,separateur);
	if (tok.countTokens()>=i){
	  for(int j=0; j<i;j++){
	    mot=tok.nextToken();
	  }
	}
	combo.addItem(mot);
	System.out.print(mot+"\t");
	System.out.println();
	a++;
      }
      while(br.readLine()!=null);
      combo.revalidate();
      br.close();
      //System.out.println(texte + "contient"+ heure + minute + second);
    }
    catch(IOException ioe){
      System.out.println("erreur : " + ioe);
    }
  }
}
