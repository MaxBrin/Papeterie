package fr.eni.papeterie.dal.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	// Constante pour le type d'article
	private static final String TYPE_STYLO = "Stylo";
	private static final String TYPE_RAMETTE = "Ramette";

	// Variable de commande sql
	private static final String sqlInsertArticle = "INSERT INTO dbo.Articles VALUES (?,?,?,?,?,?,?,?)";
	private static final String sqlSelectById = "SELECT idArticle,marque,reference,designation,prixUnitaire,qteStock,grammage,couleur,type FROM dbo.Articles WHERE idArticle = ?";
	private static final String sqlSelectAll = "SELECT idArticle,marque,reference,designation,prixUnitaire,qteStock,grammage,couleur,type FROM dbo.Articles";
	private static final String sqlUpdate = "UPDATE  dbo.Articles SET reference = ?, marque = ?,designation = ?, prixUnitaire = ?, qteStock = ?, grammage = ?, couleur = ? WHERE idArticle = ? ";
	private static final String sqlDelete = "DELETE  FROM  dbo.Articles WHERE idArticle = ?";
	// Variable de connection à la bd

	public ArticleDAOJdbcImpl() {

	}

	/**
	 * Methode pour inserer des données dans la base de donnée
	 * 
	 * @param a Article à inserer
	 * @throws DALException
	 */
	public void insert(Article a) throws DALException {
		try (PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(sqlInsertArticle,
				Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, a.getReference());
			stmt.setString(2, a.getMarque());
			stmt.setString(3, a.getDesignation());
			stmt.setFloat(4, a.getPrixUnitaire());
			stmt.setInt(5, a.getQteStock());
			if (a.getClass().getSimpleName().equals(TYPE_RAMETTE)) {
				stmt.setInt(6, ((Ramette) a).getGrammage());
				stmt.setNull(7, Types.VARCHAR);
			} else {
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, ((Stylo) a).getCouleur());
			}
			stmt.setString(8, a.getClass().getSimpleName());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				a.setIdArticle(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Insert failed - ", e);

		}
	}

	/**
	 * Methode pour récupere un article en fonction de son id dans la base de donnée
	 * 
	 * @param id idArticle de l'article à récupérer
	 * @return
	 * @throws DALException
	 */
	public Article selectById(int id) throws DALException {
		Article a = null;
		try (PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(sqlSelectById);) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("type").trim().equals(TYPE_STYLO)) {
					a = new Stylo(rs.getInt("idArticle"), rs.getString("reference").trim(), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (rs.getString("type").trim().equals(TYPE_RAMETTE)) {
					a = new Ramette(rs.getInt("idArticle"), rs.getString("reference").trim(), rs.getString("marque"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}
		} catch (SQLException e) {
			throw new DALException("SelectById failed - ", e);
		}
		return a;
	}

	/**
	 * Methode pour récupérer dans une list d'articles tous les article de la base
	 * de donnée
	 * 
	 * @return Liste d'articles
	 * @throws DALException
	 */
	public List<Article> selectAll() throws DALException {
		List<Article> listArticle = new ArrayList<Article>();
		Article a = null;
		try (Statement stmt = JdbcTools.getConnection().createStatement();) {
			ResultSet rs = stmt.executeQuery(sqlSelectAll);
			while (rs.next()) {
				if (rs.getString("type").trim().equals(TYPE_STYLO)) {
					a = new Stylo(rs.getInt("idArticle"), rs.getString("reference").trim(),
							rs.getString("marque").trim(), rs.getString("designation"), rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"), rs.getString("couleur"));
				}
				if (rs.getString("type").trim().equals(TYPE_RAMETTE)) {
					a = new Ramette(rs.getInt("idArticle"), rs.getString("reference").trim(),
							rs.getString("marque").trim(), rs.getString("designation"), rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"), rs.getInt("grammage"));
				}
				listArticle.add(a);

			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		}
		return listArticle;
	}

	/**
	 * Methode pour mettre a jour les données d'un article dans la base de donnée
	 * 
	 * @param a Article avec les données a jour
	 * @throws DALException Renvoie une exception si la méthode ne fonctionne pas
	 */
	public void update(Article a) throws DALException {
		try (PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(sqlUpdate);) {
			stmt.setString(1, a.getMarque());
			stmt.setString(2, a.getReference());
			stmt.setString(3, a.getDesignation());
			stmt.setFloat(4, a.getPrixUnitaire());
			stmt.setInt(5, a.getQteStock());
			if (a.getClass().getSimpleName().equals("Ramette")) {
				stmt.setInt(6, ((Ramette) a).getGrammage());
				stmt.setNull(7, Types.VARCHAR);
			} else {
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, ((Stylo) a).getCouleur());
			}
			stmt.setInt(8, a.getIdArticle());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update failed - ", e);
		}
	}

	/**
	 * Methode pour effacer un article, en fonction de son id, dans la base de
	 * donnée
	 * 
	 * @param id idArticle de l'article à effacer
	 * @throws DALException
	 */
	public void delete(int id) throws DALException {
		try (PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(sqlDelete);) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete failed - ", e);
		}
	}
}
