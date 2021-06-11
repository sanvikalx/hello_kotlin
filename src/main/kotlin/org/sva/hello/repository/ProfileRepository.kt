package org.sva.hello.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import org.sva.hello.entity.Profile

@Repository
interface ProfileRepository: ReactiveCrudRepository<Profile, Long>