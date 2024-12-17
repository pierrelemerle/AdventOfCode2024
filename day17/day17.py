# Lecture du fichier d'entrée
with open("input.txt") as fichier:
    donnees = fichier.read()

# Extraction des registres initiaux et du programme
registre_initiaux, programme_brut = donnees.strip().split("\n\n")
registre_a, registre_b, registre_c = (int(ligne.split(": ")[1]) for ligne in registre_initiaux.split("\n"))
programme = list(map(int, programme_brut.replace("Program: ", "").split(",")))

def executer_programme(ra, rb, rc):
    """Exécute le programme avec les registres initiaux ra, rb, rc."""
    registres = {"a": ra, "b": rb, "c": rc}
    pointeur = 0
    sortie = []

    # Définir les combinaisons dynamiques selon les registres
    combos = [
        lambda: 0,          # combo 0: toujours 0
        lambda: 1,          # combo 1: toujours 1
        lambda: 2,          # combo 2: toujours 2
        lambda: 3,          # combo 3: toujours 3
        lambda: registres["a"],  # combo 4: valeur de a
        lambda: registres["b"],  # combo 5: valeur de b
        lambda: registres["c"],  # combo 6: valeur de c
    ]

    # Définir les instructions comme fonctions internes
    def divise_a(operande): registres["a"] //= 2 ** combos[operande]()
    def xor_b_operande(operande): registres["b"] ^= operande
    def mod_b(operande): registres["b"] = combos[operande]() % 8
    def saut_si_a_non_zero(operande): return operande if registres["a"] != 0 else None
    def xor_b_c(_): registres["b"] ^= registres["c"]
    def ajoute_sortie(operande): sortie.append(combos[operande]() % 8)
    def divise_a_b(operande): registres["b"] = registres["a"] // 2 ** combos[operande]()
    def divise_a_c(operande): registres["c"] = registres["a"] // 2 ** combos[operande]()

    # Liste des opcodes associés aux instructions
    operations = [
        divise_a,          # opcode 0
        xor_b_operande,    # opcode 1
        mod_b,             # opcode 2
        saut_si_a_non_zero, # opcode 3
        xor_b_c,           # opcode 4
        ajoute_sortie,     # opcode 5
        divise_a_b,        # opcode 6
        divise_a_c,        # opcode 7
    ]

    # Boucle principale du programme
    while pointeur < len(programme):
        opcode, operande = programme[pointeur:pointeur + 2]
        saut = operations[opcode](operande)
        pointeur = saut if saut is not None else pointeur + 2

    return sortie

# Partie 1 : Exécution avec les registres initiaux
resultat_sortie = executer_programme(registre_a, registre_b, registre_c)
print("Le résultat du programme, pour la partie 1, est :", ",".join(map(str, resultat_sortie)))

# Partie 2 : Recherche de la valeur correcte pour le registre a
valeurs_valides = [0]
for longueur in range(1, len(programme) + 1):
    nouvelles_valeurs = []
    for valeur in valeurs_valides:
        for decalage in range(8):
            nouveau_a = 8 * valeur + decalage
            if executer_programme(nouveau_a, 0, 0) == programme[-longueur:]:
                nouvelles_valeurs.append(nouveau_a)
    valeurs_valides = nouvelles_valeurs

print("La valeur nécessaire pour A, pour la partie 2, est :", min(valeurs_valides))
