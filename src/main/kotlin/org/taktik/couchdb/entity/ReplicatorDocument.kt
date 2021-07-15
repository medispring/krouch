/*
 *    Copyright 2020 Taktik SA
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package org.taktik.couchdb.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder
import org.taktik.couchdb.CouchDbDocument
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class ReplicatorDocument(
        @JsonProperty("_id") override val id: String,
        @JsonProperty("_rev") override val rev: String?,
        val source: String? = null,
        val target: String? = null,
        val owner: String? = null,
        val create_target: Boolean = false,
        val continuous: Boolean = false,
        val doc_ids: List<String>? = null,
        @JsonProperty("_replication_state") val replicationState: String,
        @JsonProperty("_replication_state_time") val replicationStateTime: String,
        @JsonProperty("_replication_stats") val replicationStats: ReplicationStats,
        @JsonProperty("rev_history") override val revHistory: Map<String, String>? = null
) : CouchDbDocument {
    override fun withIdRev(id: String?, rev: String) = id?.let { this.copy(id = it, rev = rev) } ?: this.copy(rev = rev)
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class ReplicationStats(
        @JsonProperty("revisions_checked") val revisionsChecked: Int? = null,
        @JsonProperty("missing_revisions_found") val missingRevisionsFound: Int? = null,
        @JsonProperty("docs_read") val docsRead: Int? = null,
        @JsonProperty("docs_written") val docsWritten: Int? = null,
        @JsonProperty("changes_pending") val changesPending: Int? = null,
        @JsonProperty("doc_write_failures") val docWriteFailures: Int? = null,
        @JsonProperty("checkpointed_source_seq") val checkpointedSourceSeq: String? = null,
) : Serializable
