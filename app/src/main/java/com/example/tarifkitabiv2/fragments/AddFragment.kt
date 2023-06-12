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
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.tarifkitabiv2.R
import com.example.tarifkitabiv2.data.Yemek
import com.example.tarifkitabiv2.data.YemekViewModel
import com.example.tarifkitabiv2.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private var _binding:FragmentAddBinding?=null
    private val binding get() = _binding!!
    private lateinit var mYemekViewModel: YemekViewModel
    private var secilenGorsel:Uri?=null
    private var yemekFoto:Bitmap?=null
    private lateinit var secilenTur:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mYemekViewModel=ViewModelProvider(this).get(YemekViewModel::class.java)
        binding.addImageView.setOnClickListener {
            gorselSec(it)
        }
        binding.turRadioGrup.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){
                R.id.anaYemekRadioButton-> secilenTur = "Ana Yemek"
                R.id.corbaRadioButton-> secilenTur="Çorba"
                R.id.tatliRadioButton-> secilenTur="Tatlı"
                R.id.iceceklerRadioButton->secilenTur="İçecek"
            }
        }
        binding.addButton.setOnClickListener {
            if(secilenGorsel!=null){
                insertDataToDatabase()
            }else
                Toast.makeText(requireContext(),"Lütfen fotoğraf seçiniz!!",Toast.LENGTH_LONG).show()


        }
    }

    fun insertDataToDatabase(){
        var yemekIsmi=binding.isimEditText.text.toString()
        var tarif=binding.tarifEditText.text.toString()
        var tur:String=secilenTur

        if(inputCheck(yemekIsmi,tarif)){

            val yemek=Yemek(0,yemekIsmi,tarif,tur,yemekFoto!!)

            mYemekViewModel.addYemek(yemek)
            Toast.makeText(requireContext(),"Tarifiniz eklendi!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)


        }

    }
    fun inputCheck(yemekIsmi:String,tarif:String):Boolean{
        return !(yemekIsmi.isEmpty()&&tarif.isEmpty())

    }

    fun gorselSec(view: View){
        if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE)
            !=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            val galeriIntent= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntent,2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode==1){
            if(grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                val galeriIntent= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2&&resultCode==Activity.RESULT_OK&&data!=null){
            secilenGorsel=data.data
            if(secilenGorsel!=null){
                if (Build.VERSION.SDK_INT>=28){
                    val source=ImageDecoder.createSource(requireContext().contentResolver,secilenGorsel!!)
                    yemekFoto=ImageDecoder.decodeBitmap(source)
                    binding.addImageView.setImageBitmap(yemekFoto)
                }else{
                    yemekFoto=MediaStore.Images.Media.getBitmap(requireContext().contentResolver,secilenGorsel)
                    binding.addImageView.setImageBitmap(yemekFoto)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
