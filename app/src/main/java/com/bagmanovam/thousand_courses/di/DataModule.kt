package com.bagmanovam.thousand_courses.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bagmanovam.thousand_courses.data.db.ThousandCoursesDatabase
import com.bagmanovam.thousand_courses.data.internet.CourseApi
import com.bagmanovam.thousand_courses.data.reposittory.CourseApiRepositoryImpl
import com.bagmanovam.thousand_courses.data.reposittory.CourseDbRepositoryImpl
import com.bagmanovam.thousand_courses.domain.interactor.GetCoursesInteractor
import com.bagmanovam.thousand_courses.domain.interactor.GetFavouriteCoursesInteractor
import com.bagmanovam.thousand_courses.domain.interactor.RequestCoursesInteractor
import com.bagmanovam.thousand_courses.domain.interactor.SaveCoursesInteractor
import com.bagmanovam.thousand_courses.domain.interactor.SetFavouriteStatusInteractor
import com.bagmanovam.thousand_courses.domain.interactor.SortByPublishDateInteractor
import com.bagmanovam.thousand_courses.domain.reposittory.CourseApiRepository
import com.bagmanovam.thousand_courses.domain.reposittory.CourseDbRepository
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.GetFavouriteCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.RequestCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SaveCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SetFavouriteStatusUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SortByPublishDateUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


val dataModule = module {

//    single<CourseRepository> { CourseRepositoryImpl(get()) }
    singleOf(::CourseDbRepositoryImpl).bind<CourseDbRepository>()
    singleOf(::CourseApiRepositoryImpl).bind<CourseApiRepository>()

//    single<GetCoursesUseCase> { GetCoursesInteractor(get()) }
    singleOf(::GetCoursesInteractor).bind<GetCoursesUseCase>()
    singleOf(::SaveCoursesInteractor).bind<SaveCoursesUseCase>()
    single<SetFavouriteStatusUseCase> { SetFavouriteStatusInteractor(get()) }
    single<GetFavouriteCoursesUseCase> { GetFavouriteCoursesInteractor(get()) }
    single<RequestCoursesUseCase> { RequestCoursesInteractor(get()) }
    single<SortByPublishDateUseCase> { SortByPublishDateInteractor(get()) }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://drive.usercontent.google.com/")
            .build()
            .create(CourseApi::class.java)
    }

    single {
        Room.databaseBuilder(
            get(),
            ThousandCoursesDatabase::class.java,
            "thousand_courses_db"
        )
            .fallbackToDestructiveMigration(true)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d("Room", "onCreate: ")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.d("Room", "onOpen: ${db.path}")
                }

                override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                    super.onDestructiveMigration(db)
                    Log.d("Room", "onDestructiveMigration: ${db.version}")
                }
            })
            .setQueryCallback(
                { sqlQuery, bindArgs ->
                    Log.d("Room", "SQL Query: $sqlQuery SQL Args: $bindArgs")
                }, Executors.newSingleThreadExecutor()
            ).build()
    }

    single {
        get<ThousandCoursesDatabase>().getDao()
    }
}