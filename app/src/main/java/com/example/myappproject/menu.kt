package com.example.myappproject


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class menu : Fragment() {
    private var username : String? = null
    private var password : String? = null
    private var coffee : Button? = null
    private var bakery : Button? = null
    private var cart : Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_menu, container, false)
        coffee = view.findViewById<Button>(R.id.coffee)
        bakery = view.findViewById<Button>(R.id.bakery)
        cart = view.findViewById<Button>(R.id.Cart)

        val chart = view.findViewById<Button>(R.id.chart)
//        val show = view.findViewById<Button>(R.id.show)

        coffee!!.setOnClickListener{
            val coffee1 = recycler_view().newInstance("1")

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, coffee1,"fragment_recycler_view")
            transaction.addToBackStack("fragment_recycler_view")
            transaction.commit()

            val builder =
                AlertDialog.Builder(this.context)
                builder.setMessage("รับกาแฟมั้ยคะ?")
            builder.setPositiveButton(
                "รับครับ!!"
            ) { dialog, id ->
                Toast.makeText(this.context , "ขอบคุณค่ะ", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(
                "ไม่รับครับ!!"
            ) { dialog, which ->
                Toast.makeText(this.context , "ไว้โอกาสหน้านะคะ", Toast.LENGTH_SHORT).show()
            }
            builder.show()

        }

        bakery!!.setOnClickListener{
            val bakery1 = recycler_view().newInstance("2")

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, bakery1,"fragment_recycler_view")
            transaction.addToBackStack("fragment_recycler_view")
            transaction.commit()

            val builder =
                AlertDialog.Builder(this.context)
            builder.setMessage("รับ Bakery มั้ยคะ?")
            builder.setPositiveButton(
                "รับครับ!!"
            ) { dialog, id ->
                Toast.makeText(this.context , "ขอบคุณค่ะ", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(
                "ไม่รับครับ!!"
            ) { dialog, which ->
                Toast.makeText(this.context , "ไว้โอกาสหน้านะคะ", Toast.LENGTH_SHORT).show()
            }
            builder.show()

        }
        chart!!.setOnClickListener{
            val chart1 = MainChart()

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, chart1,"fragment_main_chart")
            transaction.addToBackStack("fragment_main_chart")
            transaction.commit()

        }

        cart!!.setOnClickListener{
            val list = list()

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, list,"fragment_list")
            transaction.addToBackStack("fragment_list")
            transaction.commit()

        }
//        show!!.setOnClickListener{
//            val show1 = ShowData()
//
//            val fm = fragmentManager
//            val transaction : FragmentTransaction = fm!!.beginTransaction()
//            transaction.replace(R.id.layout, show1,"fragment_show_data")
//            transaction.addToBackStack("fragment_show_data")
//            transaction.commit()
//
//        }
        // Inflate the layout for this fragment
        return view

    }

}
