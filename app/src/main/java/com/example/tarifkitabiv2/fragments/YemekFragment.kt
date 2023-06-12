package com.example.tarifkitabiv2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tarifkitabiv2.R
import com.example.tarifkitabiv2.data.YemekViewModel
import com.example.tarifkitabiv2.databinding.FragmentYemekBinding


class YemekFragment : Fragment() {
    private var _binding:FragmentYemekBinding?=null
    private val binding get() = _binding!!
    private val args by navArgs<YemekFragmentArgs>()
    lateinit var mYemekViewModel:YemekViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentYemekBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mYemekViewModel=ViewModelProvider(this).get(YemekViewModel::class.java)

        var currentYemek=args.currentYemek
        var yemekİsim=args.currentYemek.isim.toString()
        var tarif=args.currentYemek.tarif.toString()
        var tur=args.currentYemek.tur.toString()

        binding.yemekSimTextView.setText(yemekİsim)
        binding.yemekTarifTextView.setText(tarif)
        binding.turTextView.setText(tur)
        binding.imageView.setImageBitmap(args.currentYemek.yemekFoto)

        binding.updateButton.setOnClickListener {
            val action=YemekFragmentDirections.actionYemekFragmentToUpdateFragment(currentYemek)
            Navigation.findNavController(it).navigate(action)
        }

        binding.deleteButton.setOnClickListener {
            mYemekViewModel.deleteYemek(currentYemek)
            Toast.makeText(requireContext(),"Tarif silindi!!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_yemekFragment_to_listFragment)
        }

    }

}