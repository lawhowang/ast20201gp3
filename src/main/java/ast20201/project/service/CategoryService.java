package ast20201.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ast20201.project.model.Category;
import ast20201.project.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    public Category getCategory(long id) {
        return categoryRepository.getCategory(id);
    }

    public void updateCategories(List<Category> categoryList) {
        categoryRepository.updateCategories(categoryList);
    }
}
