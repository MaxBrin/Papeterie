/**
 * 
 */
package fr.eni.papeterie.bo;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 21 juil. 2020
 */
public class Ramette extends Article {
	private int grammage;

	public Ramette() {
		super();
	}

	/**
	 * @param idArticle
	 * @param reference
	 * @param marque
	 * @param designation
	 * @param prixUnitaire
	 * @param qteStock
	 */
	public Ramette(Integer idArticle, String marque, String reference, String designation, float prixUnitaire,
			int qteStock, int grammage) {
		super(idArticle, marque, reference, designation, prixUnitaire, qteStock);
		setGrammage(grammage);
	}

	/**
	 * @param reference
	 * @param marque
	 * @param designation
	 * @param prixUnitaire
	 * @param qteStock
	 */
	public Ramette(String marque, String reference, String designation, float prixUnitaire, int qteStock,
			int grammage) {
		super(marque, reference, designation, prixUnitaire, qteStock);
		setGrammage(grammage);
	}

	/**
	 * @return the grammage
	 */
	public int getGrammage() {
		return grammage;
	}

	/**
	 * @param grammage the grammage to set
	 */
	public void setGrammage(int grammage) {
		this.grammage = grammage;
	}

	@Override
	public String toString() {
		return super.toString() + "Ramette [grammage=" + grammage + "]";
	}

}
