package kudwi.schoolex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by harishananth on 20/06/17.
 */

public class Areachooser extends AppCompatActivity {
    EditText search;
    ListView searchlist;
    String value;
    ImageView icon;
    ArrayAdapter adapter;
int x;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areachooser);
        Bundle extras=getIntent().getExtras();
        if(extras!=null)
        {
            x=extras.getInt("array_value");
        }
        icon=(ImageView)findViewById(R.id.searchimg);
        searchlist=(ListView)findViewById(R.id.searchlist);
        search=(EditText)findViewById(R.id.input);

        ArrayList<String> arrayarea=new ArrayList<>();
        switch(x)
        {
            case 1:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.girls_schools)));
                break;
            case 2:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.boys_schools)));

                break;
            case 3:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.coed_schools)));

                break;
            case 4:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.ib_schools)));

                break;
            case 5:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.igcse_schools)));

                break;
            case 6:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.icse_schools)));

                break;
            case 7:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.state_schools)));

                break;
            case 8:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.cbse_schools)));

                break;
            case 9:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.array_areas)));

                break;
            case 10:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.igcse_schools)));

                break;
            case 11:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.igcse_schools)));

                break;
            case 12:
                arrayarea.addAll(Arrays.asList(getResources().getStringArray(R.array.resedential)));

                break;



        }
        adapter=new ArrayAdapter<String>(Areachooser.this,android.R.layout.simple_list_item_1,arrayarea);
        searchlist.setAdapter(adapter);
        searchlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                value=searchlist.getItemAtPosition(i).toString();
                Toast.makeText(Areachooser.this,value,Toast.LENGTH_SHORT).show();
                if(x==9) {
                    Intent intent = new Intent(Areachooser.this, Areaselected.class);
                    intent.putExtra("area", value);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(Areachooser.this, schoolact.class);
                    intent.putExtra("school", value);
                    intent.putExtra("name",MainActivity.uname);
                    startActivity(intent);
                }

            }
        });
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value=search.getText().toString();

            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int i, int i1, int i2) {
                Areachooser.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
