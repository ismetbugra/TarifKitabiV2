package com.example.tarifkitabiv2.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tarifkitabiv2.R
import com.example.tarifkitabiv2.data.Yemek
import com.example.tarifkitabiv2.data.YemekViewModel
import com.example.tarifkitabiv2.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private var _binding:FragmentUpdateBinding?=null
    private val binding get() = _binding!!
    private val argsUpdate by navArgs<UpdateFragmentArgs>()
    private lateinit var secilenTur:String
    private var secilenGorsel: Uri?=null
    private var yemekFotoUpdate: Bitmap?=null
    private lateinit var mYemekViewModel:YemekViewModel
    private lateinit var currentItem:Yemek

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mYemekViewModel=ViewModelProvider(this).get(YemekViewModel::class.java)

        currentItem=argsUpdate.currentYemek
        binding.updateImageView.setImageBitmap(currentItem.yemekFoto)
        binding.isimUpdateEditText.setText(currentItem.isim)
        binding.tarifUpdateEditText.setText(currentItem.tarif)

        /*binding.updateImageView.setOnClickListener {
            gorselSec(it)
        }*/

        binding.turUpdateRadioGrup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.anaYemekUpdateRadioButton-> secilenTur="Ana Yemek"
                R.id.corbaUpdateRadioButton->secilenTur="Çorba"
                R.id.tatliUpdateRadioButton->secilenTur="Tatlı"
                R.id.iceceklerUpdateRadioButton->secilenTur="İçecek"
            }
        }


        binding.updateButton.setOnClickListener {
            updateDataToDatabase()
        }


    }

    fun updateDataToDatabase(){
        var yemekIsmi=binding.isimUpdateEditText.text.toString()
        var yemekTarif=binding.tarifUpdateEditText.text.toString()
        var tur=secilenTur

        if (inputCheck(yemekIsmi,yemekTarif)){
            val yemek=Yemek(argsUpdate.currentYemek.id,yemekIsmi,yemekTarif,tur,argsUpdate.currentYemek.yemekFoto)
            mYemekViewModel.updateYemek(yemek)
            Toast.makeText(requireContext(),"Tarif güncellendi",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

    }
    fun inputCheck(yemekIsmi:String,tarif:String):Boolean{
        return !(yemekIsmi.isEmpty()&&tarif.isEmpty())
    }

    /*fun gorselSec(view: View){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val galeriIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntent,2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode==1){
            if(grantResults.size>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                val galeriIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2&&resultCode== Activity.RESULT_OK&&data!=null){
            secilenGorsel=data.data
            if(secilenGorsel!=null){
                if (Build.VERSION.SDK_INT>=28){
                    val source=
                        ImageDecoder.createSource(requireContext().contentResolver,secilenGorsel!!)
                    yemekFotoUpdate= ImageDecoder.decodeBitmap(source)
                    binding.updateImageView.setImageBitmap(yemekFotoUpdate)
                }else{
                    yemekFotoUpdate= MediaStore.Images.Media.getBitmap(requireContext().contentResolver,secilenGorsel)
                    binding.updateImageView.setImageBitmap(yemekFotoUpdate)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }*/


}