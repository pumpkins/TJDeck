package net.totoraj.tjdeck.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_upload_item.view.*
import net.totoraj.tjdeck.MyApplication.Companion.getAppContext
import net.totoraj.tjdeck.R
import net.totoraj.tjdeck.callback.UploadItemsDiffCallback

class UploadItemAdapter(private var items: List<Uri>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateItems(newItems: List<Uri>) {
        val diffResult = DiffUtil.calculateDiff(UploadItemsDiffCallback(items, newItems))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            UploadItemViewHolder(
                    LayoutInflater.from(getAppContext())
                            .inflate(R.layout.layout_upload_item, parent, false)
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            when (holder) {
                is UploadItemViewHolder -> holder.bind(items[position])
                else -> { // do nothing
                }
            }

    inner class UploadItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(uri: Uri) = view.img_preview.setImageURI(uri)
    }
}