package com.example.tarifkitabiv2.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tarifkitabiv2.databinding.ChildItemBinding
import com.example.tarifkitabiv2.fragments.ListFragmentDirections

class ChildAdapter():
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {
    private var yemekList= emptyList<Yemek>()
    class ChildViewHolder(val binding: ChildItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(ChildItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return yemekList.size
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem=yemekList[position]
        holder.binding.childIsimTextView.text=childItem.isim
        holder.binding.childLogoImageView.setImageBitmap(childItem.yemekFoto)
        holder.binding.childConstraintLayout.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToYemekFragment(childItem)
            Navigation.findNavController(it).navigate(action)

        }
        //notifyDataSetChanged()

    }
    // her ıtem degısımde child recyclerviewı guncelleyen fonk
    fun setData(yemekList:List<Yemek>){
        this.yemekList=yemekList
        notifyDataSetChanged()
    }

}