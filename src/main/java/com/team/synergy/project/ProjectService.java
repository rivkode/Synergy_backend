package com.team.synergy.project;

import com.team.synergy.apply.ApplyService;
import com.team.synergy.exception.AppException;
import com.team.synergy.exception.ErrorCode;
import com.team.synergy.member.Member;
import com.team.synergy.member.MemberService;
import com.team.synergy.project.dto.ProjectDto;
import com.team.synergy.project.dto.request.CreateProjectRequest;
import com.team.synergy.project.dto.response.CreateProjectResponse;
import com.team.synergy.project.dto.response.InfoProjectResponse;
import com.team.synergy.project.dto.response.ListInfoProjectResponse;
import com.team.synergy.project.dto.response.ProjectGetResponse;
import com.team.synergy.projectmember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberService projectMemberService;
    private final MemberService memberService;
    private final ApplyService applyService;


    @Transactional
    public CreateProjectResponse createProject(Member member, CreateProjectRequest request) {
        Project project = request.toEntity();
        Project savedProject = projectRepository.save(project);
        projectMemberService.createProjectMember(project, member);

        return CreateProjectResponse.from(savedProject);
    }

    public Project findProjectById(Long id) {
        Optional<Project> project = this.projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "프로젝트가 없습니다.");
        }
    }

    @Transactional
    public void deleteProject(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (projectOptional.isPresent()) {
            List<String> memberIds = projectMemberService.getProjectMemberIds(projectId);
            List<Member> members = memberService.findMembersByMemberIds(memberIds);

            for (Member m : members) {
                projectMemberService.deleteProjectMember(projectOptional.get(), m);
                applyService.deleteApply(m, projectOptional.get());
            }

            projectRepository.delete(projectOptional.get());
        } else {
            throw new AppException(ErrorCode.INVALID_DATA, "프로젝트가 존재하지 않습니다");
        }
    }

    public List<ProjectDto> findByKeyword(String keyword) {
        System.out.println("keyword = " + keyword);
        List<Project> search = projectRepository.search(keyword);
        System.out.println("search size = " + search.size());

        return ProjectDto.fromList(search);
    }

    public Page<ProjectGetResponse> getProjects(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        Page<ProjectGetResponse> projectGetResponses = ProjectGetResponse.toResponse(projects);

        return projectGetResponses;
    }

    public Page<ProjectGetResponse> searchProjects(Pageable pageable, String keyword) {
        Page<Project> projects = projectRepository.findByNameContaining(keyword, pageable);
        Page<ProjectGetResponse> projectGetResponses = ProjectGetResponse.toResponse(projects);

        return projectGetResponses;
    }

    public InfoProjectResponse projectInfo(Long projectId) {
        Project project = findProjectById(projectId);
            List<String> projectMemberIds = projectMemberService.getProjectMemberIds(projectId);
        return InfoProjectResponse.from(project, projectMemberIds);
    }

    public ListInfoProjectResponse getProjectsByMember(String memberId) {

        List<Long> projectIds = projectMemberService.findProjectIdsByMemberId(memberId);
        List<InfoProjectResponse> infoProjectResponses = new ArrayList<>();

        for (int i = 0; i < projectIds.size(); i++) {
            infoProjectResponses.add(projectInfo(projectIds.get(i)));
        }

        return new ListInfoProjectResponse(infoProjectResponses);
    }
}
