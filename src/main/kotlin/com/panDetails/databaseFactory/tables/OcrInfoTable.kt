package com.panDetails.databaseFactory.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import com.panDetails.databaseFactory.timeStamp

const val VAR_LENGTH = 1500

object OcrInfoTable : UUIDTable("ocr_info") {
    val userInfoId = uuid("user_info_id")
    val attempts = integer("attempts")
    val docFileId = uuid("doc_file_id")
    val isManuallyVerified = bool("is_manually_verified")
    val createdAt = timeStamp("created_at")
    val updatedAt = timeStamp("updated_at")
    val createdBy = varchar("created_by",VAR_LENGTH)
    val panNumber = varchar("pan_number",VAR_LENGTH)
    val panName = varchar("pan_name",VAR_LENGTH)
    val dateOfBirth = varchar("date_of_birth",VAR_LENGTH)
    val status = varchar("status",VAR_LENGTH)
    val updatedBy = varchar("updated_by",VAR_LENGTH)
}

