package com.example.projet1a;

import java.util.HashMap;
import java.util.Map;

public class Indices {

    public Indices(){
        Map<String, String> indices = new HashMap<>();
        indices.put("opération", "Lorsque l'on réalise une opération de plusieurs nombres, il faut bien faire attention aux retenues.\nEn cas de problème avec les retenues, nous t'invitons à regarder les vidéos d'Yvan Monka  :\n- Addition : https://youtu.be/ol-LeW3AO4k\n- Soustraction : https://youtu.be/OMdInhWiqiA\n- Multiplication : https://youtu.be/QRE9Q4cvIMk\n- Division : https://youtu.be/ZIwmQ3KSMH0");
        indices.put("somme3","Un nombre composé de 9 uniquement n'est jamais agréable à calculer. Pour simplifier le calcul, on va rajouter 1 à ce nombre, effectuer le calcul et ne pas oublier de retirer 1 :).");
        indices.put("sommeFrac","Pour additionner deux fractions, il faut s'assurer que les deux fractions possèdent le même dénominateur. On chercher donc si le numérateur et lé dénominateur possède un diviseur commun. Généralement, on testera 2, 3, 5, voire 7.");
        indices.put("sommeVect","La somme de vecteur se fait coordonnées par coordonnées : si x1 est la première coordonnées du vecteur 1 et x2 celle du vecteur 2 alors la première coordonnée de leur somme sera x1 + x2.");
        indices.put("reductionFrac","Afin d'obtenir une fraction irréductible, il faut obtenir deux **entiers** ");
        indices.put("sommeMat","Pour sommer deux matrices, c'est un véritable jeu d'enfant, à condition qu'elles soient de mêmes dimensions : prenez une cellule située au même endroit dans chaque matrice, additionner leur valeur, et vous obtenez alors le coefficient adéquat dans la nouvelle matrice !");
        indices.put("produitMat","Ici c'est plus technique, alors rien de mieux qu'une bonne vidéo d'Yvan Monka pour se détendre et apprendre https://youtu.be/ZOtgQxB5NXI ;)");
        indices.put("trigo","pour calculer une longueur ou un angle, on utilise les formules de trigo ! Petit moyen mnémotechnique CAH SOH TOA ! (casses-toi !) : Cosinus = Adjacent/Hypoténuse, Sinus = Opposé/Hypoténuse et Tangente = Opposé/Adjacent.");
        indices.put("produitScalCoord","Le produit scalaire en coordonnées est un jeu d'enfant : il suffit de multiplier les coordonnées correspondantes entre elles puis de les sommer : x1*x2 + y1*y2.");
        indices.put("pythagore","On va juste rappeler le théorème de Pythagore : Dans un triangle rectangle, le carré de l'hypoténuse est égale à la somme des carrés des deux autres côtés.");
        indices.put("thales","Il ne faut pas oublier les 3 égalités de Thalès ;) Encore une fois, on fait appelle à notre ami Yvan Monka https://youtu.be/JpU7X7AhB-A?t=75");
        indices.put("equation","Il suffit d'isoler x dans l'équation donnée pour trouver sa valeur");
        indices.put("equation2nd","Ici c'est un peu plus dur, il faut calculer le discriminant : b² - 4ac. En fonction du résultat, il y a deux, une, ou zéro solution. Plus d'info ici https://bit.ly/3HgnISY");
    }

}
