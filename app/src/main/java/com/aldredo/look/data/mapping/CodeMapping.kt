package com.aldredo.look.data.mapping

import com.aldredo.look.data.model.CodeModel
import com.aldredo.look.domain.dto.CodeDto

object CodeMapping {
    fun mappingCodeToDto(codeModel: CodeModel?): CodeDto {
        return CodeDto(codeModel?.result?._id ?: "")
    }
}