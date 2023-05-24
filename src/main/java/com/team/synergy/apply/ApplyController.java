package com.team.synergy.apply;

import com.team.synergy.generic.Result;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberDto;
import com.team.synergy.member.MemberRepository;
import com.team.synergy.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/testApply")
    public ResponseEntity<String> apply(@RequestParam("memberId") Long memberId,
                                        @RequestParam("projectId") Long projectId) {
        Long apply = applyService.apply(memberId, projectId);
        Apply getApply = applyRepository.findById(apply).get();
        return ResponseEntity.ok().body("신청 성공");
    }

    @GetMapping("/search/{id}")
    public Result getApply(@PathVariable("id") Long id) {
        return new Result(applyService.getApplyDto(id));
    }

    @GetMapping("/search/memberId/{id}")
    public Result getProject(@PathVariable("id") Long memberId) {
        return new Result(applyService.getApplyByMemberId(memberId));
    }
}
