package com.example.marteterrenos.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marteterrenos.R
import com.example.marteterrenos.databinding.ItemStateBinding
import com.example.marteterrenos.model.local.MarsData

class StateAdapter (
    var states : MutableList<MarsData>,
    private var listener : OnCLickListenerState,
    private var fragment: StateFragment
) : RecyclerView.Adapter<StateAdapter.VH>() {

    private  var mContext = fragment.requireContext()


    inner class VH(view : View):RecyclerView.ViewHolder(view){
        val mBinding = ItemStateBinding.bind(view)

        fun setListener(marsData : MarsData){
            with(mBinding.root){
                setOnClickListener { listener.onClick(marsData.id) }
            }
        }
    }

    fun updateCount(newState : List<MarsData>){
        states.clear()
        states.addAll(newState)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        mContext = parent.context
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_state,parent,false)
        return VH(view)
    }

    override fun getItemCount(): Int = states.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val state = states[position]
        val imgUrl = state.img

        with(holder){
            mBinding.tvType.text = "Tipo: ${state.type}"
            setListener(state)
            Glide.with(mContext)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_img)
                .error(R.drawable.ic_not_image)
                .into(mBinding.imgState)
        }
    }
}