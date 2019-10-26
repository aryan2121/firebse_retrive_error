package com.amanaryan.tutions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_recycler_main.*
import kotlinx.android.synthetic.main.view_holder.view.*

class RecyclerMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_main)
        val adapter=GroupAdapter<ViewHolder>()
        indoorRecyclerView_Id.adapter=adapter
        getIndoorData()
    }
    fun getIndoorData(){

        val ref= FirebaseDatabase.getInstance().getReference("MAIN/TUTOR/INDOOR/SUBJECT/RATUROAD")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

            val adapter=GroupAdapter<ViewHolder>()
                p0.children.forEach {
                    val mera=it.getValue(SaveTutorData::class.java)
                    Log.d("Save","aagya")
                    if(mera!=null)
                        adapter.add(orderr(mera))
                    Log.d("Savee","aagya")}
            indoorRecyclerView_Id.adapter=adapter
            }


        })
    }
}

class orderr(val data:SaveTutorData):Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.view_holder
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        if(data.NAME!=null){
            viewHolder.itemView.holder_name.text=data.NAME
            Log.d("nameeee","null nhi h pr bind nhi ho rha")}
        else{Log.d("nameeee","null h name")}

        if(data.FEE!=null){
            viewHolder.itemView.holder_fee.text=data.FEE
            Log.d("feeeee","null nhi h pr bind nhi ho rha")}
        else{Log.d("feeeee","null h fee")}

//        if(data.IMAGE!=null){
//            Picasso.get().load(data.IMAGE).into(viewHolder.itemView.holder_imgg)
//            Log.d("imageeee","null nhi h pr bind nhi ho rha h ")}
//        else{Log.d("imageeee ","null h fee")}

    }

}