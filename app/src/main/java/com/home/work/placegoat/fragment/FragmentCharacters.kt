package com.home.work.placegoat.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.home.work.placegoat.R
import com.home.work.placegoat.ServesRic
import com.home.work.placegoat.date.remote.CharacterApi
import com.home.work.placegoat.pojo.ServesCharacterOrigin
import com.home.work.placegoat.receyler.RecyclerAdapter
import kotlinx.coroutines.*

class FragmentCharacters : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var job: Job

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recView)
        updateNews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateNews() {
        val apiService = CharacterApi().create(CharacterApi::class.java)
        job = GlobalScope.launch(Dispatchers.Main) {
            try {
                val characterResponse: ServesRic = apiService.getCharacterAsync().await()
                recyclerView.adapter = RecyclerAdapter(characterResponse.results)
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                recyclerView.itemAnimator = DefaultItemAnimator()
            } catch (e: Exception) {
                Log.e("Error", "Method updateNews: $e")
                Toast.makeText(context, "Нет подключения к сети", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDetach() {
        job.cancelChildren()
        super.onDetach()
    }
}

