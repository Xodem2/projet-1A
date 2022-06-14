package com.example.projet1a;

import java.util.HashMap;
import java.util.Map;

public class Indices {

    private Map<String, String> indices = new HashMap<>();

    public Indices(){
        indices.put("opération", "Attention aux retenues !");
        //indices.put("somme3","Un nombre composé de 9 uniquement n'est jamais agréable à calculer. Pour simplifier le calcul, on va rajouter 1 à ce nombre, effectuer le calcul et ne pas oublier de retirer 1 :).");
        indices.put("sommeFrac","Pour additionner deux fractions, il faut s'assurer que les deux fractions possèdent le même dénominateur.");
        indices.put("réductionFrac","Afin d'obtenir une fraction irréductible, il faut obtenir deux **entiers**.");
        indices.put("sommeVect","La somme de vecteur se fait coordonnées par coordonnées.");
        indices.put("diffVect","La différence de vecteur se fait coordonnées par coordonnées.");
        indices.put("sommeMat","Pour sommer deux matrices, prenez une cellule située au même endroit dans chaque matrice, additionner leur valeur, et vous obtenez alors le coefficient adéquat dans la nouvelle matrice !");
        indices.put("diffMat","Pour soustraire deux matrices, prenez une cellule située au même endroit dans chaque matrice, soustraire leur valeur, et vous obtenez alors le coefficient adéquat dans la nouvelle matrice !");
        indices.put("produitMat","Attention aux signes !");
        indices.put("transMat","Inverser les lignes et les colonnes.");
        indices.put("detMat","a d-b c");
        indices.put("sqr", "Multiplier le nombre par lui-même. Attention, multiplier 2 signes - rend le nombre positif.");
        indices.put("sqrt", "Trouver 2 nombres tels que la multiplication de ces 2 nombres fasse le nombre sous la racine.");
        indices.put("trigo","Pour calculer une longueur ou un angle, on utilise les formules de trigo ! Petit moyen mnémotechnique CAH SOH TOA ! (casses-toi !).");
        indices.put("produitScalCoord","x1*x2 + y1*y2");
        indices.put("pythagore","Dans un triangle rectangle, le carré de l'hypoténuse est égale à la somme des carrés des deux autres côtés.");
        indices.put("thalès","Il ne faut pas oublier les 3 égalités de Thalès.");
        indices.put("équation","Il suffit d'isoler x dans l'équation donnée pour trouver sa valeur.");
        indices.put("équation2nd","Ici c'est un peu plus dur, il faut calculer le discriminant : b² - 4ac. En fonction du résultat, il y a deux, une, ou zéro solution.");
    }

    public String getInd(String id) {
        return indices.get(id);
    }

}
