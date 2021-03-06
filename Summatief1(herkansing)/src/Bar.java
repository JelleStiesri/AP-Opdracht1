import java.util.*;

public class Bar implements Winkel{
    HashMap<Drankje, Integer> drankjes = new HashMap<>();
    Bioscoop bioscoop;
    Double saldo;

    public Bar(Bioscoop bios, Double saldo) {
        this.bioscoop = bios;
        this.saldo = saldo;
    }

    public void addDrankje(Drankje drankje, Integer aantal) {
        drankjes.put(drankje,aantal);
    }

    public void koopDrankje(Bezoeker klant, Drankje drankje){
        Double prijs = drankje.getPrijs();
        if (drankjes.get(drankje) == null || drankjes.get(drankje) == 0) { // Voorraad check
            System.out.println("\nDrankje niet op voorraad");
        }else{
            if (klant.getSaldo() < prijs) {
                System.out.println("\nSaldo klant te laag");
            }else{
                klant.removeSaldo(prijs); // Bedrag word afgeschreven bij klant
                addSaldo(prijs); // Bedrag word bijgeschreven bij bar
                System.out.println("\nBetaling afgerond");
                drankjes.replace(drankje,drankjes.get(drankje)-1); // Vooraadcorrectie
                klant.addDrankje(drankje);
            }
        }
    }

    @Override
    public void addSaldo(Double bedrag){
        this.saldo += bedrag;
    }

    @Override
    public void removeSaldo(Double bedrag){
        this.saldo -= bedrag;
    }

    @Override
    public Double getSaldo(){
        return saldo;
    }
}