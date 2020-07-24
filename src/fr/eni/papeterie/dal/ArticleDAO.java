/**
 * 
 */
package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

/**
 * @author Maxime Brin
 * @version
 * @dateDeCréation 23 juil. 2020
 */
public interface ArticleDAO {
	// Insérer un nouvel article dans la BD
	public void insert(Article a) throws DALException;

	// Selectionner un article par son idArticle
	public Article selectById(int id) throws DALException;

	// Selectionner tous les articles
	public List<Article> selectAll() throws DALException;

	// Modifier un article connu dans la BD
	public void update(Article a) throws DALException;

	// Supprimer un article de la BD
	public void delete(int id) throws DALException;

}
