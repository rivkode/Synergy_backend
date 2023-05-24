package com.team.synergy.project;

import com.team.synergy.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<String> projectCreate(@RequestBody ProjectDto projectDto) {
        this.projectService.projectCreate(projectDto.getName(), projectDto.getContent(), projectDto.getField(), projectDto.getCreateDate(), projectDto.getEndDate());
        return ResponseEntity.ok().body("프로젝트 생성 성공");
    }

    @GetMapping("/detail/{id}")
    public Result getProject(@PathVariable("id") Long id) {
        return new Result(projectService.findById(id));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> projectDelete(@PathVariable("id") Long projectId) {
        this.projectService.projectDelete(projectId);
        return ResponseEntity.ok().body("프로젝트 삭제 성공");
    }
}
