package org.taktik.couchdb.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.pozo.KotlinBuilder
import org.taktik.couchdb.handlers.ZonedDateTimeDeserializer
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class SchedulerJobs(
        val jobs: List<ScheduledJob>,
        @JsonProperty("total_rows") val totalRows: Number,
        val offset: Number,
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @KotlinBuilder
    data class ScheduledJob(
            val id: String,
            @JsonProperty("doc_id") val docId: String,
            val database: String,
            val pid: String,
            val source: String,
            val target: String,
            val user: String,
            val info: JobInfo,
            val history: List<JobHistory>,
            val node: String,
            @JsonProperty("start_time")
            @JsonDeserialize(using = ZonedDateTimeDeserializer::class)
            val startTime: ZonedDateTime
    ) {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonIgnoreProperties(ignoreUnknown = true)
        @KotlinBuilder
        data class JobInfo(
                @JsonProperty("changes_pending") val changesPending: Number? = 0,
                @JsonProperty("checkpointed_source_seq") val checkpointedSourceSeq: String,
                @JsonProperty("doc_write_failures") val docWriteFailures: Number? = 0,
                @JsonProperty("docs_read") val docsRead: Number? = 0,
                @JsonProperty("docs_written") val docsWritten: Number? = 0,
                @JsonProperty("missing_revisions_found") val missingRevisionsFound: Number? = 0,
                @JsonProperty("revisions_checked") val revisionsChecked: Number? = 0,
                @JsonProperty("source_seq") val sourceSeq: String,
                @JsonProperty("through_seq") val throughSeq: String
        )

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonIgnoreProperties(ignoreUnknown = true)
        @KotlinBuilder
        data class JobHistory(
                @JsonDeserialize(using = ZonedDateTimeDeserializer::class)
                val timestamp: ZonedDateTime,
                val type: String
        )
    }
}

