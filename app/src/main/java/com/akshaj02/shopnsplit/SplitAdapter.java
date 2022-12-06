package com.akshaj02.shopnsplit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SplitAdapter extends RecyclerView.Adapter<SplitAdapter.ViewHolder> {

    private List<SplitModel> mList;

    public SplitAdapter(List<SplitModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String expense = mList.get(position).getExpense();
        String date = mList.get(position).getDate();
        String moneyOwed = mList.get(position).getMoneyOwed();
        String moneyPaid = mList.get(position).getMoneyPaid();

        holder.setData(expense, date, moneyOwed, moneyPaid);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView splitDate;
        private TextView splitMoneyOwed;
        private TextView splitMoneyPaid;
        private TextView splitExpense;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            splitDate = itemView.findViewById(R.id.date);
            splitMoneyOwed = itemView.findViewById(R.id.moneyOwed);
            splitMoneyPaid = itemView.findViewById(R.id.moneyPaid);
            splitExpense = itemView.findViewById(R.id.expense);
        }

        public void setData(String expense, String date, String moneyOwed, String moneyPaid) {
            splitDate.setText(date);
            splitMoneyOwed.setText(moneyOwed);
            splitMoneyPaid.setText(moneyPaid);
            splitExpense.setText(expense);
        }
    }

}