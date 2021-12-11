package com.example.pixabayapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.pixabayapp.databinding.OneResultItemBinding
import com.example.pixabayapp.domain.models.SearchHit

class HitsAdapter(
    glide: RequestManager,
    onClickItemListener: OnClickItemListener,
    context: Context
) :
    RecyclerView.Adapter<HitsAdapter.HitsViewHolder>() {
    private var list = emptyList<SearchHit>()

    private var glideInst: RequestManager? = null
    private var onClickItemListener: OnClickItemListener? = null
    private var layoutInflater: LayoutInflater? = null

    init {
        glideInst = glide
        this.layoutInflater = LayoutInflater.from(context)
        this.onClickItemListener = onClickItemListener
    }

    interface OnClickItemListener {
        fun onClick(searchHit: SearchHit)
    }

    inner class HitsViewHolder(val binding: OneResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding.root) {
                setOnClickListener {
                    val clickedItem = list[layoutPosition]
                    onClickItemListener?.onClick(clickedItem)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsViewHolder {
        return HitsViewHolder(
            OneResultItemBinding.inflate(checkNotNull(layoutInflater), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HitsViewHolder, position: Int) {
        with(holder) {
            glideInst?.load(list[position].largeImageURL)?.into(binding.imageIv)
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
