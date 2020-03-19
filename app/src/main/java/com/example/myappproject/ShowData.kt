package com.example.myappproject


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * A simple [Fragment] subclass.
 */
class ShowData : Fragment() {

    lateinit var textview : TextView
    lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_show_data, container, false)
        // Inflate the layout for this fragment

        //Some url endpoint that you may have
        val myUrl = "http://api.plos.org/search?q=title:%22Drosophila%22%20and%20body:%22RNA%22&fl=id,abstract&wt=json&indent=on"

        //String to place our result in
        val result : String

        //Instantiate new instance of our class
        val getRequest = HttpGetRequest()

        //Perform the doInBackground method, passing in our url
        result = getRequest.execute(myUrl).get()

        textview = view.findViewById(R.id.text)
        textview.setText(result)

        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val builder =
                AlertDialog.Builder(this.context)
            builder.setMessage("Coffee and Bekery ?")
            builder.setPositiveButton(
                "Yes!!"
            ) { dialog, id ->
                Toast.makeText(this.context , "THANK YOU", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(
                "No!!"
            ) { dialog, which ->
                //dialog.dismiss();
            }
            builder.show()
        }
        return view
    }


}
