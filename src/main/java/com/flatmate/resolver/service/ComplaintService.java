package com.flatmate.resolver.service;

import com.flatmate.resolver.dto.ComplaintRequest;
import com.flatmate.resolver.dto.VoteRequest;
import com.flatmate.resolver.model.Complaint;
import com.flatmate.resolver.model.Flat;
import com.flatmate.resolver.model.Karma;
import com.flatmate.resolver.model.User;
import com.flatmate.resolver.repository.ComplaintRepository;
import com.flatmate.resolver.repository.FlatRepository;
import com.flatmate.resolver.repository.KarmaRepository;
import com.flatmate.resolver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private KarmaRepository karmaRepository;

    public Complaint createComplaint(ComplaintRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flat flat = flatRepository.findByFlatCode(request.getFlatCode())
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        Complaint complaint = Complaint.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .complaintType(request.getComplaintType())
                .severityLevel(request.getSeverityLevel())
                .timestamp(LocalDateTime.now())
                .flat(flat)
                .createdBy(user)
                .build();

        return complaintRepository.save(complaint);
    }

    public String voteComplaint(VoteRequest request) {
        Complaint complaint = complaintRepository.findById(request.getComplaintId())
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        if (request.isUpvote()) {
            complaint.setUpvotes(complaint.getUpvotes() + 1);
        } else {
            complaint.setDownvotes(complaint.getDownvotes() + 1);
        }

        complaintRepository.save(complaint);
        return ("Vote registered successfully!");
    }

    public String resolveComplaint(Long complaintId, String userEmail) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setResolved(true);
        complaintRepository.save(complaint);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Karma karma = karmaRepository.findByUser(user);
        if (karma == null) {
            karma = new Karma();
            karma.setUser(user);
        }
        karma.setKarmaPoints(karma.getKarmaPoints() + 10);
        karmaRepository.save(karma);
        return "Complaint marked as resolved!";
    }

    public List<Complaint> getAllActiveComplaints() {
        return complaintRepository.findByResolvedFalse();
    }

    public List<Complaint> getTrendingComplaints() {
        Pageable topComplaints = PageRequest.of(0, 5, Sort.by("upvotes").descending());
        return complaintRepository.findByOrderByUpvotesDesc(topComplaints);
    }

    public List<Karma> getLeaderboard() {
        return karmaRepository.findTop10ByOrderByKarmaPointsDesc();
    }

    public Map<String, Long> getFlatComplaintStats(String flatCode) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalComplaints", complaintRepository.countByFlat_FlatCode(flatCode));
        stats.put("resolvedComplaints", complaintRepository.countByFlat_FlatCodeAndResolvedTrue(flatCode));
        stats.put("pendingComplaints", complaintRepository.countByFlat_FlatCodeAndResolvedFalse(flatCode));

        return stats;
    }
}
