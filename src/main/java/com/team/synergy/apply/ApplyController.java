package com.team.synergy.apply;

import com.team.synergy.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    private final ApplyRepository applyRepository;

    @PostMapping("/testApply")
    public ResponseEntity<String> apply(@RequestParam("memberId") Long memberId,
                                        @RequestParam("projectId") Long projectId) {
        Long apply = applyService.apply(memberId, projectId);
        Apply getApply = applyRepository.findById(apply).get();
        return ResponseEntity.ok().body("신청 성공");
    }

    @GetMapping("/search/{id}")
    public Result getApply(@PathVariable("id") Long id) {
        Apply getApply = applyService.findById(id);
        LocalDateTime current = LocalDateTime.now();
        ApplyDto applyDto = new ApplyDto(current, getApply.getMember(), getApply.getProject());
        return new Result(applyDto);
    }
}
