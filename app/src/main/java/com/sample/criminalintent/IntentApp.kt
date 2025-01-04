package com.sample.criminalintent

import android.app.Application
import androidx.room.Room
import com.sample.criminalintent.data.IntentDao
import com.sample.criminalintent.data.IntentDatabase
import com.sample.criminalintent.ui.viewmodel.IntentViewModel
import com.sample.criminalintent.usecase.GetIntentByIdFromDbUseCase
import com.sample.criminalintent.usecase.GetIntentsFromDbUseCase
import com.sample.criminalintent.usecase.RemoveIntentsFromDbUseCase
import com.sample.criminalintent.usecase.SaveIntentsToDbUseCase
import com.sample.criminalintent.usecase.UpdateIntentsInDbUseCase
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

class IntentApp : Application(), DIAware {
    override val di by DI.lazy{

        import(androidXModule(this@IntentApp))

        bind<IntentDatabase>() with singleton {
            Room.databaseBuilder(instance<Application>(), IntentDatabase::class.java, "intents.db")
                .build()
        }

        bind<IntentDao>() with singleton {
            instance<IntentDatabase>().intentDao()
        }

        bind<IntentLocalDataSource>() with singleton {
            IntentLocalDataSource(instance())
        }

        // Repository
        bind<IntentRepository>() with singleton {
            IntentRepositoryImpl(instance())
        }

        // Use Cases
        bind<GetIntentsFromDbUseCase>() with singleton { GetIntentsFromDbUseCase(instance()) }
        bind<GetIntentByIdFromDbUseCase>() with singleton { GetIntentByIdFromDbUseCase(instance()) }
        bind<SaveIntentsToDbUseCase>() with singleton { SaveIntentsToDbUseCase(instance()) }
        bind<RemoveIntentsFromDbUseCase>() with singleton { RemoveIntentsFromDbUseCase(instance()) }
        bind<UpdateIntentsInDbUseCase>() with singleton { UpdateIntentsInDbUseCase(instance()) }

        bind<IntentViewModel>() with singleton { IntentViewModel(instance()) }
    }

}