package com.esprit.unibackend.payload.request;

import jakarta.websocket.server.ServerEndpoint;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidateStatistics {
    private long totalCandidates;


}
