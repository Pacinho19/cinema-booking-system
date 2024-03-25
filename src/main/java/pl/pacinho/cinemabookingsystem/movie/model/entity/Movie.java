package pl.pacinho.cinemabookingsystem.movie.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pacinho.cinemabookingsystem.category.model.entity.Category;
import pl.pacinho.cinemabookingsystem.screening.model.entity.Screening;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;
    @NotNull
    private String briefDescription;
    @NotNull
    private String description;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private int duration;

    private String imgUrl;

    @NotNull
    private String alias;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private Set<Screening> screenings;
}
