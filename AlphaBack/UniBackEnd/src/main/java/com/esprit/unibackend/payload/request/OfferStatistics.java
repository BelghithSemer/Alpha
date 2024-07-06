package com.esprit.unibackend.payload.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfferStatistics {

    private int offerId;
    private String company;
    private String description;
    private long applicationCount;
}
