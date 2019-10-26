package com.amanaryan.tutions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var photoUri: Uri?=null
    lateinit var image:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tutorimg_button.setOnClickListener {
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)
        }
        submit.setOnClickListener {
            uploadImage()
        }
        intenttt.setOnClickListener {
            startActivity(Intent(this,RecyclerMain::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if(requestCode==0&&resultCode==Activity.RESULT_OK&& data!=null){
        photoUri=data.data
    }

    }


   fun uploadImage(){
val refff=FirebaseStorage.getInstance().getReference("MAIN/TUTOR/INDOOR/SUBJECT/RATUROAD/${tutor_name}")
       refff.putFile(photoUri!!)
           .addOnSuccessListener {
               refff.downloadUrl.addOnSuccessListener {
                   image=it.toString()
                   uploadData()
               }
               Toast.makeText(this,"photo Added",Toast.LENGTH_SHORT).show()
           }
           .addOnFailureListener{

           }
   }

    fun uploadData(){

        val NAME=tutor_name.text.toString()
        val FEE=tutor_fee.text.toString()
        val ref=FirebaseDatabase.getInstance().getReference("MAIN/TUTOR/INDOOR/SUBJECT/RATUROAD")
        val value=SaveTutorData(NAME,image,FEE)
        ref.child(NAME).setValue(value).addOnSuccessListener {
            Toast.makeText(this,"DATA UPLOADED",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{

        }
    }

}
