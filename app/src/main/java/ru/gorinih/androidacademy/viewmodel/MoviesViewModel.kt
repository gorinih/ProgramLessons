package ru.gorinih.androidacademy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.gorinih.androidacademy.data.Movies
import ru.gorinih.androidacademy.data.repository.MoviesInteractor
import kotlin.coroutines.CoroutineContext

class MoviesViewModel(private val moviesInteractor: MoviesInteractor) : ViewModel() {

    private val exceptionMovie =
        CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
            val isActive = coroutineContext.isActive
            Log.e(
                "MoviesViewModel::class",
                "ExceptionHandler [Scope active:$isActive, canceledContext:$throwable]"
            )
        }

    private var _movieList = MutableLiveData<List<Movies>>()
    val movieList: LiveData<List<Movies>>
        get() = _movieList

    private var _movie = MutableLiveData<Movies.Movie>()
    val movie: LiveData<Movies.Movie>
        get() = _movie

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch(exceptionMovie) {
            val movies = mutableListOf<Movies>(Movies.Header)
            val newMoviesList = moviesInteractor.getMovies()
            movies.addAll(newMoviesList)
            _movieList.value = movies
        }
    }
}