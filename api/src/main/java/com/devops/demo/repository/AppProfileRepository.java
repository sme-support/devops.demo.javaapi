package com.devops.demo.repository;

import com.devops.demo.entity.AppProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: DatVM2
 * @Date: 1/27/19
 * Time: 5:05 PM
 */
public interface AppProfileRepository extends JpaRepository<AppProfile, Long> {
    AppProfile findByEnvironment(String environment);
}
