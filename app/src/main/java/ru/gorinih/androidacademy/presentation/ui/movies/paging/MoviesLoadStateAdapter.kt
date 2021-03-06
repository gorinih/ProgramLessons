package ru.gorinih.androidacademy.presentation.ui.movies.paging


import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class MoviesLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MoviesLoadStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesLoadStateViewHolder {
        return MoviesLoadStateViewHolder.newInstance(parent, retry)
    }

    override fun onBindViewHolder(holder: MoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}