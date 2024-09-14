package ru.igorcodes.photoalbum.adapter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.igorcodes.photoalbum.databinding.ImageItemBinding
import ru.igorcodes.photoalbum.model.MyImages
import ru.igorcodes.photoalbum.utile.ConvertImage
import ru.igorcodes.photoalbum.view.AddImageActivity
import ru.igorcodes.photoalbum.view.UpdateImageActivity

class MyImagesAdapter(private val activity: Activity): RecyclerView.Adapter<MyImagesAdapter.MyImagesViewHolder>() {

    var imageList: List<MyImages> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setImage(images: List<MyImages>) {
        this.imageList = images
        notifyDataSetChanged()
    }

    class MyImagesViewHolder(val itemBinding: ImageItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImagesViewHolder {
        val view = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyImagesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MyImagesViewHolder, position: Int) {
        val myImage = imageList[position]

        with(holder) {
            itemBinding.textViewTitle.text = myImage.imageTitle
            itemBinding.textViewDescription.text = myImage.imageDescription

            val imageAsBitmap = ConvertImage.convertToBitmap(myImage.imageAsString)
            itemBinding.imageView.setImageBitmap(imageAsBitmap)

            itemBinding.cardView.setOnClickListener {
                val intent = Intent(activity, UpdateImageActivity::class.java)
                intent.putExtra("id", myImage.imageID)
                activity.startActivity(intent)
            }
        }
    }

    fun returnItemAtPosition(position: Int) : MyImages {
        return imageList[position]
    }
}