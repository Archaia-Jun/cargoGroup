package devlog;

/**
 * <p>BCC : </p>
 * <p>logiciel gestion : </p>
 * <p>Copyright : Copyright (c) 2020</p>
 * <p>Société : Web Afrique</p>
 * @author JSekpon
 * @version 1.0
 */

public class Format {
  public Format() {
  }
  public String devFormat(String message){
    char mot[] = message.toCharArray();
    for(int i=0;i<mot.length;i++){
      if (mot[i]==' '){
        mot[i]='_';
      }
    }
    String mes =new String(mot);
    return mes;
  }


}