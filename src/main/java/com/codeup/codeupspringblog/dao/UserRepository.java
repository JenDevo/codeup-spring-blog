package com.codeup.codeupspringblog.dao;

import com.codeup.codeupspringblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
}
