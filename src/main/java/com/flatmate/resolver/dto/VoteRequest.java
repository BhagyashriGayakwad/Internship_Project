package com.flatmate.resolver.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteRequest {
    private Long complaintId;
    private boolean upvote;
}
