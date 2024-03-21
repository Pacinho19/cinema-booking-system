package pl.pacinho.cinemabookingsystem.movie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.pacinho.cinemabookingsystem.movie.model.dto.MovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.dto.NewMovieDto;
import pl.pacinho.cinemabookingsystem.movie.model.entity.Movie;
import pl.pacinho.cinemabookingsystem.movie.service.MovieService;
import pl.pacinho.cinemabookingsystem.screening.service.ScreeningService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/movie")
@RestController
public class MovieController {

    private final MovieService movieService;
    private final ScreeningService screeningService;

    @GetMapping
    ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok(
                movieService.findAll()
        );
    }

    //Only for testing
    @PostMapping
    ResponseEntity<?> createMovie(@RequestBody NewMovieDto movieDto) {
        Movie save = movieService.save(movieDto);
        if (save == null || save.getId() == null)
            return ResponseEntity.badRequest()
                    .build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();

        return ResponseEntity.created(
                location
        ).build();
    }

    @GetMapping("/{movieId}/screening")
    ResponseEntity<?> getScreening(@PathVariable("movieId") int movieId) {
        if (!movieService.existsById(movieId))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(
                screeningService.findAllByMovieWithRoom(movieId)
        );
    }
}
