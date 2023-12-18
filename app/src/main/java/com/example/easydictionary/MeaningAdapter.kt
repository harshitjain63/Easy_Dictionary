package com.example.easydictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easydictionary.databinding.ActivityMainBinding
import com.example.easydictionary.databinding.MeaningRecyclerRowBinding

class MeaningAdapter(private var meaningList : List<Meaning> ) : RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>(){

    class MeaningViewHolder(private val binding: MeaningRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: Meaning){
            binding.partOfSpeech.text = meaning.partOfSpeech
            binding.definitions.text = meaning.definitions.joinToString("\n\n") {
               var currentindex = meaning.definitions.indexOf(it)
                (currentindex+1).toString()+". "+it.definition.toString()
            }

            if (meaning.synonyms.isEmpty()){
                binding.synonymsTitles.visibility = View.GONE
                binding.synonyms.visibility = View.GONE
            }else{
                binding.synonymsTitles.visibility = View.VISIBLE
                binding.synonyms.visibility = View.VISIBLE
                binding.synonyms.text = meaning.synonyms.joinToString(" , ")

            }
            if (meaning.antonyms.isEmpty()){
                binding.antonymsTitles.visibility = View.GONE
                binding.antonyms.visibility = View.GONE
            }else{
                binding.antonymsTitles.visibility = View.VISIBLE
                binding.antonyms.visibility = View.VISIBLE
                binding.antonyms.text = meaning.synonyms.joinToString(" , ")

            }
        }
    }

    fun updateNewData(newMeaningList : List<Meaning>){
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = MeaningRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
       holder.bind(meaningList[position])
    }
}