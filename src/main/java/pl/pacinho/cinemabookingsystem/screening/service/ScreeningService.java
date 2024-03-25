package pl.pacinho.cinemabookingsystem.screening.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningDto;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningSeatStatusDto;
import pl.pacinho.cinemabookingsystem.screening.model.dto.ScreeningWithMoveAndSeatsDto;
import pl.pacinho.cinemabookingsystem.screening.model.mapper.ScreeningMapper;
import pl.pacinho.cinemabookingsystem.screening.repository.ScreeningRepository;
import pl.pacinho.cinemabookingsystem.screening.seat.model.enums.SeatState;
import pl.pacinho.cinemabookingsystem.screening.tools.ScreeningSeatPlanGenerator;
import pl.pacinho.cinemabookingsystem.screening.seat.model.entity.ScreeningSeat;
import pl.pacinho.cinemabookingsystem.screening.seat.repository.ScreeningSeatRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final ScreeningSeatRepository screeningSeatRepository;

    public List<ScreeningDto> findAllByMovieWithRoom(int id) {
        return screeningRepository.findAllByMovieIdWithFetchRoom(id)
                .stream()
                .map(ScreeningMapper::convertToDtoWithRoom)
                .collect(Collectors.toList());
    }

    public boolean existsById(int screeningId) {
        return screeningRepository.existsById(screeningId);
    }

    public ScreeningSeatStatusDto[][] findScreeningSeatsStatus(int screeningId) {
        return screeningRepository.findByIdWithFetchRoom(screeningId)
                .map(screening -> {
                    List<ScreeningSeat> screeningSeats = screeningSeatRepository.findAllByScreeningIdAndStateIn(screeningId, List.of(SeatState.UNAVAILABLE, SeatState.RESERVED));
                    return ScreeningSeatPlanGenerator.generate(screening, screeningSeats);
                })
                .orElseThrow(() -> new EntityNotFoundException("Could not find screening with given id: " + screeningId));
    }

    public ScreeningWithMoveAndSeatsDto findScreeningWithSeats(int screeningId) {
        return screeningRepository.findByIdWithFetchRoomAndMovie(screeningId)
                .map(screening -> {
                    List<ScreeningSeat> screeningSeats = screeningSeatRepository.findAllByScreeningIdAndStateIn(screeningId, List.of(SeatState.UNAVAILABLE, SeatState.RESERVED));
                    return ScreeningMapper.convertToDtoWithSeats(screening, screeningSeats);
                })
                .orElseThrow(() -> new EntityNotFoundException("Could not find screening with given id: " + screeningId));
    }
}
