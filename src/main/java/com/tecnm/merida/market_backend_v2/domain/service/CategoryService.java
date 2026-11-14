package com.tecnm.merida.market_backend_v2.domain.service;

import com.tecnm.merida.market_backend_v2.domain.Category;
import com.tecnm.merida.market_backend_v2.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> update(int categoryId, Category category) {
        return getCategory(categoryId).map(current -> {
            category.setCategoryId(categoryId);
            return categoryRepository.save(category);
        });
    }

    public boolean delete(int categoryId) {
        return getCategory(categoryId).map(category -> {
            categoryRepository.delete(categoryId);
            return true;
        }).orElse(false);
    }
}
