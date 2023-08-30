package com.team.synergy.apply.dto.response;

import com.team.synergy.apply.dto.ApplyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListMyApplyResponse {
    private int count;
    private List<ApplyDto> applyDtoList;

    public static ListMyApplyResponse from(List<ApplyDto> collect) {
        return new ListMyApplyResponse(collect.size(), collect);
    }
}
