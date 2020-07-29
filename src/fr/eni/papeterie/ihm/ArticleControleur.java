/**
 * 
 */
package fr.eni.papeterie.ihm;

import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 29 juil. 2020
 */
public class ArticleControleur {
	// Attribut de class
	private static List<Article> articles;
	private static ArticleControleur instance;
	private static Ecran ecran;
	// Index pour savoir où on est dans le catalogue
	private static int indexCourant;
	private static CatalogueManager mgr;

	private ArticleControleur() {
		try {
			mgr = CatalogueManager.getInstance();
			articles = mgr.getCatalogue();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArticleControleur getInstance() {
		if (instance == null) {
			instance = new ArticleControleur();
		}
		return instance;
	}

	/**
	 * Permet de lancer l'application à partir du main
	 */
	public void start() {
		ecran = new Ecran();
		afficherPremierArticle();
		ecran.setVisible(true);
	}

	/**
	 * Affiche le premier Article de la base sinon affiche l'article par default
	 */
	public void afficherPremierArticle() {
		if (articles.size() <= 0) {
			// Affiche un article vide si il n'y pas de catalogue avec par default une
			// ramette de 80gr
			indexCourant = -1;
			ecran.afficherNouveau();
		} else {
			// Affiche le premier article du catalogue
			indexCourant = 0;
			ecran.afficheArticle(articles.get(indexCourant));
		}
	}

	/**
	 * Permet de reculer dans le catalogue et de revenir au dernier article si on
	 * est au début du catalogue
	 */
	public void back() {
		// si le catalogue est vide il affiche l'article par default
		if (articles.size() <= 0) {
			ecran.afficherNouveau();
		} else {
			// Si l'indexCourant est supérier à 0 il diminue et affiche l'article
			// correspondant
			if (indexCourant > 0) {
				indexCourant--;
				ecran.afficheArticle(articles.get(indexCourant));
			} else {
				// Si l'indexCourant est inférieur à 0 il prend la valeur de l'index du dernier
				// élément du catalogue et l'affiche
				indexCourant = articles.size() - 1;
				ecran.afficheArticle(articles.get(indexCourant));
			}
		}
	}

	/**
	 * Permet d'avancer dans le catalague et de revenir au premier article si on est
	 * à la fin du catalogue
	 */
	public void forward() {
		if (articles.size() <= 0) {
			ecran.afficherNouveau();
		} else {
			if (indexCourant >= articles.size()) {
				indexCourant = 0;
				ecran.afficheArticle(articles.get(indexCourant));
			} else {
				ecran.afficheArticle(articles.get(indexCourant));
				indexCourant++;
			}
		}
	}

	public void nouveau() {
		indexCourant = articles.size();
		ecran.afficherNouveau();
	}

	/**
	 * Sauvegarde un nouvel article dans la base de donné ou met à jour un article
	 * existant
	 */
	public void save() {
		Article article = ecran.getArticle();
		try {
			if (article.getIdArticle() != null) {
				mgr.updateArticle(article);
				articles.set(indexCourant, article);
			} else {
				mgr.addArticle(article);
				articles.add(article);
				ecran.afficheArticle(article);
				indexCourant++;
			}
		} catch (BLLException e) {
			ecran.messageErreur("Erreur de saisie d'un article.");
			e.printStackTrace();
		}
	}

	/**
	 * Efface un article de la base de donnée et passe au suivant
	 */
	public void delete() {
		try {
			mgr.removeArticle(articles.get(indexCourant));
			articles.remove(indexCourant);
		} catch (Exception e) {
			ecran.messageErreur("Erreur de suppréssion.");
			e.printStackTrace();
		}
		if (indexCourant < 0) {
			ecran.afficherNouveau();
		} else if (indexCourant < articles.size()) {
			ecran.afficheArticle(articles.get(indexCourant));
		} else {
			indexCourant--;
			ecran.afficheArticle(articles.get(indexCourant));
		}

	}
}
