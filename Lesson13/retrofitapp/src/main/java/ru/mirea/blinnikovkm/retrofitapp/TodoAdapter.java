package ru.mirea.blinnikovkm.retrofitapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    public static final String TAG = "TodoAdapter";
    private LayoutInflater layoutInflater;
    private List<Todo> todos;
    private ApiService apiService;

    public TodoAdapter(Context context, List<Todo> todoList, ApiService apiService) {
        this.layoutInflater = LayoutInflater.from(context);
        this.todos = todoList;
        this.apiService = apiService;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);

        holder.textViewTitle.setText(todo.getTitle());
        holder.checkBoxCompleted.setChecked(todo.getCompleted());
        holder.checkBoxCompleted.setOnClickListener(null);

        holder.checkBoxCompleted.setOnClickListener(v -> {
            boolean newState = holder.checkBoxCompleted.isChecked();
            if (todo.getCompleted() != newState) {
                todo.setCompleted(newState);
                updateTodoOnServer(todo, holder.itemView.getContext());
            }
        });

        if (todo.getImageUrl() != null && !todo.getImageUrl().isEmpty()) {
            Picasso.get()
                    .load(todo.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .resize(100, 100)
                    .centerCrop()
                    .into(holder.imageViewTodo);
        } else {
            loadRandomCatImage(holder.imageViewTodo, todo);
        }
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    private void updateTodoOnServer(Todo todo, Context context) {
        Call<Todo> call = apiService.updateTodo(todo.getId(), todo);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Todo updated successfully!");
                } else {
                    Log.e(TAG, "Failed to update Todo: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e(TAG, "Error updating Todo: " + t.getMessage());
            }
        });
    }

    private void loadRandomCatImage(ImageView imageView, Todo todo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService catApiService = retrofit.create(ApiService.class);
        Call<List<CatImage>> call = catApiService.getRandomCatImage();

        call.enqueue(new Callback<List<CatImage>>() {
            @Override
            public void onResponse(Call<List<CatImage>> call, Response<List<CatImage>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    String imageUrl = response.body().get(0).getUrl();

                    todo.setImageUrl(imageUrl);

                    Picasso.get()
                            .load(imageUrl)
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.error_image)
                            .resize(100, 100)
                            .centerCrop()
                            .into(imageView);
                }
            }

            @Override
            public void onFailure(Call<List<CatImage>> call, Throwable t) {
                Picasso.get()
                        .load(R.drawable.error_image)
                        .into(imageView);
            }
        });
    }
}
