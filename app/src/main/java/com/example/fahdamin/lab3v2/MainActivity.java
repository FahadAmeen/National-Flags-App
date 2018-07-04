package com.example.fahdamin.lab3v2;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.ByteArrayOutputStream;

        import static android.R.attr.bitmap;

/**
 * Created by Vishal on 4/3/2017.
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMain;
    String [] countryNames={"Andorra","United Arab Emirates","Afghanistan","Antigua and Barbuda","Albania","Armenia","Angola","Argentina","Austria","Australia","Alberta","Bosnia and Herzegovina"};
    Bitmap[] logos = new Bitmap[12];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMain = (RecyclerView) findViewById(R.id.rvMain);

        logos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ad);
        logos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ae);
        logos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.af);
        logos[3] = BitmapFactory.decodeResource(getResources(), R.drawable.ag);
        logos[4] = BitmapFactory.decodeResource(getResources(), R.drawable.al);
        logos[5] = BitmapFactory.decodeResource(getResources(), R.drawable.am);
        logos[6] = BitmapFactory.decodeResource(getResources(), R.drawable.ao);
        logos[7] = BitmapFactory.decodeResource(getResources(), R.drawable.ar);
        logos[8] = BitmapFactory.decodeResource(getResources(), R.drawable.at);
        logos[9] = BitmapFactory.decodeResource(getResources(), R.drawable.au);
        logos[10] = BitmapFactory.decodeResource(getResources(), R.drawable.az);
        logos[11] = BitmapFactory.decodeResource(getResources(), R.drawable.ba);
        MyAdapter adapter = new MyAdapter(countryNames, logos);
        rvMain.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        rvMain.setAdapter(adapter);
    }



    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        String[] companyList;
        Bitmap[] logoList;

        public MyAdapter(String[] companyList, Bitmap[] logoList) {
            this.companyList = companyList;
            this.logoList = logoList;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.logo.setImageBitmap(logoList[position]);
            holder.logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "This is: " + companyList[position], Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,Main2Activity.class);

                    String sName=countryNames[position].toString();
                    Bitmap logo= logos[position];

                    i.putExtra("position",position);
                    i.putExtra("name",sName);
                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    logo.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                    byte[] byteArray = bStream.toByteArray();

                    i.putExtra("logo", byteArray);

                    startActivity(i);

                }
            });
            holder.name.setText(companyList[position]);
        }

        @Override
        public int getItemCount() {
            return companyList.length;
        }
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView logo;
        public TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView)itemView.findViewById(R.id.ivLogo);
            name = (TextView)itemView.findViewById(R.id.tvCompany);
        }
    }
}
