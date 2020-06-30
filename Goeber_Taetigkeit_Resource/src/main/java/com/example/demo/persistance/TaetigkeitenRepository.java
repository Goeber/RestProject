package com.example.demo.persistance;

import com.example.demo.domain.Taetigkeit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaetigkeitenRepository extends JpaRepository<Taetigkeit, Integer> {
    @Query("select sum(s.dauer) from Taetigkeit s where s.mitarbeiter.id = :empid")
    Integer getTotalWorkingTimeByEmpID(@Param("empid") String empid);

    @Query("select t from Taetigkeit t where t.mitarbeiter.id = :empid and t.datum between :start and :end")
    List<Taetigkeit> getAllByMitarbeiter_IdAndDatumBetween(@Param("empid")String empid, @Param("start")LocalDate start,
                                                           @Param("end")LocalDate end);

    Integer countTaetigkeitsByMitarbeiter_Id(String empid);

    void deleteAllByMitarbeiter_Id(String empid);
    List<Taetigkeit> getAllByDatum(LocalDate datum);

}
