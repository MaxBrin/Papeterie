/**
 * 
 */
package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 27 juil. 2020
 */
public class CatalogueManager {
	private static CatalogueManager instance;
	private static ArticleDAO daoArticles;

	public static CatalogueManager getInstance() {
		if (instance == null) {
			instance = new CatalogueManager();
		}
		return instance;
	}

	static {
		daoArticles = DAOFactory.getArticleDAO();
	}

	private CatalogueManager() {

	}

	/**
	 * Ajoute un article dans la base de données
	 * 
	 * @param article Article a ajouter
	 * @throws BLLException Exception si article déjà présent dans le catalogue
	 */
	public void addArticle(Article article) throws BLLException {
		try {
			validerArticle(article);
			if (article.getIdArticle() != null) {
				throw new BLLException("Ajout d'un article déjà présent");
			}
			daoArticles.insert(article);
		} catch (DALException e) {
			throw new BLLException("Echec de addArticle", e);
		}
	}

	/**
	 * 
	 * @return
	 * @throws BLLException
	 */
	public List<Article> getCatalogue() throws BLLException {
		List<Article> listArticle = null;
		try {
			listArticle = daoArticles.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de GetCatalogue", e);
		}
		return listArticle;
	}

	/**
	 * 
	 * @param article
	 * @throws BLLException
	 */
	public void updateArticle(Article article) throws BLLException {
		try {
			validerArticle(article);
			daoArticles.update(article);
		} catch (DALException e) {
			throw new BLLException("Echec de updateArticle - article : " + article, e);
		} catch (NullPointerException e) {
			throw new BLLException("L'article n'est pas présent dans le catalogue", e);
		}

	}

	/**
	 * 
	 * @param article
	 * @throws BLLException
	 */
	public void removeArticle(Article article) throws BLLException {
		try {
			validerArticle(article);
			daoArticles.delete(article.getIdArticle());
		} catch (DALException e) {
			throw new BLLException("Echec de removeArticle - article : " + article, e);
		} catch (NullPointerException e) {
			throw new BLLException("L'article n'est pas présent dans le catalogue", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 * @throws BLLException
	 */
	public Article getArticle(int index) throws BLLException {
		Article article = null;
		try {
			System.out.println();
			article = daoArticles.selectById(index);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (article == null) {
			throw new BLLException("Cet id d'article n'existe pas dans le catalogue");
		}
		return article;
	}

	/**
	 * 
	 * @param article
	 * @throws BLLException
	 */
	public void validerArticle(Article article) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (article == null) {
			throw new BLLException("Article null");
		}
		// Les attributs des articles sont obligatoires
		if (article.getReference() == null || article.getReference().trim().length() == 0) {
			sb.append("La reference article est obligatoire.\n");
			valide = false;
		}
		if (article.getMarque() == null || article.getMarque().trim().length() == 0) {
			sb.append("La marque  est obligatoire.\n");
			valide = false;
		}
		if (article.getDesignation() == null || article.getDesignation().trim().length() == 0) {
			sb.append("La designation  est obligatoire.\n");
			valide = false;
		}
		if (article instanceof Ramette && ((Ramette) article).getGrammage() <= 0) {
			sb.append("Le grammage doit avoir une valeur positive.\n");
			valide = false;
		}
		if (article instanceof Stylo) {
			if (((Stylo) article).getCouleur() == null || ((Stylo) article).getCouleur().trim().length() == 0) {
				sb.append("La couleur pour un stylo  est obligatoire.\n");
				valide = false;
			}
		}

		if (!valide) {
			throw new BLLException(sb.toString());
		}

	}
}
