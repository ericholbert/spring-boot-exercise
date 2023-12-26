package ericholbert.springbootexercise.artwork;

import ericholbert.springbootexercise.artwork.exception.ArtworkNotFoundException;
import ericholbert.springbootexercise.artwork.exception.InvalidSortPropertyException;
import ericholbert.springbootexercise.artwork.exception.MissingParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    public Page<Artwork> getAll(String institution, String author, String date, String materials, Pageable pageable) {
        Example<Artwork> example = Example.of(new Artwork(null, institution, null, author, null, date, materials, null));
        try {
            return artworkRepository.findAll(example, pageable);
        } catch (PropertyReferenceException e) {
            throw new InvalidSortPropertyException(e.getPropertyName());
        }
    }

    public Artwork getOne(Long id) {
        return artworkRepository.findById(id).orElseThrow(() -> new ArtworkNotFoundException(id));
    }

    public Artwork getOne(String institution, String invNum) {
        return artworkRepository.findByInstitutionAndInvNum(institution, invNum).orElseThrow(() -> new ArtworkNotFoundException(institution, invNum));
    }

    public Artwork save(Artwork artwork, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new MissingParametersException(artwork);
        }
        return artworkRepository.save(artwork);
    }

    public Artwork update(Long id, Artwork newArtwork) {
        return artworkRepository.findById(id)
                .map(oldArtwork -> {
                    Optional.ofNullable(newArtwork.getInstitution()).ifPresent(oldArtwork::setInstitution);
                    Optional.ofNullable(newArtwork.getInvNum()).ifPresent(oldArtwork::setInvNum);
                    Optional.ofNullable(newArtwork.getAuthor()).ifPresent(oldArtwork::setAuthor);
                    Optional.ofNullable(newArtwork.getTitle()).ifPresent(oldArtwork::setTitle);
                    Optional.ofNullable(newArtwork.getDate()).ifPresent(oldArtwork::setDate);
                    Optional.ofNullable(newArtwork.getMaterials()).ifPresent(oldArtwork::setMaterials);
                    Optional.ofNullable(newArtwork.getDimensions()).ifPresent(oldArtwork::setDimensions);
                    return artworkRepository.save(oldArtwork);
                })
                .orElseGet(() -> {
                    newArtwork.setId(id);
                    return artworkRepository.save(newArtwork);
                });
    }

    public void delete(Long id) {
        artworkRepository.deleteById(id);
    }

    boolean artworkExists(Long id) {
        return artworkRepository.existsById(id);
    }
}
