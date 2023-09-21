package inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.management.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
