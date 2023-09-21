package inventory.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.management.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Long> {

}
