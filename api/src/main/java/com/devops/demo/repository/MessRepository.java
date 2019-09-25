package com.devops.demo.repository;

import com.devops.demo.entity.Mess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessRepository extends JpaRepository<Mess,Long> {
}
