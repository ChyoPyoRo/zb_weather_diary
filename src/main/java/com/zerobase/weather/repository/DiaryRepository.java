package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, UUID> {
}
