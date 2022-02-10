package agenda.persistence;

import java.util.List;

import agenda.model.Category;

public interface ICategory {
    public Category ajouter(Category e);
    public List<Category> listCategories();
    public Category obtenir(int id);
    public Category modification(int id, Category e);
    public void supprimer(int id);
}
