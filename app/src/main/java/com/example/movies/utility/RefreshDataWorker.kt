package com.example.movies.utility

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.movies.movies.repository.MoviesRepo
import retrofit2.HttpException
import javax.inject.Inject

class RefreshDataWorker @Inject constructor(appContext:Context, params: WorkerParameters, private val repo: MoviesRepo):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            repo.returnDataFromApi()
            repo.returnDataFromApi()
            Result.success()
        }catch (e: HttpException){
            Result.retry()
        }
    }
}