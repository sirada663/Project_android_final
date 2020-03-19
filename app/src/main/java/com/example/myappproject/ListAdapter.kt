package com.example.myappproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import org.json.JSONArray

class ListAdapter(act : FragmentActivity, val dataSource: JSONArray) : BaseAdapter() {
    private val thisActivity : FragmentActivity = act
    private val thiscontext: Context = act.baseContext
    private val inflater: LayoutInflater = thiscontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.length()
    }

    override fun getItem(position: Int): Any {
        return dataSource.getJSONObject(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder : ViewHolder // declare object from class ViewHolder
// 1
        if (convertView == null) {

            // 2
            view = inflater.inflate(R.layout.recy_layout, parent, false) //reuse layout from recycle view

            // 3
            holder = ViewHolder()
            holder.titleTextView = view.findViewById(R.id.tv_name) as TextView
            holder.detailTextView = view.findViewById(R.id.tv_description) as TextView
            holder.image = view.findViewById(R.id.imgV) as ImageView

            // 4
            view.tag = holder
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        // 6
        val titleTextView = holder.titleTextView
        val detailTextView = holder.detailTextView
        val image = holder.image
        //set value to element
        titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )
        detailTextView.setText( dataSource.getJSONObject(position).getString("description").toString() )
        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(image)

        return view
    }

    private class ViewHolder {

        lateinit var titleTextView: TextView // contain element id tv_name
        lateinit var detailTextView: TextView  // contain element id tv_description
        lateinit var image: ImageView // contain element id imgV


    }
}