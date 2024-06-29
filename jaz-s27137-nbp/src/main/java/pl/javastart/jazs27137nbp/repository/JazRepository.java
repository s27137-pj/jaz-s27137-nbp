package pl.javastart.jazs27137nbp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.jazs27137nbp.model.Waluta;

@Repository
public interface JazRepository extends JpaRepository<Waluta, Long> {

}
