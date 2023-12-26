package ericholbert.springbootexercise.artwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    Optional<Artwork> findByInstitutionAndInvNum(String institution, String invNum) throws IllegalArgumentException;
}
