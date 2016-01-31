package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mapletree.zihover.familytodo.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by yonghak on 2016-01-25.
 */
public class ExpenseAdapter extends RealmBaseAdapter<Expense> implements ListAdapter {

    /*private static class MyViewHolder {
        TextView name;
    }*/

    public ExpenseAdapter(Context context, int resId,
                     RealmResults<Expense> realmResults,
                     boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_fruit, parent, false);
        }

        Expense item = realmResults.get(position);


        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.title);
        TextView tvCard = (TextView) convertView.findViewById(R.id.card);

        TextView tvDate = (TextView) convertView.findViewById(R.id.date);
        TextView tvValue = (TextView) convertView.findViewById(R.id.value);

        // Populate the data into the template view using the data object
        //tvTitle.setText(Integer.toString(item.getId()));
        tvTitle.setText(item.getPlace());
        tvCard.setText(item.getCard());

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy MM/dd HH:mm");//dd/MM/yyyy
        Date targetDate = null;
        try {
            targetDate = sdfDate.parse(item.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat compareSdf = new SimpleDateFormat("yyyy MM/dd");
        Date date = new Date();
        String strToday = compareSdf.format(date);
        String strTargetDay = compareSdf.format(targetDate); //item.getDate().substring(0,10);

        String showDate;
        if(strTargetDay.equals(strToday)){
            //오늘이면  시간을 보여준다.
            //SimpleDateFormat timeSdf = new SimpleDateFormat("MM/dd");
            showDate = item.getDate().substring(11);
        }else{


            //오늘 이전 날짜이면  날짜를 보여준다.
            //SimpleDateFormat daySdf = new SimpleDateFormat("MM/dd");
            showDate = item.getDate().substring(5,10);
        }



        tvDate.setText(showDate);

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        tvValue.setText(nf.format(item.getValue()));

        //ViewHolder viewHolder;
        /*if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }*/


        //viewHolder.name.setText(item.getName());
        return convertView;
    }

    public RealmResults<Expense> getRealmResults() {
        return realmResults;
    }
}
