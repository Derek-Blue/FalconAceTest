package com.farris.falconacetest.repository

import com.farris.falconacetest.ext.checkIsSuccessful
import com.farris.falconacetest.ext.requireBody
import com.farris.falconacetest.service.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class GetNewsRepositoryImpl(
    private val newsService: NewsService
) : GetNewsRepository {

    override suspend fun invoke(): Result<List<RepositoryItem>> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                newsService.getNews()
                    .checkIsSuccessful()
                    .requireBody()
            }
                .map { response ->
                    response.vector?.items?.mapNotNull { data ->
                        when (FilterType.fromType(data.type ?: "")) {
                            FilterType.Divider -> {
                                RepositoryItem.Divider(
                                    data.title ?: return@mapNotNull null
                                )
                            }
                            FilterType.News -> {
                                val createdTime = data.extra?.created?.let {
                                    Calendar.getInstance(
                                        TimeZone.getTimeZone("Asia/Taipei"),
                                        Locale.TAIWAN
                                    )
                                        .apply {
                                            timeInMillis = it * 1000
                                        }
                                }
                                RepositoryItem.News(
                                    data.source ?: "",
                                    data.ref ?: "",
                                    data.appearance?.mainTitle ?: "",
                                    data.appearance?.subTitle ?: "",
                                    data.appearance?.thumbnail ?: "",
                                    data.appearance?.subscript ?: "",
                                    createdTime,
                                    data.extra?.description ?: "",
                                    data.meta?.section ?: return@mapNotNull null
                                )
                            }
                            else -> {
                                return@mapNotNull null
                            }
                        }
                    } ?: emptyList()
                }
        }

    }
}