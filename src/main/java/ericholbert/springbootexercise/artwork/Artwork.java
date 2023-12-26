package ericholbert.springbootexercise.artwork;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="artworks")
public class Artwork {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String institution;
    @NotNull
    private String invNum;
    private String author;
    private String title;
    private String date;
    private String materials;
    private String dimensions;

    @JsonIgnore
    public List<String> getNullAttributes() {
        List<String> l = new ArrayList<>();
        if (this.institution == null) {
            l.add("institution");
        }
        if (this.invNum == null) {
            l.add("invNum");
        }
        return l;
    }
}
