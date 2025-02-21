package com.flatmate.resolver.repository;

import com.flatmate.resolver.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByResolvedFalse();
    List<Complaint> findByUpvotesGreaterThanEqual(int upvotes);
}
