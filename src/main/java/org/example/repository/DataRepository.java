package org.example.repository;

import org.example.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data,Long> {
}
