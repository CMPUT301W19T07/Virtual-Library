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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by MTX on 2019-04-01.
 */

public class ScanBookInfo {

    private Book book;


    public ScanBookInfo(Book book){
        this.book = book;
    }

    public void showDialog(Context context){

        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.scan_book_detail);


        TextView title = dialog.findViewById(R.id.scan_book_title);
        TextView author = dialog.findViewById(R.id.scan_book_author);
        TextView des = dialog.findViewById(R.id.scan_book_des);
        des.setMovementMethod(new ScrollingMovementMethod());

        ImageView image = dialog.findViewById(R.id.scan_book_image);

        Button button = dialog.findViewById(R.id.btn_ok);

        title.setText("Title: "+ book.getTitle() + "\n");
        author.setText("Author: " + book.getAuthor() + "\n");
        des.setText("Description: \n"+book.getDescription());

        Context ctx = dialog.getContext();
        DatabaseHandler dh = DatabaseHandler.getInstance(ctx);
        dh.retrieveImageFromFirebase(book.getISBN(), image);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.SCAN_ISBN = null;
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
