package com.team.synergy.apply;

import com.team.synergy.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping()
    public ResponseEntity<String> apply(@RequestParam("memberId") String memberId,
                                        @RequestParam("projectId") Long projectId) {
        applyService.apply(memberId, projectId);
        return ResponseEntity.ok().body("신청 성공");
    }

    @DeleteMapping()
    public ResponseEntity<String> cancelApply(@RequestParam("id") Long applyId) {
        applyService.cancelApply(applyId);
        return ResponseEntity.ok().body("신청 취소 성공");
    }

    @GetMapping("/{id}")
    public Result getApply(@PathVariable("id") Long id) {
        return new Result(applyService.getApplyDto(id));
    }

    @GetMapping("/member/{id}")
    public Result getProject(@PathVariable("id") String memberId) {
        return new Result(applyService.getApplyByMemberId(memberId));
    }
}
