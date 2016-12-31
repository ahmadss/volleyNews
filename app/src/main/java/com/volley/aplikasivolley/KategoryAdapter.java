package com.volley.aplikasivolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.volley.aplikasivolley.model.Kategori;

import java.util.List;

/**
 * Created by ahmad on 31/12/2015.
 */
public class KategoryAdapter extends RecyclerView.Adapter<KategoryAdapter.myViewHolder> {

    List<Kategori> kategoris;
    Context context;
    MyListItemClick myListener;

    public KategoryAdapter(List<Kategori> kategoris, Context context, MyListItemClick myListener){
        this.kategoris = kategoris;
        this.context = context;
        this.myListener = myListener;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Kategori currentKategori = kategoris.get(position);
        holder.mTextViewPostName.setText(currentKategori.getkName());
        if (!TextUtils.isEmpty(currentKategori.getkImage())){
            ((View)holder.mKategoriImage.getParent()).setVisibility(View.VISIBLE);
            holder.mKategoriImage.setImageUrl("http://192.168.56.1/ebook/upload/category/"+currentKategori.getkImage(), KoneksiManager.getImageLoader(this.context));
        } else {
            ((View)holder.mKategoriImage.getParent()).setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return this.kategoris.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTextViewPostName;
        public NetworkImageView mKategoriImage;

        public myViewHolder(View itemView) {
            super(itemView);
            mTextViewPostName = (TextView) itemView.findViewById(R.id.rowTextViewName);
            mKategoriImage  = (NetworkImageView) itemView.findViewById(R.id.rowNetImageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myListener.OnItemClick(kategoris.get(getPosition()));
        }
    }

    public static interface MyListItemClick{
        public void OnItemClick(Kategori itemClick);
    }
}
