package ericholbert.springbootexercise.artwork;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;
    @Autowired
    private ArtworkAssembler artworkAssembler;
    @Autowired
    private PagedResourcesAssembler<Artwork> pagedResourcesAssembler;

    @GetMapping("/api/v0/artworks")
    public ResponseEntity<PagedModel<EntityModel<Artwork>>> getAll(@RequestParam(required=false) String institution,
                                                                   @RequestParam(required=false) String author,
                                                                   @RequestParam(required=false) String date,
                                                                   @RequestParam(required=false) String materials,
                                                                   Pageable pageable) {
        if (pageable.getSort() == Sort.unsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "institution", "invNum"));
        }
        Page<Artwork> page = artworkService.getAll(institution, author, date, materials, pageable);
        PagedModel<EntityModel<Artwork>> pagedModel = pagedResourcesAssembler.toModel(page, artworkAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/api/v0/artworks/{id}")
    public ResponseEntity<EntityModel<Artwork>> getOne(@PathVariable Long id) {
        EntityModel<Artwork> entityModel = artworkAssembler.toModel(artworkService.getOne(id));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/api/v0/artworks/{institution}/{invNum}")
    public ResponseEntity<EntityModel<Artwork>> getOne(@PathVariable String institution, @PathVariable String invNum) {
        EntityModel<Artwork> entityModel = artworkAssembler.toModel(artworkService.getOne(institution, invNum));
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping("/api/admin/v0/artworks")
    public ResponseEntity<EntityModel<Artwork>> save(@Valid @RequestBody Artwork artwork, BindingResult errors) {
        EntityModel<Artwork> entityModel = artworkAssembler.toModel(artworkService.save(artwork, errors));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/api/admin/v0/artworks/{id}")
    public ResponseEntity<EntityModel<Artwork>> update(@PathVariable Long id, @RequestBody Artwork artwork) {
        EntityModel<Artwork> entityModel = artworkAssembler.toModel(artworkService.update(id, artwork));
        if (artworkService.artworkExists(id)) {
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        } else {
            return ResponseEntity.ok(entityModel);
        }
    }

    @DeleteMapping("/api/admin/v0/artworks/{id}")
    public ResponseEntity<EntityModel<Artwork>> delete(@PathVariable Long id) {
        artworkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
