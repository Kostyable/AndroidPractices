package ru.mirea.blinnikovkm.recyclerviewapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HistoricalEvent> historicalEvents = getHistoricalEvents();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setAdapter(new HistoricalEventRecyclerViewAdapter(historicalEvents));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<HistoricalEvent> getHistoricalEvents() {
        List<HistoricalEvent> events = new ArrayList<>();
        events.add(new HistoricalEvent(
                "Крещение Руси",
                "В 988 году князь Владимир Великий крестил Русь, что стало важнейшим событием в истории государства и культуры.",
                "baptism_of_rus"));
        events.add(new HistoricalEvent(
                "Монгольское нашествие",
                "В 1237–1240 годах Монгольское нашествие привело к завоеванию Руси и установлению зависимости от Золотой Орды.",
                "mongol_invasion"));
        events.add(new HistoricalEvent(
                "Смутное время",
                "Период кризиса в России в конце XVI — начале XVII века, сопровождавшийся династическим кризисом и интервенцией.",
                "time_of_troubles"));
        events.add(new HistoricalEvent(
                "Реформы Петра I",
                "В начале XVIII века Петр I реформировал Россию, основав новую столицу - Санкт-Петербург, и превратил её в одну из крупнейших европейских держав.",
                "peter_the_great_reforms"));
        events.add(new HistoricalEvent(
                "Отечественная война 1812 года",
                "Война России против армии Наполеона, завершившаяся изгнанием французов и укреплением России как великой державы.",
                "patriotic_war_of_1812"));
        events.add(new HistoricalEvent(
                "Октябрьская революция 1917 года",
                "Октябрьская революция, возглавляемая большевиками, привела к установлению советской власти и образованию Советского Союза.",
                "october_revolution"));
        events.add(new HistoricalEvent(
                "Великая Отечественная война",
                "С 1941 по 1945 год СССР вёл войну против нацистской Германии, одержав победу и освободив Европу от фашизма.",
                "great_patriotic_war"));
        return events;
    }
}
