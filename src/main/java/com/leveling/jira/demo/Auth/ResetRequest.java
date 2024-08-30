package com.leveling.jira.demo.Auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResetRequest {

    private String newPassword;

}
