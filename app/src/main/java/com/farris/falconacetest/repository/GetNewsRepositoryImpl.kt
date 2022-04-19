package com.farris.falconacetest.repository

import com.farris.falconacetest.ext.checkIsSuccessful
import com.farris.falconacetest.ext.requireBody
import com.farris.falconacetest.service.NewsService
import com.farris.falconacetest.service.exception.EmptyBodyException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

class GetNewsRepositoryImpl(
    private val newsService: NewsService
) : GetNewsRepository {

    override suspend fun invoke(): Result<List<RepositoryNews>> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val response = newsService.getNews()
                    .checkIsSuccessful()
                    .requireBody()

                response.vector?.items?.mapNotNull { data ->
                    val createTime = data.extra?.created?.let {
                        Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"), Locale.TAIWAN)
                            .apply {
                                timeInMillis = TimeUnit.SECONDS.toMillis(it)
                            }
                    }
                    RepositoryNews(
                        type = data.type ?: return@mapNotNull null,
                        title = data.title ?: "",
                        source = data.source ?: "",
                        ref = data.ref ?: "",
                        style = data.style ?: "",
                        mainTitle = data.appearance?.mainTitle ?: "",
                        subTitle = data.appearance?.subTitle ?: "",
                        thumbnail = data.appearance?.thumbnail ?: "",
                        subscript = data.appearance?.subscript ?: "",
                        createdTime = createTime,
                        description = data.extra?.description ?: "",
                        section = data.meta?.section ?: return@mapNotNull null,
                        category = data.meta.category ?: emptyList(),
                        insideItems = emptyList()
                    )
                } ?: throw EmptyBodyException()
            }
        }
    }
}