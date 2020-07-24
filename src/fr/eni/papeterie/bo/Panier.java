/**
 * 
 */
package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 21 juil. 2020
 */
public class Panier {
	private float montant;
	private List<Ligne> lignesPanier;

	public Panier() {
		lignesPanier = new ArrayList<Ligne>();
	}

	/**
	 * @return the montant
	 */
	public float getMontant() {
		return montant;
	}

	/**
	 * @return the lignesPanier
	 */
	public List<Ligne> getLignesPanier() {
		return lignesPanier;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Ligne getLigne(int index) {
		return lignesPanier.get(index);
	}

	/**
	 * 
	 * @param article
	 * @param qte
	 */
	public void addLigne(Article article, int qte) {
		this.lignesPanier.add(new Ligne(article, qte));
		montant += article.getPrixUnitaire() * qte;
	}

	/**
	 * 
	 * @param index
	 * @param newQte
	 */
	public void updateLigne(int index, int newQte) {
		montant += this.lignesPanier.get(index).getPrix() * (newQte - this.lignesPanier.get(index).getQte());
		this.getLigne(index).setQte(newQte);
	}

	/**
	 * 
	 * @param index
	 */
	public void removeLigne(int index) {
		montant -= this.lignesPanier.get(index).getPrix() * this.lignesPanier.get(index).getQte();
		this.lignesPanier.remove(index);

	}

	@Override
	public String toString() {
		StringBuilder affichage = new StringBuilder();
		for (Ligne ligne : this.lignesPanier) {
			affichage.append("Ligne " + this.lignesPanier.indexOf(ligne) + " :   " + ligne);
			affichage.append("\n");
		}
		affichage.append("\n");
		affichage.append("Valeur du panier : ");
		affichage.append(String.format("%.2f", this.getMontant()));
		return affichage.toString();
	}

}
