package ericholbert.springbootexercise.artwork;

import lombok.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArtworkAssembler implements RepresentationModelAssembler<Artwork, EntityModel<Artwork>> {

    @Override
    public @NonNull EntityModel<Artwork> toModel(@NonNull Artwork artwork) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        EntityModel<Artwork> entityModel = EntityModel.of(
                artwork,
                linkTo(methodOn(ArtworkController.class).getOne(artwork.getId())).withSelfRel(),
                linkTo(methodOn(ArtworkController.class).getOne(artwork.getInstitution(), artwork.getInvNum())).withRel("selfByParameters")
        );
        if (auth.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            entityModel.add(
                    linkTo(methodOn(ArtworkController.class).save(artwork, null)).withRel("create"),
                    linkTo(methodOn(ArtworkController.class).update(artwork.getId(), artwork)).withRel("update"),
                    linkTo(methodOn(ArtworkController.class).delete(artwork.getId())).withRel("delete")
            );
        }
        return entityModel;
    }

    @Override
    public @NonNull CollectionModel<EntityModel<Artwork>> toCollectionModel(@NonNull Iterable<? extends Artwork> artworks) {
        return RepresentationModelAssembler.super.toCollectionModel(artworks);
    }
}
