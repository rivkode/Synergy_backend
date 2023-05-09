package com.team.synergy.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReository extends JpaRepository<Post, Long> {
}
