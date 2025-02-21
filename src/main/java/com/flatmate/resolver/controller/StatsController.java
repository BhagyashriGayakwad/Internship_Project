package com.flatmate.resolver.controller;

import com.flatmate.resolver.model.Karma;
import com.flatmate.resolver.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private ComplaintService complaintService;


    @GetMapping("/leaderboard")
    public ResponseEntity<List<Karma>> getLeaderboard() {
        return ResponseEntity.ok(complaintService.getLeaderboard());
    }

    @GetMapping("/flat/{flatCode}/stats")
    public ResponseEntity<Map<String, Long>> getFlatStats(@PathVariable String flatCode) {
        return ResponseEntity.ok(complaintService.getFlatComplaintStats(flatCode));
    }
}
