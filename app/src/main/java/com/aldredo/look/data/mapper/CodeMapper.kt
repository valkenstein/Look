package com.aldredo.look.data.mapper

import com.aldredo.look.data.model.CodeModel
import com.aldredo.look.domain.dto.CodeDto

object CodeMapper {
    fun mappingCodeToDto(codeModel: CodeModel?, cookie: String?): CodeDto {
        return CodeDto(codeModel?.result?._id ?: "", cookie ?: "")
    }
}