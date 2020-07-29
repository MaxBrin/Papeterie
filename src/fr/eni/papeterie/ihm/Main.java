/**
 * 
 */
package fr.eni.papeterie.ihm;

import javax.swing.SwingUtilities;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 28 juil. 2020
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ArticleControleur.getInstance().start();
			}
		});

	}

}
