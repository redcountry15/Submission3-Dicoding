package com.example.submisi3_made_dicoding.favorite


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submisi3_made_dicoding.Adapter.MovieAdapter
import com.example.submisi3_made_dicoding.Model.Movie

import com.example.submisi3_made_dicoding.R
import com.example.submisi3_made_dicoding.db.MovieHelper
import kotlinx.android.synthetic.main.fragment_movie_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFavorite : Fragment() {


    private lateinit var mvAdapter: MovieAdapter
    lateinit var movieHelper: MovieHelper
    lateinit var fav: ArrayList<Movie>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieHelper = MovieHelper.getInstance(context!!)
        movieHelper.open()
        fav = movieHelper.getAllMovie()
        mvAdapter = MovieAdapter(fav)

        rv_favorite_movie.apply {
            adapter = mvAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            mvAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Movie) {
                    val intent = Intent(context, DetailMovieFavorite::class.java)
                    intent.putExtra(DetailMovieFavorite.DATA_MOVIE_FAVORITE,data)
                    startActivity(intent)
                }
            })
        }
    }
    override fun onResume() {
        super.onResume()
        fav.clear()
        fav.addAll(movieHelper.getAllMovie())
        rv_favorite_movie.adapter?.notifyDataSetChanged()
    }
}
