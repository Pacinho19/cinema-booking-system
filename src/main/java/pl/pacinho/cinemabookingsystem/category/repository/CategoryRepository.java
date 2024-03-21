package pl.pacinho.cinemabookingsystem.category.repository;

import pl.pacinho.cinemabookingsystem.category.model.entity.Category;

import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findByName(String category);
}
