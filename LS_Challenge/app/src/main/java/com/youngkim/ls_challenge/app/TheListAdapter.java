package com.youngkim.ls_challenge.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;

/**
 * Created by YoungKwang on 6/7/2014.
 */
public class TheListAdapter extends ArrayAdapter<ListItem> {

    public TheListAdapter(Context context, int resource, List<ListItem> obj) {
        super(context, resource, obj);
    }
    private class ViewHolder {
        ImageView smt_img;
        ImageView desc_img;
        TextView smt_name;
        TextView attr;
        TextView desc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ListItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem, null);
            holder = new ViewHolder();

            holder.smt_img = (ImageView) convertView.findViewById(R.id.submitter_image);
            holder.smt_name = (TextView) convertView.findViewById(R.id.submitter);
            holder.attr = (TextView) convertView.findViewById(R.id.attr);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            holder.desc_img = (ImageView) convertView.findViewById(R.id.info_image);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        UrlImageViewHelper.setUrlDrawable(holder.smt_img, rowItem.getUser().getAvatar().getImage());
        UrlImageViewHelper.setUrlDrawable(holder.desc_img, rowItem.getImage());

        holder.smt_name.setText(rowItem.getUser().getName());
        holder.attr.setText(rowItem.getAttr());
        holder.desc.setText(rowItem.getDesc());

        return convertView;
    }

}
