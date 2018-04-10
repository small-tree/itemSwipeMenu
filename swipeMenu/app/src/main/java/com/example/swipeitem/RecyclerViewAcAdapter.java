package com.example.swipeitem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IQB on 2018/4/10.
 */

public class RecyclerViewAcAdapter extends RecyclerView.Adapter<RecyclerViewAcAdapter.MyViewHolder> {

    ItemClickListener itemClickListener;
    ItemMenuClickListener itemMenuClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemMenuClickListener(ItemMenuClickListener itemMenuClickListener) {
        this.itemMenuClickListener = itemMenuClickListener;
    }

    @Override
    public RecyclerViewAcAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAcAdapter.MyViewHolder holder, int position) {
        initEvent(holder , position);
    }

    private void initEvent(MyViewHolder holder, final int position) {
        holder.menuLayout.setXCSwipMenuLayoutListener(new XCSwipMenuLayoutListener() {
            @Override
            public void onSliding(int distence) {

            }

            @Override
            public void onClickMenu() {
                if (itemMenuClickListener != null) {
                    itemMenuClickListener.onItemMenuClick(position);
                }
            }

            @Override
            public void onClick() {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public interface ItemClickListener {
         void onItemClick(int position);
    }

    public interface ItemMenuClickListener {
         void onItemMenuClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public XCSwipMenuLayout menuLayout;
        public XCMenuView menuView;

        public MyViewHolder(View itemView) {
            super(itemView);
            menuLayout = itemView.findViewById(R.id.xcsml_swipmenulayout);
            menuView = itemView.findViewById(R.id.xcmv_menuview);
        }
    }
}
