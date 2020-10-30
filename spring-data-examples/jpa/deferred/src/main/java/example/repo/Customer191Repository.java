package example.repo;

import example.model.Customer191;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface Customer191Repository extends CrudRepository<Customer191, Long> {

	List<Customer191> findByLastName(String lastName);
}
