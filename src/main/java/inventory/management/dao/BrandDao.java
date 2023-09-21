package inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.management.entity.Brand;

public interface BrandDao extends JpaRepository<Brand, Long> {

}
