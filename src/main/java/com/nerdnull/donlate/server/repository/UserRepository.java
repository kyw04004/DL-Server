package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
