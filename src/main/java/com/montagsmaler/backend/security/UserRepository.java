package com.montagsmaler.backend.security;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<UserEntity, UUID> {
    UserEntity findExampleUserEntityByUserName(String userName);
    UserEntity findUserEntityByEmail(String email);
}
