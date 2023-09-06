package com.team.synergy.project;

import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.dto.request.CreateProjectRequest;
import com.team.synergy.project.dto.response.CreateProjectResponse;
import com.team.synergy.project.dto.response.InfoProjectResponse;
import com.team.synergy.project.dto.response.ListInfoProjectResponse;
import com.team.synergy.project.dto.response.ProjectGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<CreateProjectResponse> createProject(HttpServletRequest servletRequest, @RequestBody CreateProjectRequest request) {
        String memberId = memberService.findMemberIdByToken(servletRequest);
        Member member = memberService.findMemberById(memberId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(member, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoProjectResponse> getProject(@PathVariable("id") Long projectId) {
        return ResponseEntity.ok()
                .body(projectService.projectInfo(projectId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/recent")
    public ResponseEntity<Page<ProjectGetResponse>> getProjectList(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProjectGetResponse> projectGetResponses = projectService.getProjects(pageable);

        return ResponseEntity.ok()
                .body(projectGetResponses);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProjectGetResponse>> searchProjectList(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String keyword) {
        Page<ProjectGetResponse> projectGetResponses = projectService.searchProjects(pageable, keyword);

        return ResponseEntity.ok()
                .body(projectGetResponses);
    }

    @GetMapping
    public ResponseEntity<ListInfoProjectResponse> getProjectsByMember(@Param("memberId") String memberId) {
        return ResponseEntity.ok()
                .body(projectService.getProjectsByMember(memberId));
    }
}
