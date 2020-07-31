/**
 * 
 */
package fr.eni.papeterie.ihm;

import java.awt.*;
import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import javax.swing.*;
import javax.swing.border.Border;

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
	private static Logger monLogger;


 static{

 }
	private ArticleControleur() {
		monLogger = (Logger) LoggerFactory.getLogger("fr.eni");
		monLogger.debug("Lancement de l'application");

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
		try {
			mgr = CatalogueManager.getInstance();
			articles = mgr.getCatalogue();
		} catch (BLLException e) {
			ecran.afficherNouveau();
			monLogger.error("BLL Echec Acces BD");
			ecran.getLblLogger().setText(e.getMessage());
		}
		try{
		if (articles.size() <= 0) {
			// Affiche un article vide si il n'y pas de catalogue avec par default une
			// ramette de 80gr
			indexCourant = -1;
			ecran.afficherNouveau();
		} else {
			// Affiche le premier article du catalogue
			indexCourant = 0;
			ecran.afficheArticle(articles.get(indexCourant));
			ecran.getPbIndexCatalogue().setMaximum(articles.size());
			ecran.getPbIndexCatalogue().setValue(indexCourant+1);

		}
		}catch (NullPointerException e){
			monLogger.error("BLL Echec Acces BD");
			ecran.getLblLogger().setText(e.getMessage());
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
			} else {
				// Si l'indexCourant est inférieur à 0 il prend la valeur de l'index du dernier
				// élément du catalogue et l'affiche
				indexCourant = articles.size() - 1;
			}
			ecran.afficheArticle(articles.get(indexCourant));
			ecran.getLblLogger().setText("");
			defaultBorder();
			ecran.getPbIndexCatalogue().setValue(indexCourant+1);
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
			if (indexCourant >= articles.size() - 1) {
				indexCourant = 0;
			} else {
				indexCourant++;
			}
			ecran.afficheArticle(articles.get(indexCourant));
			ecran.getLblLogger().setText("");
			ecran.getPbIndexCatalogue().setValue(indexCourant+1);
			defaultBorder();


		}
	}

	public void nouveau() {
		indexCourant = articles.size();
		ecran.afficherNouveau();
		ecran.getLblLogger().setText("");
		ecran.getPbIndexCatalogue().setValue(0);
		defaultBorder();


	}

	/**
	 * Sauvegarde un nouvel article dans la base de donné ou met à jour un article
	 * existant
	 */
	public void save() {
		Article article = null;
		try {
			article = ecran.getArticle();
			validerArticle(article);
			try {
				//
				if (indexCourant != -1 && indexCourant != articles.size()) {
					article.setIdArticle(indexCourant);
					mgr.updateArticle(article);
					articles.set(indexCourant, article);
				} else {
					mgr.addArticle(article);
					articles.add(article);
					indexCourant++;
				}
				ecran.afficheArticle(article);
				ecran.getPbIndexCatalogue().setMaximum(articles.size());
				ecran.getPbIndexCatalogue().setValue(indexCourant+1);

				defaultBorder();
				ecran.getLblLogger().setText("");

			} catch (BLLException e) {
				monLogger.error(e.getMessage());
				ecran.getLblLogger().setText("Il faut des données dans les cases surlignées.");

			}
		}catch (NumberFormatException e){
			ecran.getLblLogger().setText(e.getMessage());
			ecran.getTxtStock().setBorder(BorderFactory.createLineBorder(Color.RED));
			ecran.getTxtPrix().setBorder(BorderFactory.createLineBorder(Color.RED));
		}


	}

	/**
	 * Efface un article de la base de donnée et passe au suivant
	 */
	public void delete() {
		try {
			if (indexCourant>=0){
			mgr.removeArticle(articles.get(indexCourant));
			articles.remove(indexCourant);
			}
			else {
				throw  new BLLException("Pas d'article");
			}
		} catch (BLLException e) {
			monLogger.error("BLL Échec de suppression");
			ecran.getLblLogger().setText(e.getMessage());
		}
		try {
			if (indexCourant < 0) {
				ecran.afficherNouveau();
			} else if (indexCourant < articles.size()) {
				ecran.afficheArticle(articles.get(indexCourant));
			} else {
				indexCourant--;
				ecran.afficheArticle(articles.get(indexCourant));
			}
		}catch (NullPointerException e){
			ecran.getLblLogger().setText(e.getMessage());
		}

	}

	/**
	 * Changement en rouge de la bordure des champs si il y'a une erreur
	 * @param article
	 */
	public void validerArticle(Article article)  {


		// Les attributs des articles sont obligatoires
		if (article.getReference() == null || article.getReference().trim().length() == 0) {
			ecran.getTxtReference().setBorder(BorderFactory.createLineBorder(Color.RED));

		}
		if (article.getMarque() == null || article.getMarque().trim().length() == 0) {
			ecran.getTxtMarque().setBorder(BorderFactory.createLineBorder(Color.RED));

		}
		if (article.getDesignation() == null || article.getDesignation().trim().length() == 0) {
			ecran.getTxtDesignation().setBorder(BorderFactory.createLineBorder(Color.RED));

		}
		if(article.getQteStock() == 0 || article.getQteStock() ==null){
			ecran.getTxtStock().setBorder(BorderFactory.createLineBorder(Color.RED));

		}
		if(article.getPrixUnitaire() == 0){
			ecran.getTxtPrix().setBorder(BorderFactory.createLineBorder(Color.RED));

		}
		if (article instanceof Stylo) {
			if (((Stylo) article).getCouleur() == null || ((Stylo) article).getCouleur().trim().length() == 0) {
			ecran.getCbCouleur().setBorder(BorderFactory.createLineBorder(Color.RED));

			}
		}
	}

	/**
	 * Retour par default des couleurs des bordures des différents champs
	 */
	public void defaultBorder(){

		ecran.getTxtReference().setBorder((UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border")));
		ecran.getTxtMarque().setBorder((UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border")));
		ecran.getTxtDesignation().setBorder((UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border")));
		ecran.getTxtStock().setBorder((UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border")));
		ecran.getTxtPrix().setBorder((UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border")));
		ecran.getCbCouleur().setBorder((UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border")));
	}
}
