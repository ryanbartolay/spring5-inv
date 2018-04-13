package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    
//    public ObservableList<Category> getCategories();
//    public Category getCategory(long id);
//    public void saveCategory(Category category);
//    public void updateCategory(Category category);
//    public void deleteCategory(Category category);
//    public ObservableList<String> getTypes();
}
