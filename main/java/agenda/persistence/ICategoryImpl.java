package agenda.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agenda.model.Category;

public class ICategoryImpl implements ICategory{
	
    Connection conn = ConnexionToDb.getConnexion();

	@Override
	public Category ajouter(Category category) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into Category(nom) values(?)");
			ps.setString(1, category.getNom());
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Category> listCategories() {
		List<Category> categories = new ArrayList<Category>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from category");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category p = new Category();
				p.setIdCategory(rs.getInt("idCategory"));
				p.setNom(rs.getString("nom"));
				categories.add(p);								
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public Category obtenir(int id) {
		Category c = new Category();
		try {
			PreparedStatement ps = conn.prepareStatement("select idCategory, nom from category where idCategory = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				c.setIdCategory(rs.getInt("idCategory"));
				c.setNom(rs.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Category modification(int id, Category e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(int id) {
	       try {
			PreparedStatement ps = conn.prepareStatement("delete from category where idCategory = ?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
