package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Evenement,Long> {

    @Query("select d from Evenement d where d.id=:id ")
    public Evenement findEventById(@Param("id") Long id);

    @Query("select d from Evenement d where d.title=:title ")
    public Evenement findEventByTitle(@Param("title") String title);
}
