package org.taktik.couchdb.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class SchedulerJobs(
        val jobs: List<ScheduledJob>,
        @JsonProperty("total_rows") val totalRows: Number,
        val offset: Number,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class ScheduledJob(
        val id: String,
        @JsonProperty("doc_id") val docId: String,
        val databasse: String,
        val pid: String,
        val source: String,
        val target: String,
        val user: String? = null,
        val history: List<JobHistory>,
        val node: String,
        @JsonProperty("start_time") val startTime: LocalDateTime
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class JobHistory(
        val timestamp: LocalDateTime,
        val type: String
)