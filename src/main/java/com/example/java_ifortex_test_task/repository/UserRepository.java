package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.DeviceType;
import com.example.java_ifortex_test_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value =" SELECT DISTINCT u.* FROM user u " +
            " JOIN sessions s ON u.id = s.user_id" +
            " WHERE s.device_type = :deviceTypeCode" +
            " ORDER BY s.started_at_utc DESC", nativeQuery = true)
    List<User> getUsersWithAtLeastOneMobileSession(int deviceType);

    @Query(value = " SELECT u.* FROM users u" +
            " JOIN sessions s ON u.id = s.user_id" +
            " GROUP BY u.id" +
            " ORDER BY COUNT(s.id) DESC" +
            " LIMIT 1", nativeQuery = true)
    User getUserWithMostSessions();
}
