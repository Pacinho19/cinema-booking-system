package pl.pacinho.cinemabookingsystem.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.cinemabookingsystem.category.model.entity.Category;

@Repository
interface CategoryJpaRepository extends CategoryRepository, JpaRepository<Category, Integer> {
}
