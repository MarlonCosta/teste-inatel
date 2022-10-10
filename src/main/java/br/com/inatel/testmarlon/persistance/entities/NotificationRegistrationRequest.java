package br.com.inatel.testmarlon.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRegistrationRequest {
    private String host;
    private Integer port;
}
