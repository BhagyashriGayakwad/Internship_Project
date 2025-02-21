package com.flatmate.resolver.controller;

import com.flatmate.resolver.dto.ComplaintRequest;
import com.flatmate.resolver.dto.VoteRequest;
import com.flatmate.resolver.model.Complaint;
import com.flatmate.resolver.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping
    public Complaint createComplaint(@RequestBody ComplaintRequest request, Authentication authentication) {
        return complaintService.createComplaint(request, authentication.getName());
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllActiveComplaints();
    }

    @PostMapping("/{id}/vote")
    public Complaint voteComplaint(@RequestBody VoteRequest request) {
       return complaintService.voteComplaint(request);
    }

    @PutMapping("/{id}/resolve")
    public void resolveComplaint(@PathVariable Long id, Authentication authentication) {
        complaintService.resolveComplaint(id, authentication.getName());
    }
}
