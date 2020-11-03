package devlog;

public class Commande {

    private long NumFacture;
    private String NomClient;
    private String Dossier;
    private long PoidsBrut;
    private long DebooursMOE;
    private String Prestation;
    private long MontantHT;
    private long TVA;
    private long MontantTVA;
    private long MontantTTC;
    private long AIB;
    private long NetAPayer;



    public Commande(long numFacture, String nomClient, String dossier, long poidsBrut, long debooursMOE, String prestation, long montantHT, long TVA, long montantTVA, long montantTTC, long AIB, long netAPayer) {
        this.NumFacture = numFacture;
        this.NomClient = nomClient;
        this.Dossier = dossier;
        this.PoidsBrut = poidsBrut;
        this.DebooursMOE = debooursMOE;
        this.Prestation = prestation;
        this.MontantHT = montantHT;
        this.TVA = TVA;
        this.MontantTVA = montantTVA;
        this.MontantTTC = montantTTC;
        this.AIB = AIB;
        this.NetAPayer = netAPayer;
    }



    public long getNumFacture() {
        return NumFacture;
    }

    public void setNumFacture(long numFacture) {
        this.NumFacture = numFacture;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String nomClient) {
        this.NomClient = nomClient;
    }

    public String getDossier() {
        return Dossier;
    }

    public void setDossier(String dossier) {
        Dossier = dossier;
    }

    public long getPoidsBrut() {
        return PoidsBrut;
    }

    public void setPoidsBrut(long poidsBrut) {
        PoidsBrut = poidsBrut;
    }

    public long getDebooursMOE() {
        return DebooursMOE;
    }

    public void setDebooursMOE(long debooursMOE) {
        DebooursMOE = debooursMOE;
    }

    public String getPrestation() {
        return Prestation;
    }

    public void setPrestation(String prestation) {
        Prestation = prestation;
    }

    public long getMontantHT() {
        return MontantHT;
    }

    public void setMontantHT(long montantHT) {
        MontantHT = montantHT;
    }

    public long getTVA() {
        return TVA;
    }

    public void setTVA(long TVA) {
        this.TVA = TVA;
    }

    public long getMontantTVA() {
        return MontantTVA;
    }

    public void setMontantTVA(long montantTVA) {
        MontantTVA = montantTVA;
    }

    public long getMontantTTC() {
        return MontantTTC;
    }

    public void setMontantTTC(long montantTTC) {
        MontantTTC = montantTTC;
    }

    public long getAIB() {
        return AIB;
    }

    public void setAIB(long AIB) {
        this.AIB = AIB;
    }

    public long getNetAPayer() {
        return NetAPayer;
    }

    public void setNetAPayer(long netAPayer) {
        NetAPayer = netAPayer;
    }

    public void add(FenetreCommande fenetreCommande) {
    }
}