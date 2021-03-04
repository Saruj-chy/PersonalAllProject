package com.sd.saruj.personalallproject.ListDataSelect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sd.saruj.personalallproject.R;
import com.sd.saruj.personalallproject.controller.AppController;
import com.sd.saruj.personalallproject.controller.AppImageLoader;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListDataSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCtx;
    private List<ListDataModel> mItemList;
    private  List<String> mPhoneList ;
    private OnCheckItemList onCheckItemList ;

    public ListDataSelectAdapter(Context mCtx, List<ListDataModel> mItemList, OnCheckItemList onCheckItemList) {
        this.mCtx = mCtx;
        this.mItemList = mItemList;
        this.onCheckItemList = onCheckItemList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_data_list, null);
        mPhoneList = new ArrayList<>() ;
        return new ListDataSelectAdapter.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ListDataModel mItem = mItemList.get(position);

        ((ListDataSelectAdapter.ItemViewHolder) holder).bind(mItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((ItemViewHolder) holder).mCheckBox.isChecked()){
                    ((ItemViewHolder) holder).mCheckBox.setVisibility(View.GONE);
                    ((ItemViewHolder) holder).mCheckBox.setChecked(false);
                    mPhoneList.remove(mItem.getPhone()) ;
                }else{
                    ((ItemViewHolder) holder).mCheckBox.setVisibility(View.VISIBLE);
                    ((ItemViewHolder) holder).mCheckBox.setChecked(true);
                    mPhoneList.add(mItem.getPhone()) ;
                }
                onCheckItemList.OnCheckItemList(mPhoneList) ;
                    AppController.getAppController().getInAppNotifier().showToast(" phnList length: "+ mPhoneList.size() );
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mPhone ;
        CheckBox mCheckBox ;
        CircleImageView mCircleImageView ;


        public ItemViewHolder(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.text_name);
            mPhone = itemView.findViewById(R.id.text_phone);
            mCheckBox = itemView.findViewById(R.id.checkbox);
            mCircleImageView = itemView.findViewById(R.id.circle_image);
        }

        public void bind(ListDataModel mItem) {
            mName.setText(mItem.getUsername());
            mPhone.setText(mItem.getPhone());
            AppImageLoader.loadImageInView(mItem.getImage(), R.drawable.smart_shop, mCircleImageView);


        }
    }
}