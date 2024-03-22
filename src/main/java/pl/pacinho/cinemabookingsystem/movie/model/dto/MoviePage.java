package pl.pacinho.cinemabookingsystem.movie.model.dto;

import java.util.List;

public record MoviePage (List<MovieDto> movies, long currentPage, long totalElements, long totalPages, long size) {
}

