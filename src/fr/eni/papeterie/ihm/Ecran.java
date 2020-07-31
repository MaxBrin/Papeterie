/**
 * 
 */
package fr.eni.papeterie.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 28 juil. 2020
 */
public class Ecran extends JFrame {

	private static final long serialVersionUID = 2942422144839188321L;
	// Attribut JLabel
	private JLabel lblLogger;
	// Attributs de classe pour les JTextfield
	private  JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;

	// Attributs de classe pour les JRadioButton
	private  JRadioButton rbTypeRamette, rbTypeStylo;

	// Attributs de classe pour les JCheckBox
	private  JCheckBox ck80Grammes, ck100Grammes;

	// Attributs de classe pour les JComboBox
	private  JComboBox<String> cbCouleur;

	// Attributs d'instance pour les JButton
	private JButton btnBack, btnNew, btnSave, btnDelete, btnForward;

	// Attributs d'instance pour les JPanel
	private JPanel panelType, panelGrammage, panelBoutons;

	// Attributs pour les JProgressBar
	private JProgressBar pbIndexCatalogue;

	// Constructeur
	public Ecran() {
		super("Papeterie");
		this.setSize(new Dimension(450, 500));
		this.setLocationRelativeTo(null);
		myPanel();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// JLabel

	public JLabel getLblLogger (){
		if (this.lblLogger == null){
			this.lblLogger = new JLabel("");
			this.lblLogger.setForeground(Color.RED);
		}
		return this.lblLogger;
	}

	// JTextField

	public JTextField getTxtReference() {
		if (this.txtReference == null) {
			this.txtReference = new JTextField(20);
		}
		return this.txtReference;
	}

	public JTextField getTxtDesignation() {
		if (this.txtDesignation == null) {
			this.txtDesignation = new JTextField(20);
		}
		return this.txtDesignation;
	}

	public JTextField getTxtMarque() {
		if (this.txtMarque == null) {
			this.txtMarque = new JTextField(20);
		}
		return this.txtMarque;
	}

	public JTextField getTxtStock() {
		if (this.txtStock == null) {
			this.txtStock = new JTextField(20);
		}
		return this.txtStock;
	}

	public JTextField getTxtPrix() {
		if (this.txtPrix == null) {
			this.txtPrix = new JTextField(20);
		}
		return this.txtPrix;
	}

	// JRadioButton

	public  JRadioButton getRbTypeRamette() {
		if (this.rbTypeRamette == null) {
			this.rbTypeRamette = new JRadioButton("Ramette");
			this.rbTypeRamette.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getCbCouleur().setEnabled(false);
					getCk100Grammes().setEnabled(true);
					getCk80Grammes().setEnabled(true);
				}
			});
		}
		return this.rbTypeRamette;
	}

	public  JRadioButton getRbTypeStylo() {
		if (this.rbTypeStylo == null) {
			this.rbTypeStylo = new JRadioButton("Stylo");
			this.rbTypeStylo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getCbCouleur().setEnabled(true);
					getCk100Grammes().setEnabled(false);
					getCk80Grammes().setEnabled(false);
				}
			});
		}
		return this.rbTypeStylo;
	}

	// JCheclBox

	public  JCheckBox getCk80Grammes() {
		if (this.ck80Grammes == null) {
			this.ck80Grammes = new JCheckBox("80 grammes");
		}
		return this.ck80Grammes;
	}

	public  JCheckBox getCk100Grammes() {
		if (this.ck100Grammes == null) {
			this.ck100Grammes = new JCheckBox("100 grammes");
		}
		return this.ck100Grammes;
	}

	// JComboBox

	public  JComboBox<String> getCbCouleur() {
		if (this.cbCouleur == null) {
			String[] couleurs = { "bleu", "rouge", "noir", "vert" };
			this.cbCouleur = new JComboBox<String>(couleurs);
		}
		return this.cbCouleur;
	}

	// JButton

	public JButton getBtnBack() {
		if (this.btnBack == null) {
			this.btnBack = new JButton(new ImageIcon(this.getClass().getResource("Icone/Back24.gif")));
			this.btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleControleur.getInstance().back();
				}
			});
		}
		return this.btnBack;
	}

	public JButton getBtnNew() {
		if (this.btnNew == null) {
			this.btnNew = new JButton(new ImageIcon(this.getClass().getResource("Icone/New24.gif")));
			this.btnNew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleControleur.getInstance().nouveau();
				}
			});
		}
		return this.btnNew;
	}

	public JButton getBtnSave() {
		if (this.btnSave == null) {
			this.btnSave = new JButton(new ImageIcon(this.getClass().getResource("Icone/Save24.gif")));
			this.btnSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleControleur.getInstance().save();
				}
			});
		}
		return this.btnSave;
	}

	public JButton getBtnDelete() {
		if (this.btnDelete == null) {
			this.btnDelete = new JButton(new ImageIcon(this.getClass().getResource("Icone/Delete24.gif")));
			this.btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleControleur.getInstance().delete();
				}
			});
		}
		return this.btnDelete;
	}

	public JButton getBtnForward() {
		if (this.btnForward == null) {
			this.btnForward = new JButton(new ImageIcon(this.getClass().getResource("Icone/Forward24.gif")));
			this.btnForward.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticleControleur.getInstance().forward();
				}
			});
		}
		return this.btnForward;
	}

	// JProgressBar
 	public JProgressBar getPbIndexCatalogue(){
		if (this.pbIndexCatalogue == null){
			this.pbIndexCatalogue = new JProgressBar();
			this.pbIndexCatalogue.setStringPainted(true);

		}
		return this.pbIndexCatalogue;
	}
	// Panel pour RadioButton Type
	public JPanel getPanelType() {
		if (panelType == null) {
			panelType = new JPanel();
			panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
			panelType.add(getRbTypeRamette());
			panelType.add(getRbTypeStylo());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getRbTypeRamette());
			bg.add(getRbTypeStylo());
		}
		return panelType;
	}

	// Panel pour CheckBox Grammage
	public JPanel getPanelGrammage() {
		if (panelGrammage == null) {
			panelGrammage = new JPanel();
			panelGrammage.setLayout(new BoxLayout(panelGrammage, BoxLayout.Y_AXIS));
			panelGrammage.add(getCk80Grammes());
			panelGrammage.add(getCk100Grammes());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getCk80Grammes());
			bg.add(getCk100Grammes());
		}

		return panelGrammage;
	}

	// Panel pour Boutons
	public JPanel getPanelBoutons() {
		if (panelBoutons == null) {
			panelBoutons = new JPanel();
			panelBoutons.setLayout(new GridLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.ipadx = 5;
			panelBoutons.add(getBtnBack());
			panelBoutons.add(getBtnNew());
			panelBoutons.add(getBtnSave());
			panelBoutons.add(getBtnDelete());
			panelBoutons.add(getBtnForward());

		}

		return panelBoutons;
	}

	// Panel Principal
	public void myPanel() {
		// Création d'un panel
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		// Le Layout utilisé sera celui de la grille
		panel.setLayout(new GridBagLayout());
		// Et on y applique des contraintes
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 3;
		gbc.insets = new Insets(5, 5, 5, 5);

		// Ligne 1
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Référence"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(getTxtReference(), gbc);

		// Ligne 2
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(new JLabel("Désignation"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(getTxtDesignation(), gbc);

		// Ligne 3
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(new JLabel("Marque"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(getTxtMarque(), gbc);

		// Ligne 4
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(new JLabel("Stock"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(getTxtStock(), gbc);

		// Ligne 5
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(new JLabel("Prix"), gbc);

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(getTxtPrix(), gbc);

		// Ligne 6
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(new JLabel("Type"), gbc);

		gbc.gridx = 1;
		gbc.gridheight = 1;
		panel.add(getPanelType(), gbc);

		// Ligne 7
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(new JLabel("Grammage"), gbc);

		gbc.gridx = 1;
		panel.add(getPanelGrammage(), gbc);

		// Ligne 8
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel.add(new JLabel("Couleur"), gbc);

		gbc.gridx = 1;
		panel.add(getCbCouleur(), gbc);

		// Ligne 9
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(getPanelBoutons(), gbc);


		// Ligne 10
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(getLblLogger(),gbc);

		// Ligne 11
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(getPbIndexCatalogue(),gbc);
		this.setContentPane(panel);

	}


	/**
	 * Afficher un nouvel article qui est par default une ramette de 80gr
	 */
	public void afficherNouveau() {
		afficheArticle(new Ramette("", "", "", 0.0f, 0, 80));
		getRbTypeStylo().setEnabled(true);
		getRbTypeRamette().setEnabled(true);
	}

	/**
	 * Affiche dans l'appli l'article passé en paramettre
	 * 
	 * @param article
	 */
	public void afficheArticle(Article article) {
		// Mets l'attribut de l'article dans le textfield correspondant
		getTxtReference().setText(article.getReference() + "");
		getTxtDesignation().setText(article.getDesignation() + "");
		getTxtMarque().setText(article.getMarque() + "");
		getTxtStock().setText(article.getQteStock() + "");
		getTxtPrix().setText(article.getPrixUnitaire() + "");

		// Affichage en fonction de si c'est une ramette ou un stylo
		if (article instanceof Ramette) {
			// Si c'est une ramette on active les checkbox pour le grammage et on desactive
			// la couleur
			getRbTypeRamette().setEnabled(true);
			getRbTypeStylo().setEnabled(false);
			getRbTypeRamette().setSelected(true);
			getCk80Grammes().setEnabled(true);
			getCk100Grammes().setEnabled(true);
			getCk80Grammes().setSelected(((Ramette) article).getGrammage() == 80);
			getCk100Grammes().setSelected(((Ramette) article).getGrammage() == 100);
			getCbCouleur().setSelectedItem(null);
			getCbCouleur().setEnabled(false);
		} else {
			// Si c'est un crayon on active la combobox pour la couleur et on desactive le
			// grammage
			getRbTypeStylo().setEnabled(true);
			getRbTypeRamette().setEnabled(false);
			getRbTypeStylo().setSelected(true);
			getCbCouleur().setEnabled(true);
			getCbCouleur().setSelectedItem(((Stylo) article).getCouleur());
			getCk80Grammes().setEnabled(false);
			getCk100Grammes().setEnabled(false);
		}
	}

	/**
	 * Methode qui nous récupère un article rentré par l'utlisateur
	 * 
	 * @return
	 */
	public Article getArticle() {
		Article article = null;
		// On instancie arcticle en fonction de si l'utilisateur à cocher Stylo ou
		// Ramette
		if (getRbTypeStylo().isSelected()) {
			article = new Stylo();
		} else {
			article = new Ramette();
		}
		// On récupère les données rentrées par l'utilisateur dans les textfield
		article.setReference(getTxtReference().getText());
		article.setDesignation(getTxtDesignation().getText());
		article.setMarque(getTxtMarque().getText());
		article.setPrixUnitaire(Float.parseFloat(getTxtPrix().getText()));
			article.setQteStock(Integer.parseInt(getTxtStock().getText()));

		// On verifie si le combobox couleur est activé comme ça on sait que article est
		// un stylo
		if (getCbCouleur().isEnabled()) {
			// On cast article en Stylo pour récupérer la couleur
			((Stylo) article).setCouleur((String) getCbCouleur().getSelectedItem());
		} else {
			// On cast article en Ramette pour récupérer le grammage
			((Ramette) article).setGrammage(getCk80Grammes().isSelected() ? 80 : 100);
		}
		// On renvoie l'article récupéré
		return article;
	}

}
