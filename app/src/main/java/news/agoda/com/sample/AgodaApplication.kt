package news.agoda.com.sample

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class AgodaApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }


}