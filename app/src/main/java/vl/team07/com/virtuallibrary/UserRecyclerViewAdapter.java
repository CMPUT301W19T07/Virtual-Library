/*
 * Copyright <2019-1-23> <Ronghui Shao>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package vl.team07.com.virtuallibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<vl.team07.com.virtuallibrary.UserRecyclerViewAdapter.UserHolder>{

    public class UserHolder extends RecyclerView.ViewHolder {

        private TextView UsernameTextView, NameTextView, UserEmailTextView;
        private ImageView UserImage;

        public UserHolder(View itemView) {
            super(itemView);
            UsernameTextView = itemView.findViewById(R.id.UsernameTextView);
            NameTextView = itemView.findViewById(R.id.NameTextView);
            UserEmailTextView = itemView.findViewById(R.id.UserEmailTextView);

            UserImage = itemView.findViewById(R.id.UserImage);
        }

        public void setDetails(User user) {
            UsernameTextView.setText(user.getUserName());
            NameTextView.setText(user.getName());
            UsernameTextView.setText(user.getUserName());
        }
    }
    private Context context;
    private ArrayList<User> users;
    private View.OnClickListener onClickListener;

    public UserRecyclerViewAdapter(Context context, ArrayList<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(context).inflate(R.layout.user_card_view, viewGroup, false);
        UserHolder userHolder = new UserHolder(view);

        userHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { onClickListener.onClick(v); }
        });

        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        User user = users.get(i);
        userHolder.setDetails(user);
    }

    @Override
    public int getItemCount(){ return users.size();}

    public void setClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

}
