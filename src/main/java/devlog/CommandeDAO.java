package devlog;

import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

    public static List<Commande> listCommandes() {
        List<Commande> list = new ArrayList<Commande>();

        Commande c1 = new Commande(6455,	"Nom",	"GP",	98,	9,	"livr",	98,	6,	5,	1031,	98,	109);
        Commande c2 = new Commande(2,	"MDSG",	"TEST",	98,	9,	"livr",	98,	6,	9,	101,	98,	9);
        Commande c3 = new Commande(39,	"XFK",	"IK",	98,	9,	"livr",	98,	6,	5,	10781,	8,	1);
        list.add(c1);
        list.add(c2);
        list.add(c3);

        return list;
    }

}