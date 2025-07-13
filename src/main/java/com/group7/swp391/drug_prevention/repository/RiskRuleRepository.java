package com.group7.swp391.drug_prevention.repository;

import com.group7.swp391.drug_prevention.domain.RiskRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskRuleRepository extends JpaRepository<RiskRule, Long> {
    @Query("SELECT r FROM RiskRule r WHERE :score BETWEEN r.minScore AND r.maxScore")
    List<RiskRule> findByScoreBetween(@Param("score") long score);

    @Query("SELECT r FROM RiskRule r WHERE :score BETWEEN r.minScore AND r.maxScore AND :testId = r.test.id")
    List<RiskRule> findByScoreBetweenByTestId(@Param("score") long score, @Param("testId") long testId);

}
