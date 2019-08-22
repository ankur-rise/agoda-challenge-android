package news.agoda.com.sample.ui.utils

import news.agoda.com.sample.data.models.NewsDataModel


    fun getImageUrlFromResult(result: NewsDataModel?): String? {
        var url: String? = null
        if (result != null) {
            val media = result.multimedia
            if (media != null && !media.isEmpty()) {
                for (multiMediumDataModel in media) {
                    if (multiMediumDataModel.format == "Normal") {
                        url = multiMediumDataModel.url
                        break
                    }
                }
            }
        }
        return url
    }


