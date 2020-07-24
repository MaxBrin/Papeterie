/**
 * 
 */
package fr.eni.papeterie.bo;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 21 juil. 2020
 */
public class Ligne {
	protected int qte;
	private Article article;

	/**
	 * @param qte
	 */
	public Ligne(Article article, int qte) {
		setArticle(article);
		setQte(qte);
	}

	/**
	 * @return the qte
	 */
	public int getQte() {
		return qte;
	}

	/**
	 * @param qte the qte to set
	 */
	public void setQte(int qte) {
		this.qte = qte;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	private void setArticle(Article article) {
		this.article = article;
	}

	public float getPrix() {
		return this.article.getPrixUnitaire();
	}

	@Override
	public String toString() {
		return "Ligne [qte=" + qte + ", article=" + article + "]";
	}

}
