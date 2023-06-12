package com.example.tarifkitabiv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tarifkitabiv2.R
import com.example.tarifkitabiv2.data.*
import com.example.tarifkitabiv2.databinding.FragmentAddBinding
import com.example.tarifkitabiv2.databinding.FragmentListBinding


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding?=null
    private val binding get() = _binding!!

    private lateinit var mYemekViewModel:YemekViewModel
    var parentList=ArrayList<ParentClass>()
    var anaYemekChildClass=ArrayList<Yemek>()
    var corbaChildClass=ArrayList<Yemek>()
    var tatliChildClass=ArrayList<Yemek>()
    var icecekChildClass=ArrayList<Yemek>()
    lateinit var adapter: ParentAdapter
    lateinit var childAdapter: ChildAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        mYemekViewModel=ViewModelProvider(this).get(YemekViewModel::class.java)
        observeData()
        binding.recyclerViewParent.setHasFixedSize(true)
        binding.recyclerViewParent.layoutManager=LinearLayoutManager(requireContext())
        adapter=ParentAdapter()
        binding.recyclerViewParent.adapter=adapter


        //addData()


    }

    fun addData(yemekList:List<Yemek>){
        lateinit var yemek:Yemek
        yemekList.let {
            for (yemek in it){
                if (yemek.tur=="Ana Yemek"){
                    anaYemekChildClass.add(yemek)
                }else if (yemek.tur=="Çorba"){
                    corbaChildClass.add(yemek)
                }else if (yemek.tur=="Tatlı"){
                    tatliChildClass.add(yemek)
                }else if (yemek.tur=="İçecek"){
                    icecekChildClass.add(yemek)
                }

            }
            parentList.add(ParentClass("Ana Yemekler",anaYemekChildClass))
            parentList.add(ParentClass("Çorbalar",corbaChildClass))
            parentList.add(ParentClass("Tatlılar",tatliChildClass))
            parentList.add(ParentClass("İçecekler",icecekChildClass))

        }



    }

    fun observeData(){
        mYemekViewModel.readAllData.observe(viewLifecycleOwner, Observer {

            // liste her yenilendiginde eskileri siler yeni listeleri uazerine yazar. clear olmadan yaparsan listenin onceki versıyonuyla alır.
            parentList.clear()
            anaYemekChildClass.clear()
            corbaChildClass.clear()
            tatliChildClass.clear()
            icecekChildClass.clear()
            addData(it)
            adapter.setDataParent(parentList)  //parentItemların datasını guncelledik


            //adapter.notifyDataSetChanged()
            //adapter.setDataParent(it)

        })

    }
}