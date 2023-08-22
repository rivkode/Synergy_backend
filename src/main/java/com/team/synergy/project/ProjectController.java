package com.team.synergy.project;

import com.team.synergy.generic.Result;
import com.team.synergy.project.dto.ProjectDto;
import com.team.synergy.project.dto.response.ProjectGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<String> projectCreate(@RequestBody ProjectDto projectDto) {
        this.projectService.projectCreate(projectDto);
        return ResponseEntity.ok().body("프로젝트 생성 성공");
    }

    @GetMapping("/{id}")
    public Result getProject(@PathVariable("id") Long id) {
        return new Result(projectService.findProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> projectDelete(@PathVariable("id") Long projectId) {
        this.projectService.projectDelete(projectId);
        return ResponseEntity.ok().body("프로젝트 삭제 성공");
    }

    @GetMapping("/recent")
    public ResponseEntity<Page<ProjectGetResponse>> getProjectList(@PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProjectGetResponse> projectGetResponses = projectService.getProjects(pageable);

        return ResponseEntity.ok()
                .body(projectGetResponses);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProjectGetResponse>> searchProjectList(@PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        Page<ProjectGetResponse> projectGetResponses = projectService.searchProjects(pageable, keyword);

        return ResponseEntity.ok()
                .body(projectGetResponses);
    }
}
