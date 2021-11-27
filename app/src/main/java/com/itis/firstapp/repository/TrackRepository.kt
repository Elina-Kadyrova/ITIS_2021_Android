package com.itis.firstapp.repository

import com.itis.firstapp.R
import com.itis.firstapp.models.Track

object TrackRepository {
    private var i = 1

    val tracksList: ArrayList<Track> = arrayListOf(
        Track(i++,"Snowman","Sia", R.drawable.sia, R.raw.snowman),
        Track(i++,"Underneath the Tree","Kelly Clarkson", R.drawable.clarkson, R.raw.underneath_the_tree),
        Track(i++,"Last Christmas","Wham!", R.drawable.wham, R.raw.last_christmas),
        Track(i++,"All I Want For Christmas Is You","Mariah Carey", R.drawable.carey, R.raw.all_i_want),
        Track(i++,"Jingle Bell Rock","Bobby Helms", R.drawable.helms, R.raw.jingle_bell_rock),
        Track(i++,"Let it snow","Frank Sinatra", R.drawable.sinatra, R.raw.let_it_snow),
        Track(i++,"Santa's coming for us","Sia", R.drawable.sia, R.raw.santa_coming_for_us),
        Track(i++,"Jingle bells","Frank Sinatra", R.drawable.sinatra, R.raw.jingle_bells),
        Track(i++,"Santa tell me","Ariana Grande", R.drawable.grande, R.raw.santa_tell_me),
        Track(i++,"Christmas Lights","Coldplay", R.drawable.coldplay, R.raw.christmas_lights)
    )
}
