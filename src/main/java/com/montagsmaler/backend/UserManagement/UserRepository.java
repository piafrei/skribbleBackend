package com.montagsmaler.backend.UserManagement;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<UserEntity, UUID> {
    UserEntity findExampleUserEntityByUserName(String userName);
}
