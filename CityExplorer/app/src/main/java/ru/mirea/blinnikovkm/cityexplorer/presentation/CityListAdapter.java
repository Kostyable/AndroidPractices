package ru.mirea.blinnikovkm.cityexplorer.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.mirea.blinnikovkm.cityexplorer.R;
import ru.mirea.blinnikovkm.domain.domain.models.City;

public class CityListAdapter extends ListAdapter<City, CityListAdapter.CityViewHolder> {

    private final OnCityClickListener onCityClickListener;

    public interface OnCityClickListener {
        void onCityClick(City city);
    }

    public CityListAdapter(OnCityClickListener onCityClickListener) {
        super(DIFF_CALLBACK);
        this.onCityClickListener = onCityClickListener;
    }

    private static final DiffUtil.ItemCallback<City> DIFF_CALLBACK = new DiffUtil.ItemCallback<City>() {
        @Override
        public boolean areItemsTheSame(@NonNull City oldItem, @NonNull City newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull City oldItem, @NonNull City newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = getItem(position);
        holder.bind(city);
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        private final TextView cityNameTextView;
        private final TextView countryNameTextView;

        private final ImageView flagImageView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.city_name);
            countryNameTextView = itemView.findViewById(R.id.country_name);
            flagImageView = itemView.findViewById(R.id.flag_image);

            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onCityClickListener.onCityClick(getItem(position));
                }
            });
        }

        public void bind(City city) {
            cityNameTextView.setText(city.getName());
            countryNameTextView.setText(city.getCountryName());

            if (city.getFlagUrl() != null && !city.getFlagUrl().isEmpty()) {
                Picasso.get()
                        .load(city.getFlagUrl())
                        .into(flagImageView);
            }
        }
    }
}