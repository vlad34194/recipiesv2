package ro.siit.recipiesv2.model;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface RecipiesRepository extends JpaRepository<Recipies, Long> {
    List<Recipies> findAllByCategoryOrderByLastModified(Category category);

    List<Recipies> findAll();

}
