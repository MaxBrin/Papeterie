/**
 * 
 */
package fr.eni.papeterie.bo;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 21 juil. 2020
 */
public abstract class Article {
	private Integer idArticle;
	private String reference;
	private String marque;
	private String designation;
	private Float prixUnitaire;
	private Integer qteStock;

	public Article() {
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
	public Article(Integer idArticle, String marque, String reference, String designation, float prixUnitaire,
			int qteStock) {
		super();
		setIdArticle(idArticle);
		setMarque(marque);
		setReference(reference);
		setDesignation(designation);
		setPrixUnitaire(prixUnitaire);
		setQteStock(qteStock);

	}

	/**
	 * @param reference
	 * @param marque
	 * @param designation
	 * @param prixUnitaire
	 * @param qteStock
	 */
	public Article(String marque, String reference, String designation, float prixUnitaire, int qteStock) {
		super();
		setMarque(marque);
		setReference(reference);
		setDesignation(designation);
		setPrixUnitaire(prixUnitaire);
		setQteStock(qteStock);
	}

	/**
	 * @return the idArticle
	 */
	public Integer getIdArticle() {
		return idArticle;
	}

	/**
	 * @param idArticle the idArticle to set
	 */
	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the prixUnitaire
	 */
	public Float getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @return the qteStock
	 */
	public Integer getQteStock() {
		return qteStock;
	}

	/**
	 * @param qteStock the qteStock to set
	 */
	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", marque=" + marque + ", reference=" + reference + ", designation="
				+ designation + ", prixUnitaire=" + prixUnitaire + ", qteStock=" + qteStock + "]";
	}

}
