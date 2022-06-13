package team2.mobileapp.gplx.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.dto.QuestionDetails;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;

class QuestionListViewAdapter extends ArrayAdapter<QuestionDetails> {
    private Context context;
    private ArrayList<QuestionDetails> arrayList;
    int layoutResource;

    public QuestionListViewAdapter(Context context, int resource, ArrayList<QuestionDetails>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layoutResource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.question_view_list_item, null,
                        false);
        TextView tvQuestion = (TextView) convertView.findViewById(R.id.tv_question_item_listview);
        TextView tvResult = (TextView) convertView.findViewById(R.id.tv_result_item_listview);
        TextView tvListAnswer = (TextView) convertView.findViewById(R.id.tv_list_answer);
        String listAnswer = "";
        int index = arrayList.indexOf(arrayList.get(position));
        int lenList = arrayList.get(position).getAnswer().getAnswerName().length;
        for (int i = 0; i < lenList; i++) {
            String item = arrayList.get(position).getAnswer().getAnswerByIndex(i);
            listAnswer += (i + 1) + " - " + item + (i == lenList - 1 ? "" : "\n");
        }


        int positionResult = arrayList.get(position).getAnswer().getResult();
        tvListAnswer.setText(listAnswer);
        tvQuestion.setText("Câu " + (index + 1) + ". " + arrayList.get(position).getQuestion().getQuery());
        tvResult.setText(arrayList.get(position).getAnswer().getResult() + 1 + " - " + arrayList.get(position).getAnswer().getAnswerByIndex(positionResult));
        if (!arrayList.get(position).getQuestion().getPhoto().isEmpty()) {
            try {
                ImageView ivBoardPhoto = (ImageView) convertView.findViewById(R.id.iv_board);
                String photo = arrayList.get(position).getQuestion().getPhoto();

                if(photo.trim().length()>5) {
                    ivBoardPhoto.setVisibility(View.VISIBLE);
                    String uri = VariableGlobal.PHOTO1 + VariableGlobal.typeCode + VariableGlobal.PHOTO2 + photo + VariableGlobal.PHOTO3 + VariableGlobal.Token;
                    Picasso.get()
                            .load(uri)
                            .placeholder(com.wooplr.spotlight.R.drawable.ic_spotlight_arc)
                            .error(com.wooplr.spotlight.R.drawable.ic_spotlight_arc)
                            .fit()
                            .into(ivBoardPhoto);
                }else{
                    ivBoardPhoto.setVisibility(View.GONE);
                }

            } catch (Exception ex) {
                Log.i("Error", "Image not exits ");
            }
        }

// Get item
        return convertView;
    }
}