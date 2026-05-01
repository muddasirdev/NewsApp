package com.codewithmuddasir.newsapp.di

import android.content.Context
import androidx.room.Room
import com.codewithmuddasir.newsapp.data.api.NewsApi
import com.codewithmuddasir.newsapp.data.db.ArticleDao
import com.codewithmuddasir.newsapp.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    @Singleton
    fun provideDao(db: NewsDatabase): ArticleDao = db.articleDao()
}