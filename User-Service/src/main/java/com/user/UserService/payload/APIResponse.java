package com.user.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse {
    private String message;
    private Boolean success;
    private HttpStatus status;
}
