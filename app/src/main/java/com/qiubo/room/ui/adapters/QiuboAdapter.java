package com.qiubo.room.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.qiubo.room.R;
import com.qiubo.room.entities.Developer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lawrence Cermeño on 20/03/18.
 */

public class QiuboAdapter extends RecyclerView.Adapter {

    public static final int FIRST_POSITION = 0;

    private List<Developer> mItems;
    private IOnClickListener mListener;

    public interface IOnClickListener {
        void deleteDeveloper(Developer developer);

        void bookmarkDeveloper(Developer developer);
    }

    public QiuboAdapter(IOnClickListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_developer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Developer item = mItems.get(position);
        viewHolder.mTxtVwItemName.setText(item.getName());
        if (item.isBookmark()) {
            viewHolder.mTxtVwItemName.append("[*]");
        }
        viewHolder.setListeners(item);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public void setItems(List<Developer> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void add(Developer developer) {
        if (mItems == null) {
            mItems = new ArrayList<>();
        }
        mItems.add(FIRST_POSITION, developer);
        notifyItemInserted(FIRST_POSITION);
    }

    public void update(Developer developer) {
        if (mItems != null) {
            int index = mItems.indexOf(developer);
            if (index != -1) {
                notifyItemChanged(index);
            }
        }
    }

    public void remove(Developer developer) {
        if (mItems != null) {
            int index = mItems.indexOf(developer);
            if (index != -1) {
                mItems.remove(index);
                notifyItemRemoved(index);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vw_item_root_view)
        View mRootView;
        @BindView(R.id.txt_vw_item_name)
        TextView mTxtVwItemName;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setListeners(Developer developer) {
            mRootView.setOnLongClickListener(view -> {
                showMenu(developer);
                return true;
            });
        }

        private void showMenu(Developer developer) {
            PopupMenu popup = new PopupMenu(mRootView.getContext(), mTxtVwItemName);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.item_actions, popup.getMenu());
            popup.getMenu().findItem(R.id.bookmark).setVisible(!developer.isBookmark());
            popup.getMenu().findItem(R.id.delete_bookmark).setVisible(developer.isBookmark());
            popup.show();
            popup.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.delete:
                        mListener.deleteDeveloper(developer);
                        break;
                    case R.id.bookmark:
                    case R.id.delete_bookmark:
                        mListener.bookmarkDeveloper(developer);
                        break;
                }
                return false;
            });
        }
    }
}
