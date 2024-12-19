import java.util.*;

public class Day19Util {

    public static boolean peutFormerDesign(String design, List<String> motifs) {

        // Si le design est vide, il peut être formé
        if (design.isEmpty()) {
            return true;
        }

        // Essayer chaque motif pour voir s'il peut être utilisé au début du design
        for (String motif : motifs) {
            if (design.startsWith(motif)) {
                // Récursivement vérifier le reste du design
                String reste = design.substring(motif.length());
                if (peutFormerDesign(reste, motifs)) {
                    return true;
                }
            }
        }
        // Si aucun motif ne correspond, le design ne peut pas être formé
        return false;
    }

    // Fonction pour compter toutes les possibilités pour former un design
    public static long combienDePossibilite(String design, List<String> motifs, Map<String, Long> memo) {
        // Vérifier si le design est dans le cache
        if (memo.containsKey(design)) {
            return memo.get(design);
        }

        // Si le design est vide, il y a une seule possibilité (terminée)
        if (design.isEmpty()) {
            return 1;
        }

        // Compter les possibilités
        long nbPossibilites = 0;

        // Essayer chaque motif pour voir s'il peut être utilisé au début du design
        for (String motif : motifs) {
            if (design.startsWith(motif)) {
                // Récursivement vérifier le reste du design
                String reste = design.substring(motif.length());
                nbPossibilites += combienDePossibilite(reste, motifs, memo);
            }
        }

        // Mémoriser le résultat pour ce design
        memo.put(design, nbPossibilites);
        return nbPossibilites;
    }

    public static List<String> filtrerMotifsUniques(List<String> motifs) {
        // Liste pour les motifs uniques
        List<String> motifsUniques = new ArrayList<>();

        // Trier les motifs par longueur (les plus courts d'abord)
        motifs.sort(Comparator.comparingInt(String::length));

        for (int i = 0; i < motifs.size(); i++) {
            String motif = motifs.get(i);

            // Vérifier si le motif peut être construit à partir des autres motifs
            List<String> autresMotifs = new ArrayList<>(motifsUniques); // Considérer uniquement les motifs déjà validés
            if (!peutFormerDesign(motif, autresMotifs)) {
                motifsUniques.add(motif); // Si non constructible, le conserver\n
            }
        }

        return motifsUniques;
    }

}