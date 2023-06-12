package com.example.tarifkitabiv2.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarifkitabiv2.databinding.ParentItemBinding

class ParentAdapter(): RecyclerView.Adapter<ParentAdapter.MyViewHolder>() {
    lateinit var adapter: ChildAdapter
    var parentList= emptyList<ParentClass>()

    class MyViewHolder(val binding:ParentItemBinding):RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ParentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return parentList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val parentItem=parentList[position]
        holder.binding.parentTurTextView.text=parentItem.title

       // holder.binding.childRecyclerView.setHasFixedSize(true)
        holder.binding.childRecyclerView.layoutManager=GridLayoutManager(holder.binding.childRecyclerView.context,3)
        adapter=ChildAdapter()
        holder.binding.childRecyclerView.adapter=adapter
        // ıtem degısınce chıld recyclerviewı degıstırcez  child recycler viewı guncelleyoruz itemlarını eklıyoruz
        adapter.setData(parentItem.mList)



    }


    //  her ıtem degısımde parent recylerviewı güncelleyen fonk
    fun setDataParent(parentList:List<ParentClass>){
       this.parentList=parentList
        notifyDataSetChanged()
    }
}