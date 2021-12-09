package com.example.pixabayapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.pixabayapp.databinding.OneResultItemBinding
import com.example.pixabayapp.domain.models.SearchHit

class HitsAdapter(glide: RequestManager) :
    RecyclerView.Adapter<HitsAdapter.HitsViewHolder>() {
    private var list = emptyList<SearchHit>()

    private var glideInst: RequestManager? = null

    init {
        glideInst = glide
    }

    inner class HitsViewHolder(val binding: OneResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding.root) {
                setOnClickListener {
                    val clickedItem = list[layoutPosition]
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsViewHolder {
        return HitsViewHolder(
            OneResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HitsViewHolder, position: Int) {
        with(holder) {
//            binding.titleTv.text = list[position].user
            glideInst?.load(list[position].largeImageURL)
                ?.override(100, 100)
                ?.diskCacheStrategy(DiskCacheStrategy.DATA)
                ?.thumbnail(0.5f)
                ?.fitCenter()
                ?.apply(RequestOptions().override(700, 400))
                ?.into(binding.imageIv)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<SearchHit>) {
        this.list = list
        notifyDataSetChanged()

    }
}
