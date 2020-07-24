/**
 * 
 */
package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eni Ecole
 *
 */
public class AppliTestBO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Article> articles = null;
		try {
			// Constituer une liste d'articles
			articles = new ArrayList<Article>();

			// ********************************
			// tester la gestion des articles
			// ********************************

			Stylo unBic = new Stylo(1, "Bic", "BBOrange", "Bic bille Orange", 1.2f, 20, "Bleu");
			System.out.println("\nREM : Affichage d'un article Stylo 'Bic'");
			System.out.println(unBic.toString());
			System.out.println("---------------------------------------------------------------");
			Ramette uneRamette = new Ramette(2, "Clairef", "CRA4S", "Ramette A4 Sup", 9f, 20, 80);
			System.out.println("\nREM : Affichage d'un article Ramette 'Clairefontaine'");
			System.out.println(uneRamette.toString());
			System.out.println("---------------------------------------------------------------");

			// Ajout des articles à la liste.
			articles.add(unBic);
			articles.add(uneRamette);

			articles.add(new Stylo(3, "Stypen", "PlumeS", "Stylo Plume Stypen", 5.5f, 20, "jaune"));
			articles.add(new Stylo(4, "Waterman", "WOBGreen", "Waterman Orion Bille vert", (float) 5.5, 20, "vert"));
			articles.add(new Stylo(5, "Parker", "PlumeP", "Stylo Plume Parker", 5.5f, 5, "noir"));

			System.out.println("\nREM : Affichage du catalogue");
			// on affiche la liste des articles
			afficherCatalogue(articles);
			System.out.println("---------------------------------------------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ******************
		// tester le Panier
		// ******************
		Panier panier = new Panier();
		try {
			panier.addLigne(articles.get(0), 2);
			System.out.println("\nREM : Affichage de l'article de la premiere ligne du panier");
			System.out.println(panier.getLigne(0).getArticle());
			System.out.println("---------------------------------------------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR : " + e.getMessage());
		}

		try {
			panier.addLigne(articles.get(1), 13);
			panier.addLigne(articles.get(2), 12);
			panier.addLigne(articles.get(3), 5);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR : " + e.getMessage());
		}

		try {
			System.out.println("\nREM : Affichage du panier - Ajout");

			System.out.println(panier.toString());
			System.out.println("---------------------------------------------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR : " + e.getMessage());
		}

		try {
			System.out.println("\nREM : Modification du panier");
			// modifier une ligne du panier
			panier.updateLigne(0, 3); // +1 de BBOrange
			System.out.println(panier.toString());
			System.out.println("---------------------------------------------------------------");
			panier.updateLigne(1, 12); // -1 de CRA4S
			System.out.println(panier.toString());
			System.out.println("---------------------------------------------------------------");
			// supprimer une ligne du panier
			panier.removeLigne(2); // suppression de PlumeS

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR : " + e.getMessage());
		}

		try {
			System.out.println("\nREM : Affichage du panier - Modification");

			System.out.println(panier.toString());
			System.out.println("---------------------------------------------------------------");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR : " + e.getMessage());
		}

	}

	private static void afficherCatalogue(List<Article> articles) {
		for (Article article : articles) {
			System.out.println(article.toString());
		}

	}

}
