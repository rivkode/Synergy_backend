package com.team.synergy.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListInfoProjectResponse {
    private List<InfoProjectResponse> infoProjectResponses;

    public static ListInfoProjectResponse from(List<InfoProjectResponse> infoProjectResponses) {
        return new ListInfoProjectResponse(infoProjectResponses);
    }
}
