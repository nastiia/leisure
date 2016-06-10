package by.bsuir.leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import by.bsuir.leisure.entities.Category;

/**
 * Created by nastia on 28.05.2016.
 */
public class CategoriesFragment extends Fragment {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recycler;
    private ArrayList<Category> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setHasFixedSize(false);
        RVAdapter adapter = new RVAdapter();
        recycler.setAdapter(adapter);
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

        public class PersonViewHolder extends RecyclerView.ViewHolder {

            CardView cv;
            TextView personName;
            TextView personAge;
            ImageView personPhoto;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                personName = (TextView)itemView.findViewById(R.id.person_name);
                personAge = (TextView)itemView.findViewById(R.id.person_age);
                personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        Intent intent = new Intent(getContext(), EventDetailActivity.class);
                        intent.putExtra(EventDetailActivity.EXTRA_NAME, "The Roots");

                        getContext().startActivity(intent);
                    }
                });
            }
        }

        ArrayList a = new ArrayList();

        RVAdapter() {}

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            Map qwe = new HashMap<String, String>();
            qwe.put("title", "Кино");
            qwe.put("description", "53 события");
            a.add(qwe);
            qwe = new HashMap<String, String>();
            qwe.put("title", "Выставки");
            qwe.put("description", "10 событий");
            a.add(qwe);
            qwe = new HashMap<String, String>();
            qwe.put("title", "Концерты");
            qwe.put("description", "9 событий");
            a.add(qwe);
            qwe = new HashMap<String, String>();
            qwe.put("title", "Спорт");
            qwe.put("description", "25 событий");
            a.add(qwe);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            Map q = (Map) a.get(i);
            personViewHolder.personName.setText(q.get("title").toString());
            personViewHolder.personAge.setText(q.get("description").toString());
            int resource = 0;
            switch (i) {
                case 0: {
                    resource = R.drawable.cinema;
                    break;
                }
                case 1: {
                    resource = R.drawable.exhibition;
                    break;
                }
                case 2: {
                    resource = R.drawable.concert;
                    break;
                }
                case 3: {
                    resource = R.drawable.sport;
                    break;
                }
            }
            Picasso.with(getContext())
                    .load(resource)
                    .resize(200, 200)
                    .into(personViewHolder.personPhoto);
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}
