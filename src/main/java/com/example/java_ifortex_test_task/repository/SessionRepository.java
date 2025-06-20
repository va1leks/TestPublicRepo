package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query(value = " SELECT * FROM sessions " +
            " WHERE device_type = :deviceType " +
            " ORDER BY started_at_utc LIMIT 1 ", nativeQuery = true)
    Session getFirstDesktopSession(@Param("deviceType")int deviceType);

    @Query(value = " SELECT s FROM sessions s " +
            " JOIN s.user u " +
            " WHERE s.endedAtUtc <:endDate " +
            " AND u.deleted = false " +
            " ORDER BY s.endedAtUtc DESC", nativeQuery = true)
    List<Session> getSessionsFromActiveUsersEndedBefore2025(@Param("endDate") LocalDateTime endDate);
}