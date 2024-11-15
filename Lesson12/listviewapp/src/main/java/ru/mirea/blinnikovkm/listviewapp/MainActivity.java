package ru.mirea.blinnikovkm.listviewapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView booksList = findViewById(R.id.booksListView);

        String[] books = {
                "Фёдор Достоевский - Белые ночи",
                "Лев Толстой - Семейное счастье",
                "Иван Тургенев - Первая любовь",
                "Михаил Булгаков - Дни Турбиных",
                "Антон Чехов - Дама с собачкой",
                "Фёдор Достоевский - Преступление и наказание",
                "Александр Пушкин - Дубровский",
                "Михаил Лермонтов - Тамань",
                "Иван Бунин - Суходол",
                "Александр Куприн - Олеся",
                "Максим Горький - Мои университеты",
                "Николай Лесков - Очарованный странник",
                "Валентин Катаев - Алмазный мой венец",
                "Константин Паустовский - Золотая роза",
                "Лев Толстой - Война и мир",
                "Михаил Шолохов - Тихий Дон",
                "Джейн Остин - Эмма",
                "Шарлотта Бронте - Городок",
                "Ивлин Во - Возвращение в Брайдсхед",
                "Томас Манн - Волшебная гора",
                "Фрэнсис Скотт Фицджеральд - Ночь нежна",
                "Джон Голсуорси - Сага о Форсайтах",
                "Роберт Грейвз - Я, Клавдий",
                "Агата Кристи - Убийство в Восточном экспрессе",
                "Рэй Брэдбери - 451 градус по Фаренгейту",
                "Леон Фейхтвангер - Гойя",
                "Стефан Цвейг - Нетерпение сердца",
                "Эрих Мария Ремарк - Триумфальная арка",
                "Джон Ирвинг - Мир глазами Гарпа",
                "Исаак Башевис Зингер - Семья Мушкат",
                "Габриэль Гарсиа Маркес - Любовь во время чумы",
                "Кеннет Грэм - Ветер в ивах"
        };

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, books) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                text2.setText(getItem(position).toString());
                text1.setText(String.valueOf(position+1));
                return view;
            }
        };

        booksList.setAdapter(adapter);
    }
}