package ru.gorinih.androidacademy.data.db


import androidx.paging.PagingSource
import androidx.room.*
import ru.gorinih.androidacademy.data.models.*

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdateMovies(items: List<Movies.Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(items: List<Genre>)

    @Insert
    suspend fun insertGenresOfMovie(items: List<RelationGenresOfMovie>)

    @Insert
    suspend fun insertActorsOfMovie(items: List<RelationActorsOfMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(items: List<Actor>)

    /*

    @Insert
    suspend fun insertActorsOfMovie(items: List<RelationActorsOfMovie>)

    @Query(
        "SELECT * FROM movies WHERE id NOT IN (SELECT id_movie FROM tmp_id_movies) LIMIT 20"
    )
    suspend fun loadMoviesFromDB(): List<Movies.Movie>
    */
    @Query(
        "SELECT * FROM movies ORDER BY id_db"
    )
    fun loadMoviesFromDB(): PagingSource<Int, Movies.Movie>

    @Query(
        "SELECT * FROM genres WHERE id IN (SELECT id_genre FROM relation_genres_of_movie WHERE id_movie = :idMovie)"
    )
    suspend fun loadGenresById(idMovie: Int): List<Genre>?

    @Query(
        "SELECT * FROM MOVIES WHERE id = :idMovie LIMIT 1"
    )
    suspend fun loadMovieFromDB(idMovie: Int): Movies.Movie?

    @Query(
        "SELECT * FROM actors WHERE id IN (SELECT id_actor FROM relation_actors_of_movie WHERE id_movie = :idMovie)"
    )
    suspend fun loadActorsById(idMovie: Int): List<Actor>?

    @Query(
        "DELETE FROM movies"
    )
    suspend fun clearMovies()
/*
    @Query(
        "SELECT * FROM actors WHERE id IN (SELECT id_actor FROM relation_actors_of_movie WHERE id_movie = :id_movie)"
    )
    suspend fun loadActorsById(id_movie: Int): List<Actor>?


    //--------tmp-------//

    @Insert
    suspend fun insertIdMovie(id_movies: List<TmpIdMovies>)

    @Query("DELETE FROM tmp_id_movies")
    suspend fun deleteIdMovie()
    */
}

