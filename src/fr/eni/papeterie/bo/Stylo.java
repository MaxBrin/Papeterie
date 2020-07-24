/**
 * 
 */
package fr.eni.papeterie.bo;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 21 juil. 2020
 */
public class Stylo extends Article {
	private String couleur;

	public Stylo() {
		super();
	}

	/**
	 * @param idArticle
	 * @param reference
	 * @param marque
	 * @param designation
	 * @param prixUnitaire
	 * @param qteStock
	 * @param couleur
	 */
	public Stylo(Integer idArticle, String marque, String reference, String designation, float prixUnitaire,
			int qteStock, String couleur) {
		super(idArticle, marque, reference, designation, prixUnitaire, qteStock);
		setCouleur(couleur);
	}

	/**
	 * @param reference
	 * @param marque
	 * @param designation
	 * @param prixUnitaire
	 * @param qteStock
	 */
	public Stylo(String marque, String reference, String designation, float prixUnitaire, int qteStock,
			String couleur) {
		super(marque, reference, designation, prixUnitaire, qteStock);
		setCouleur(couleur);
	}

	/**
	 * @return the couleur
	 */
	public String getCouleur() {
		return couleur;
	}

	/**
	 * @param couleur the couleur to set
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public String toString() {

		return super.toString() + "Stylo [couleur=" + couleur + "]";
	}

}
