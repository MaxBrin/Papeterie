/**
 * 
 */
package fr.eni.papeterie.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

	// Attributs de classe pour les JTextfield
	private static JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;

	// Attributs de classe pour les JRadioButton
	private static JRadioButton rbTypeRamette, rbTypeStylo;

	// Attributs de classe pour les JCheckBox
	private static JCheckBox ck80Grammes, ck100Grammes;

	// Attributs de classe pour les JComboBox
	private static JComboBox<String> cbCouleur;

	// Attributs d'instance pour les JButton
	private JButton btnBack, btnNew, btnSave, btnDelete, btnForward;

	// Attributs d'instance pour les JPanel
	private JPanel panelType, panelGrammage, panelBoutons;

	// Constructeur
	public Ecran() {
		super("Papeterie");
		this.setSize(new Dimension(450, 450));
		this.setLocationRelativeTo(null);
		myPanel();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// JTextField

	public static JTextField getTxtReference() {
		if (txtReference == null) {
			txtReference = new JTextField(20);
		}
		return txtReference;
	}

	public static JTextField getTxtDesignation() {
		if (txtDesignation == null) {
			txtDesignation = new JTextField(20);
		}
		return txtDesignation;
	}

	public static JTextField getTxtMarque() {
		if (txtMarque == null) {
			txtMarque = new JTextField(20);
		}
		return txtMarque;
	}

	public static JTextField getTxtStock() {
		if (txtStock == null) {
			txtStock = new JTextField(20);
		}
		return txtStock;
	}

	public static JTextField getTxtPrix() {
		if (txtPrix == null) {
			txtPrix = new JTextField(20);
		}
		return txtPrix;
	}

	// JRadioButton

	public static JRadioButton getRbTypeRamette() {
		if (rbTypeRamette == null) {
			rbTypeRamette = new JRadioButton("Ramette");
			rbTypeRamette.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getCbCouleur().setEnabled(false);
					getCk100Grammes().setEnabled(true);
					getCk80Grammes().setEnabled(true);
				}
			});
		}
		return rbTypeRamette;
	}

	public static JRadioButton getRbTypeStylo() {
		if (rbTypeStylo == null) {
			rbTypeStylo = new JRadioButton("Stylo");
			rbTypeStylo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getCbCouleur().setEnabled(true);
					getCk100Grammes().setEnabled(false);
					getCk80Grammes().setEnabled(false);
				}
			});
		}
		return rbTypeStylo;
	}

	// JCheclBox

	public static JCheckBox getCk80Grammes() {
		if (ck80Grammes == null) {
			ck80Grammes = new JCheckBox("80 grammes");
		}
		return ck80Grammes;
	}

	public static JCheckBox getCk100Grammes() {
		if (ck100Grammes == null) {
			ck100Grammes = new JCheckBox("100 grammes");
		}
		return ck100Grammes;
	}

	// JComboBox

	public static JComboBox<String> getCbCouleur() {
		if (cbCouleur == null) {
			String[] couleurs = { "bleu", "rouge", "noir", "vert" };
			cbCouleur = new JComboBox<String>(couleurs);
		}
		return cbCouleur;
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
			panelBoutons.setLayout(new FlowLayout());
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
		panel.add(getTxtReference(), gbc);

		// Ligne 2
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Désignation"), gbc);

		gbc.gridx = 1;
		panel.add(getTxtDesignation(), gbc);

		// Ligne 3
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Marque"), gbc);

		gbc.gridx = 1;
		panel.add(getTxtMarque(), gbc);

		// Ligne 4
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel("Stock"), gbc);

		gbc.gridx = 1;
		panel.add(getTxtStock(), gbc);

		// Ligne 5
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(new JLabel("Prix"), gbc);

		gbc.gridx = 1;
		panel.add(getTxtPrix(), gbc);

		// Ligne 6
		gbc.gridx = 0;
		gbc.gridy = 5;
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
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(getPanelBoutons(), gbc);
		this.setContentPane(panel);

	}

	/**
	 * Permet d'afficher un message d'erreur à l'utilisateur
	 * 
	 * @param message
	 */
	public void messageErreur(String message) {
		JOptionPane.showMessageDialog(Ecran.this, message, "", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Afficher un nouvel article qui est par default une ramette de 80gr
	 */
	public void afficherNouveau() {
		afficheArticle(new Ramette("", "", "", 0.0f, 0, 80));
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
		// On met l'id de l'article à null car il n'est pas rentré par l'utilisateur
		// mais il est géré par l'appli
		article.setIdArticle(null);
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
