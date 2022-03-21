package ie.dorset.student_25308.ca2movie_booking_app

data class Movie(
    var name : String ,
    var image: String,
    var certification: String,
    var description: String,
    var starring: String,
    var running_time_mins: Int,
    var seats_remaining: Int = (0..15).random(),
    var seats_selected: Int =0

)
