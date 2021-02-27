package com.sd.saruj.personalallproject.MoreDataLoad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.sd.saruj.personalallproject.R;
import com.sd.saruj.personalallproject.controller.AppController;

import java.util.List;


public abstract class MoreDataLoadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mCtx;
    private List<MoreDataModel> mItemList;

    int mPageNumber = 1 ;


    public MoreDataLoadAdapter(Context mCtx, List<MoreDataModel> mItemList) {
        this.mCtx = mCtx;
        this.mItemList = mItemList;
    }


    public void setPageNumber(int pageNumber){
        mPageNumber= pageNumber ;
    }
    public abstract void loadNextPage(int pageNumber) ;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.more_data_list, null);
        return new MoreDataViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(position == (mItemList.size() - 1)){
            loadNextPage(mPageNumber+1);
        }

        MoreDataModel mItem = mItemList.get(position);

        ((MoreDataViewHolder) holder).bind(mItem);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class MoreDataViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mUserName, mEmail, mWebsite ;


        public MoreDataViewHolder(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.text_name);
            mUserName = itemView.findViewById(R.id.text_username);
            mEmail = itemView.findViewById(R.id.text_email);
            mWebsite = itemView.findViewById(R.id.text_website);


        }

        public void bind(MoreDataModel mItem) {
            mName.setText(mItem.getId());
            mUserName.setText(mItem.getUserId());
            mEmail.setText(mItem.getBody());
            mWebsite.setText(mItem.getTitle());

        }
    }
}