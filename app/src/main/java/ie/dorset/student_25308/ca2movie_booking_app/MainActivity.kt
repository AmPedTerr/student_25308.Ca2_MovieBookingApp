package ie.dorset.student_25308.ca2movie_booking_app

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ie.dorset.student_25308.ca2movie_booking_app.Movie
import java.util.concurrent.Executors

const val MAIN_ACT_KEY = "mainAct"

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Movie>
    lateinit var titleImage : Array<String>
    lateinit var heading : Array<String>
    lateinit var movieCertification:Array<String>
    lateinit var starring:Array<String>
    lateinit var runningTime :Array<Int>
    lateinit var description : Array<String>




    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


// uncharted https://www.myvue.com/-/media/images/film%20and%20events/january%202022/uncharted_film_page_panel.jpg?sc=.99
        //batman https://www.myvue.com/-/media/the%20batman.jpg?sc=.99
        // sing 2 https://www.myvue.com/-/media/images/film%20and%20events/november%202021/sing%202_film_page_panel.jpg?sc=.99
        // wolf https://www.myvue.com/-/media/images/film%20and%20events/march%2022/wolf-panel.jpg?sc=.99

        //https://www.myvue.com/-/media/images/film%20and%20events/january%202022/belfast_film_page_panel.jpg?sc=.99

        // internet belfast
/*
        val belfasrURl="https://www.myvue.com/-/media/images/film%20and%20events/january%202022/belfast_film_page_panel.jpg?sc=.99"
        val  belfastImage= Picasso.get().load(belfasrURl).placeholder(ContextCompat.getDrawable(this ,R.drawable.ic_baseline_local_movies_24)!!).error(
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_error_24)!!).memoryPolicy(
            MemoryPolicy.NO_CACHE)

*/


        heading =
            arrayOf("THE BATMAN", "UNCHARTED", "SING 2", "WOLF", "BELFAST", "FANTASTIC BEASTS")
        // titleImage= arrayOf(R.drawable.the_batman,R.drawable.uncharted_film_page_panel, R.drawable.sing_2_film_page_panel,R.drawable.wolf_panel)

        titleImage = arrayOf(
            "https://www.myvue.com/-/media/the%20batman.jpg?sc=.99",
            "https://www.myvue.com/-/media/images/film%20and%20events/january%202022/uncharted_film_page_panel.jpg?sc=.99",
            "https://www.myvue.com/-/media/images/film%20and%20events/november%202021/sing%202_film_page_panel.jpg?sc=.99",
            "https://www.myvue.com/-/media/images/film%20and%20events/march%2022/wolf-panel.jpg?sc=.99",
            "https://www.myvue.com/-/media/images/film%20and%20events/january%202022/belfast_film_page_panel.jpg?sc=.99",
            "https://www.myvue.com/-/media/images/film%20and%20events/february%202022/the-secrets-of-dumbledore-panel.jpg?sc=.99"
        )



        movieCertification = arrayOf("15A", "12A", "G", "15A", "12A", "12A")
        starring = arrayOf(
            "Robert Pattinson, Zoe Kravitz, Paul Dano, Colin Farrell",
            "Tom Holland, Mark Wahlberg, Antonio Banderas",
            "Reese Witherspoon,Matthew McConaughey,Scarlett Johansson",
            "George MacKay, Eileen Walsh, Paddy Considine, Lily-Rose Depp",
            "Caitriona Balfe,Jamie Dornan,Judi Dench",
            "Eddie Redmayne, Jude Law, Ezra Miller, Alison Sudol, Dan Fogler, Mads Mikkelsen"
        )
        runningTime = arrayOf(175, 116, 110, 100, 98, 142)
        description = arrayOf(
            "From Warner Bros. Pictures comes \"The Batman,\" with director Matt Reeves (the \"Planet of the Apes\" films) at the helm and with Robert Pattinson (\"Tenet,\" \"The Lighthouse,\" \"Good Time\") starring as Gotham City's vigilante detective, Batman.",
            "Street-smart Nathan Drake (Tom Holland) is recruited by seasoned treasure hunter Victor \"Sully\" Sullivan (Mark Wahlberg) to recover a fortune amassed by Ferdinand Magellan and lost 500 years ago by the House of Moncada.",
            "Can-do koala Buster Moon and his all-star cast of animal performers prepare to launch their most dazzling stage extravaganza yet... in the glittering entertainment capital of the world.",
            "Believing he is a wolf trapped in a human body, Jacob eats, sleeps, and lives like a wolf – much to the shock of his family.",
            "From lauded director Kenneth Branagh, and starring an acclaimed ensemble cast, 'Belfast' is a personal and joyful story about the power of memory, set in late 1960s Northern Ireland.",
            "Unable to stop the powerful Dark wizard Gellert Grindelwald alone, Professor Albus Dumbledore entrusts Magizoologist Newt Scamander to lead an intrepid team of wizards, witches and one brave Muggle baker on a dangerous mission."
        )



        newRecyclerView = findViewById(R.id.recyclerView1)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<Movie>()
        getUserdata()

    }
        override fun onStart() {
            super.onStart()
            Log.i(MAIN_ACT_KEY, "onStart called")
        }

        override fun onPause() {
            super.onPause()
            Log.i(MAIN_ACT_KEY, "onPause called")
        }

        override fun onResume() {
            super.onResume()
            Log.i(MAIN_ACT_KEY, "onResume called")
        }

        override fun onStop() {
            super.onStop()
            Log.i(MAIN_ACT_KEY, "onStop called")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.i(MAIN_ACT_KEY, "onDestroy called")
        }



    private fun getUserdata() {
        for(i in heading.indices){
            val movie = Movie( heading[i],titleImage[i], movieCertification[i], description[i] ,starring [i], runningTime[i])
            newArrayList.add(movie)
        }
        newRecyclerView.adapter= MovieAdapter(newArrayList,this)
    }
}