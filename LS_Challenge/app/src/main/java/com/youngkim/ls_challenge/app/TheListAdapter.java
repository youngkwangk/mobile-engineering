package com.youngkim.ls_challenge.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;

/**
 * Created by YoungKwang on 6/7/2014.
 */
public class TheListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ListItem> items;
    private Context context;

    public TheListAdapter(Context context,List<ListItem> obj) {
        super();
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.items = obj;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        ImageView smt_img;
        ImageView desc_img;
        TextView smt_name;
        TextView attr;
        TextView desc;
        FrameLayout info;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final ListItem rowItem = items.get(position);


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listitem, null);
            holder = new ViewHolder();

            holder.smt_img = (ImageView) convertView.findViewById(R.id.submitter_image);
            holder.smt_name = (TextView) convertView.findViewById(R.id.submitter);
            holder.attr = (TextView) convertView.findViewById(R.id.attr);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            holder.desc_img = (ImageView) convertView.findViewById(R.id.info_image);
            holder.info = (FrameLayout) convertView.findViewById(R.id.information);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        UrlImageViewHelper.setUrlDrawable(holder.smt_img, rowItem.getUser().getAvatar().getImage());
        UrlImageViewHelper.setUrlDrawable(holder.desc_img, rowItem.getImage(), R.drawable.placeholder);

        holder.smt_name.setText(rowItem.getUser().getName());
        holder.attr.setText(rowItem.getAttr());
        holder.desc.setText(rowItem.getDesc());

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(rowItem.getLink()));
                context.startActivity(i);
            }
        });

        return convertView;
    }

}
