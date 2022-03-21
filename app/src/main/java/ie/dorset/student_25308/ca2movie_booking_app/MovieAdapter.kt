package ie.dorset.student_25308.ca2movie_booking_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorFilter
import android.media.Image
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import java.util.concurrent.Executors

class MovieAdapter (private val movielist: ArrayList<Movie>,private val context:Context):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    private val picasso = Picasso.get()


   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflator= LayoutInflater.from(parent.context)
         val view =   inflator.inflate(R.layout.movies_recycler_template,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = movielist[position]
       //holder.titleImage.setImageResource(currentItem.image)

        holder.movieHeading.text=currentItem.name

//image
        Picasso.get().load(currentItem.image).placeholder(ContextCompat.getDrawable(context, R.drawable.ic_baseline_local_movies_24)!!).error(
            ContextCompat.getDrawable(context, R.drawable.ic_baseline_error_24)!!).memoryPolicy(
            MemoryPolicy.NO_CACHE).into(holder.titleImage)


        // change to add real photos movies
       // generate random images https://thisartworkdoesnotexist.com/
        /*
               val dpImageView = holder.titleImage
               val dpUrl="https://thisartworkdoesnotexist.com/"
               Picasso.get().load(dpUrl).placeholder(ContextCompat.getDrawable(context, R.drawable.ic_baseline_local_movies_24)!!).error(
                   ContextCompat.getDrawable(context, R.drawable.ic_baseline_error_24)!!).memoryPolicy(
                   MemoryPolicy.NO_CACHE).into(dpImageView)




 */

      //  val imageurl = holder.itemView.findViewById<ImageView>(R.id.movie_image)




        holder.movieCertification.text=currentItem.certification

        if(holder.movieCertification.text == "15A"){
            holder.movieCertification.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4618D")))
        }else if(holder.movieCertification.text == "G"){
            holder.movieCertification.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")))
        }else if(holder.movieCertification.text == "12A"){
            holder.movieCertification.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F44336")))

        }

        holder.starring.text=currentItem.starring
        holder.runningTime.text=currentItem.running_time_mins.toString()

        holder.description.text=currentItem.description


        val numSeatSelectedTextView=holder.itemView.findViewById<TextView>(R.id.movie_selectSeats_editTextNumber)
        val numSeatsRemainingTextView=holder.itemView.findViewById<TextView>(R.id.seats_remaining_textView)
        numSeatsRemainingTextView.text=currentItem.seats_remaining.toString()

        if (currentItem.seats_remaining <= 3){
            val salehurry = holder.itemView.findViewById<ImageView>(R.id.selling_fastview)
            salehurry.visibility=View.VISIBLE
        }
        holder.itemView.findViewById<ImageButton>(R.id.minus_imageButton).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#727272")))



        holder.itemView.findViewById<ImageButton>(R.id.minus_imageButton).setOnClickListener{

            if(currentItem.seats_selected > 0){
                currentItem.seats_selected -= 1
                numSeatSelectedTextView.text =currentItem.seats_selected.toString()
                //val sofa =holder.itemView.findViewById<ImageButton>(R.id.imageButton_seat)
                //val srTextView=holder.itemView.findViewById<TextView>(R.id.textView8)
                //numSeatsRemainingTextView.visibility=View.GONE



                holder.itemView.findViewById<ImageButton>(R.id.minus_imageButton).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFFFF")))
                holder.itemView.findViewById<ImageButton>(R.id.plus_imageButton).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFFFF")))
                currentItem.seats_remaining = currentItem.seats_remaining +1
                numSeatsRemainingTextView.text = currentItem.seats_remaining.toString()
                numSeatsRemainingTextView.setTextColor(Color.parseColor("#00FF00"))
                if (currentItem.seats_remaining<= 3){
                    val salehurry = holder.itemView.findViewById<ImageView>(R.id.selling_fastview)
                    salehurry.visibility=View.VISIBLE
                }

                if(currentItem.seats_selected == 0){
                    holder.itemView.findViewById<ImageButton>(R.id.minus_imageButton).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#727272")))
                }


            }
        }
        holder.itemView.findViewById<ImageButton>(R.id.plus_imageButton).setOnClickListener{

            if(currentItem.seats_selected < 15){
               currentItem.seats_selected += 1
                val sofa =holder.itemView.findViewById<ImageButton>(R.id.imageButton_seat)
                val srTextView=holder.itemView.findViewById<TextView>(R.id.textView8)
                numSeatSelectedTextView.text =currentItem.seats_selected.toString()
                //numSeatsRemainingTextView.visibility=View.GONE
                //sofa.visibility=View.GONE
                //srTextView.visibility=View.GONE
                sofa.setColorFilter(Color.parseColor("#00FF00"));
                srTextView.setText("Remaining Seats")
                srTextView.setTextColor(Color.parseColor("#00FF00"))
                currentItem.seats_remaining = currentItem.seats_remaining -1
                numSeatsRemainingTextView.text = currentItem.seats_remaining.toString()
                numSeatsRemainingTextView.setTextColor(Color.parseColor("#00FF00"))
                if (currentItem.seats_remaining<= 3){
                    val salehurry = holder.itemView.findViewById<ImageView>(R.id.selling_fastview)
                    salehurry.visibility=View.VISIBLE
                }

                holder.itemView.findViewById<ImageButton>(R.id.minus_imageButton).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFFFF")))
                if(currentItem.seats_selected == 15){
                    holder.itemView.findViewById<ImageButton>(R.id.plus_imageButton).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#727272")))
                }
            }
        }





    }

    override fun getItemCount():Int {
        return movielist.size
    }

    class MovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleImage :ImageView =itemView.findViewById(R.id.movie_image)
        val movieHeading :TextView= itemView.findViewById(R.id.movieHeading)
        val movieCertification : TextView = itemView.findViewById(R.id.movie_category)
        val starring :TextView =itemView.findViewById(R.id.movie_staring)
        val runningTime : TextView =itemView.findViewById(R.id.movie_running_time)
        val description : TextView = itemView.findViewById(R.id.movie_description)
        //val numSeatsSelected : TextView=itemView.findViewById(R.id.movie_selectSeats_editTextNumber)
    }
}