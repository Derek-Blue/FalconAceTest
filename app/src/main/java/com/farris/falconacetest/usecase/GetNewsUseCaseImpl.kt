package com.farris.falconacetest.usecase

import com.farris.falconacetest.repository.GetNewsRepository

class GetNewsUseCaseImpl(
    private val repository: GetNewsRepository
) : GetNewsUseCase {

    override suspend fun invoke(): Result<Map<String, List<UseCaseNews>>> {

        return repository().map { repository ->
            val allCategory = repository.mapNotNull {
                if (it.type == FilterType.Divider.title && it.title.isNotEmpty()) {
                    it.title
                } else {
                    return@mapNotNull null
                }
            }

            repository.mapNotNull {
                if (it.type == FilterType.News.title && allCategory.contains(it.section)) {
                    UseCaseNews(
                        type = it.type,
                        title = it.mainTitle,
                        source = it.source,
                        ref = it.ref,
                        style = it.style,
                        mainTitle = it.mainTitle,
                        subTitle = it.subTitle,
                        thumbnail = it.thumbnail,
                        subscript = it.subscript,
                        createdTime = it.createdTime,
                        description = it.description,
                        section = it.section,
                        category = it.category
                    )
                } else {
                    return@mapNotNull null
                }
            }.groupBy { it.section }
        }
    }
}