package com.example.myappproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class list : Fragment() {

    private var uid : String ? = ""//contain uid from user data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        /*--------set uid---------*/
        //get user data
        val user = FirebaseAuth.getInstance().currentUser
        //check login with local or login with facebook
        if(user != null){
            //login with facebook
            uid = user.uid
        }else{
            //login with local
            uid = "60160099"
        }

        /*--------get data form database realtime---------*/
        val mRootRef = FirebaseDatabase.getInstance().reference
        val mcCartRef = mRootRef.child("cart").child(uid!!)//use uid as folder name in database to path

        mcCartRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val jsonArray : JSONArray? = JSONArray()
                val listView = view.findViewById<ListView>(R.id.cart_list)//get element list_view

                for (ds in dataSnapshot.children) {

                    val jObject = JSONObject()

                    val title = ds.child("title").getValue(String::class.java)!!
                    val description = ds.child("description").getValue(String::class.java)!!
                    val image = ds.child("image").getValue(String::class.java)!!

                    jObject.put("key",ds.key)//as a primary key in sql database but in this scenario it doesn't use
                    jObject.put("title",title)
                    jObject.put("description",description)
                    jObject.put("image",image)

                    jsonArray!!.put(jObject)

                }

                val adapter = ListAdapter(activity!!, jsonArray!!)//decalre object from class ListAdapter with pass param

                listView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })






        // Inflate the layout for this fragment
        return view
    }


}
