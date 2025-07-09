package com.group7.swp391.drug_prevention.domain.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResConsultantDTO {
    private String firstName;
    private String lastName;
    private String avatar;
    private long consultantId;


}
