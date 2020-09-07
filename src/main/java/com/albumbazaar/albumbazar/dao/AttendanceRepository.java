package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
    
}
