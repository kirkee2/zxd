package com.hellmoney.thca.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.module.network.networkData.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private final String TAG = ItemAdapter.class.getName();
    private final Context mContext;
    private final List<Item> mItems;

    ItemAdapter(Context context, List<Item> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final Item item = mItems.get(position);

        holder.mItemNameTextView.setText(item.getItemName());
        holder.mItemInterestRateTypeTextView.setText(item.getInterestRateType());
        holder.mItemRepaymentTypeTextView.setText(item.getRepaymentType());
        holder.mItemMinInterestRateTextView.setText(item.getMinInterestRate().toString() + "%");
        holder.mItemMaxInterestRateTextView.setText(item.getMaxInterestRate().toString() + "%");
        holder.itemView.setTag(item.getItemId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailActivity.EXTRA_ITEM_ID, item.getItemId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name_text_view)
        protected TextView mItemNameTextView;
        @BindView(R.id.item_interest_rate_type_text_view)
        protected TextView mItemInterestRateTypeTextView;
        @BindView(R.id.item_repayment_type_text_view)
        protected TextView mItemRepaymentTypeTextView;
        @BindView(R.id.item_min_interest_rate_text_view)
        protected TextView mItemMinInterestRateTextView;
        @BindView(R.id.item_max_interest_rate_text_view)
        protected TextView mItemMaxInterestRateTextView;

        ItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
