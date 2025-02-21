package com.flatmate.resolver.dto;

import com.flatmate.resolver.model.ComplaintType;
import com.flatmate.resolver.model.SeverityLevel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintRequest {
    private String title;
    private String description;
    private ComplaintType complaintType;
    private SeverityLevel severityLevel;
    private String flatCode;
}
