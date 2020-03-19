package com.example.myappproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class food : Fragment() {
    private var _title_:String?=null
    private var _detail_:String?=null
    private var _img_:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)

        val layout_title =view?.findViewById<TextView>(R.id._title)
        val layout_detail =view?.findViewById<TextView>(R.id._det)
        val layout_img =view.findViewById<ImageView>(R.id.image)

        layout_title?.text = this._title_
        layout_detail?.text = this._detail_
        Glide.with(this).load(_img_).into(layout_img);


        val btn1 = view.findViewById<Button>(R.id.btn1)

        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
        val mCartRef = mRootRef.child("cart")//go to folder cart if it not exist it will auto create folder

        btn1.setOnClickListener {
            var user = FirebaseAuth.getInstance().currentUser // get data of current user

            //check is login
            if(user != null){
                //login with facebook
                var mUserIdRef = mCartRef.child(user!!.uid)//go to cart of current user
                var postValue: HashMap<String, Any> = HashMap() // Array for insert to database
                postValue["title"] = this._title_!!
                postValue["description"] = this._detail_!!
                postValue["image"] = _img_!!
                mUserIdRef.push().setValue(postValue)//insert value to db in folder path cart/uid(from user data)
            }else{
                //login with local
                var mUserIdRef = mCartRef.child("60160099")//go to cart of current user
                var postValue: HashMap<String, Any> = HashMap() // Array for insert to database
                postValue["title"] = this._title_!!
                postValue["description"] = this._detail_!!
                postValue["image"] = _img_!!
                mUserIdRef.push().setValue(postValue)//insert value to db in folder path cart/uid(from user data)
            }
        }


        return view
    }
    fun newInstance(_title_: String,_detail_: String,_img_: String): food {
        val fragment = food()
        val bundle = Bundle()
        bundle.putString("_title", _title_)
        bundle.putString("_detail", _detail_)
        bundle.putString("_img", _img_)
        fragment.setArguments(bundle)
        return fragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            _title_ = bundle.getString("_title").toString()
            _detail_ = bundle.getString("_detail").toString()
            _img_ = bundle.getString("_img").toString()
        }
    }

}
