package foshol.company.com.foshol;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 9/2/2017.
 */

public class krishokGoodsList  extends ArrayAdapter<kGoodNode> {
    private Activity context;
    private List<kGoodNode> goodslist;

    public krishokGoodsList(Activity context, List<kGoodNode> goodslist) {
        super(context, R.layout.goods_list_layout,goodslist);
        this.context=context;
        this.goodslist = goodslist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.goods_list_layout,null,true);
        TextView textViewDate=(TextView)listViewItem.findViewById(R.id.textViewPrice);
        TextView textViewName=(TextView)listViewItem.findViewById(R.id.textViewName);

        kGoodNode goods = goodslist.get(position);
        textViewName.setText(goods.getName());
        textViewDate.setText(goods.getDate());

        return  listViewItem;

    }
}
