package com.flatmate.resolver.repository;

import com.flatmate.resolver.model.Complaint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByResolvedFalse();
    List<Complaint> findByUpvotesGreaterThanEqual(int upvotes);

    @Query("SELECT c FROM Complaint c ORDER BY c.upvotes DESC")
    List<Complaint> findTopTrending(Pageable pageable);
    List<Complaint> findByOrderByUpvotesDesc(Pageable pageable);


    long countByFlat_FlatCode(String flatCode);
    long countByFlat_FlatCodeAndResolvedTrue(String flatCode);
    long countByFlat_FlatCodeAndResolvedFalse(String flatCode);
}
