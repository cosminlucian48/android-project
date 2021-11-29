package com.example.proiect_real;


import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public List<Track> getTracks() {
        Track track1 = new Track();
        track1.setName("Track1");
        track1.setCountry("Cluj");
        track1.setLength("200");

        Track track2 = new Track();
        track2.setName("Track2");
        track2.setCountry("Cluj");
        track2.setLength("22");

        Track track3 = new Track();
        track3.setName("Track3");
        track3.setCountry("Cluj");
        track3.setLength("22");

        Track track4 = new Track();
        track4.setName("Track4");
        track4.setCountry("Cluj");
        track4.setLength("22");

        Track track5 = new Track();
        track5.setName("Track5");
        track5.setCountry("Cluj");
        track5.setLength("22");

        Track track6 = new Track();
        track6.setName("track6");
        track6.setCountry("Cluj");
        track6.setLength("22");

        Track track7 = new Track();
        track7.setName("track7");
        track7.setCountry("Cluj");
        track7.setLength("22");





        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        tracks.add(track3);
        tracks.add(track4);
        tracks.add(track5);
        tracks.add(track6);
        tracks.add(track7);

        System.out.println(tracks);

        return tracks;
    }


}