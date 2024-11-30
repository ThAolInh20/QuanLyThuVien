package haui.edu.libmanagerhaui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

import haui.edu.libmanagerhaui.R;
import haui.edu.libmanagerhaui.fragment.TopFragment;
import haui.edu.libmanagerhaui.model.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment topFragment;
    ArrayList<Top> lists;
    TextView tvSach, tvSoLuong;
    ImageView imgDel;

    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> lists) {
        super(context, 0, lists);
        this.context = context;
        this.topFragment = topFragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = lists.get(position);
        if(item != null){
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Book: "+item.getTenSach());

            tvSoLuong = v.findViewById(R.id.tvSL);
            tvSoLuong.setText("Quantity: "+item.getSoLuong());
        }
        return v;
    }
}
