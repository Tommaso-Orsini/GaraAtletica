import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GestioneFile {


    private final String nomeFile;


    public GestioneFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }


    public List<String> leggiClassificaPrecedente() {
        List<String> classifica = new ArrayList<>();
        File file = new File(nomeFile);


        if (!file.exists()) {
            System.out.println("Nessuna classifica precedente trovata.");
            return classifica;
        }


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String riga;
            System.out.println("\nClassifica precedente:");
            while ((riga = br.readLine()) != null) {
                classifica.add(riga);
                System.out.println(riga);
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura della classifica precedente.");
        }


        return classifica;
    }


    // scrive la classifica di fine gara
    public void scriviClassifica(List<Atleta> classifica, int arrivati) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeFile))) {


            pw.println("CLASSIFICA FINALE:");
            for (int i = 0; i < classifica.size(); i++) {
                Atleta a = classifica.get(i);
                String linea = (i + 1) + " - " + a.getNome();


                if (i >= arrivati) {
                    linea += " (RITIRATO)";
                }


                pw.println(linea);
            }


            System.out.println("\nLa classifica verrà salvata");


        } catch (IOException e) {
            System.err.println("Errore nella scrittura della classifica.");
        }
    }
}



