package com.example.projet1a;

import java.util.HashMap;
import java.util.Map;

public class Indices {

    public Indices(){
        Map<String, String> indices = new HashMap<>();
        indices.put("somme1", "Lorsque l'on réalise une addition de plusieurs nombres, il faut bien faire attention aux retenues.\nEn cas de problème avec les retenues, nous t'invitons à regarder cette vidéo d'Yvan Monka https://youtu.be/ol-LeW3AO4k");
        indices.put("sommeFrac","Pour additionner deux fractions, il faut s'assurer que les deux fractions possèdent le même dénominateur. On chercher donc si le numérateur et lé dénominateur possède un diviseur commun. Généralement, on testera 2, 3, 5, voire 7.");
        indices.put("reductionFrac","Afin d'obtenir une fraction irréductible, il faut obtenir deux **entiers** ");
        indices.put("sommeMatrice","Pour sommer deux matrices, c'est un véritable jeu d'enfant, à condition qu'elles soient de mêmes dimensions : prenez une cellule située au même endroit dans chaque matrice, additionner leur valeur, et vous obtenez alors le coefficient adéquat dans la nouvelle matrice !");
        indices.put("produitMatrice","Ici c'est plus technique, alors rien de mieux qu'une bonne vidéo d'Yvan Monka pour se détendre et apprendre https://youtu.be/ZOtgQxB5NXI ;)");
        indices.put("","");
    }

}
