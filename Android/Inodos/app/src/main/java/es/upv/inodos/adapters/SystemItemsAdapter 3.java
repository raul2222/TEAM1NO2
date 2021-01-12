package es.upv.inodos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.upv.inodos.R;
import es.upv.inodos.data.SystemItem;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;


public class SystemItemsAdapter extends RealmBaseAdapter<SystemItem> {

    public SystemItemsAdapter(OrderedRealmCollection<SystemItem> realmResults) {
        super(realmResults);
    }

    private static class ViewHolder {
        TextView eventTextView;
        TextView timestampTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listview_system_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.eventTextView = (TextView) convertView.findViewById(R.id.eventTextView);
            viewHolder.timestampTextView = (TextView) convertView.findViewById(R.id.timestampTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            SystemItem item = adapterData.get(position);
            viewHolder.eventTextView.setText(item.getEvent());
            viewHolder.timestampTextView.setText(Long.toString(item.getTimestamp()));
        }

        return convertView;
    }

}
