package org.sva.hello.controller

import org.springframework.web.bind.annotation.*
import org.sva.hello.dto.AverageHealthStatus
import org.sva.hello.entity.HealthRecord
import org.sva.hello.entity.Profile
import org.sva.hello.repository.HealthRecordRepository
import org.sva.hello.repository.ProfileRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ProfileController(val healthRecordRepository: HealthRecordRepository, val profileRepository: ProfileRepository) {

    @PostMapping("/profile")
    fun save(@RequestBody profile: Profile): Mono<Profile> = profileRepository.save(profile)

    @GetMapping("/profile")
    fun getProfile(): Flux<Profile> = profileRepository.findAll()

    @PostMapping("/health/record")
    fun saveHealthStatus(@RequestBody healthRecord: HealthRecord) =
        healthRecordRepository.save(healthRecord)

    @GetMapping("/health/{profileId}/avg")
    fun fetchHealthRecordAverage(@PathVariable("profileId") profileId: Long): Mono<AverageHealthStatus> {
        val averageHealthStatus = AverageHealthStatus(0,0.0,0.0,0.0)

        return healthRecordRepository.findByProfileId(profileId).reduce(averageHealthStatus
        ) { acc: AverageHealthStatus, p: HealthRecord ->
            acc.bloodPressure += p.bloodPressure
            acc.cnt++
            acc.heartRate += p.heartRate
            acc.temperature += p.temperature
            acc
        }.map { acc: AverageHealthStatus ->
            acc.temperature = acc.temperature / acc.cnt
            acc.heartRate = acc.heartRate / acc.cnt
            acc.bloodPressure = acc.bloodPressure / acc.cnt
            acc
        }
    }

}