package com.example.easydictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easydictionary.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MeaningAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val word = binding.search.text.toString()
            getMeaning(word)
        }
        adapter= MeaningAdapter(emptyList())
        binding.meaningRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.meaningRecyclerView.adapter = adapter
    }

    private fun getMeaning(word: String) {
         setInProgress(true)

        GlobalScope.launch {
            val response = RetrofitInstance.dictionaryApi.getMeaning(word)
//            Log.i("Response from API",response.body().toString())
            runOnUiThread {
                setInProgress(false)

                response.body()?.first()?.let {
                    setUI(it)
                }
            }
        }
    }

        // method to show word and phonetics
    private fun setUI(response : WordResult){
        binding.word.text = response.word
            binding.phonetic.text = response.phonetic
            adapter.updateNewData(response.meanings)
    }

        // setting the ui in setInProgress function

        private fun setInProgress(inProgress: Boolean){
               if(inProgress){
                   binding.btn.visibility = View.INVISIBLE
                   binding.progressBar.visibility = View.VISIBLE
               }else{
                   binding.progressBar.visibility = View.INVISIBLE
                   binding.btn.visibility = View.VISIBLE

               }
        }

    }
