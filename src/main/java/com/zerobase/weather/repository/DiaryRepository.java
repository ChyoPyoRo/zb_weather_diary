package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, UUID> {

    Optional<Diary> findFirstByDate(LocalDate date);

    Optional<List<Diary>> findAllByDateBetween(LocalDate dateBefore, LocalDate dateAfter);

    void deleteAllByDate(LocalDate date);
}
