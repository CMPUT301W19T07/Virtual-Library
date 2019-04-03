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
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * The type Request list adapter.
 * A recyclerview adapter for Requests
 * @version 1.0
 * @since 1.0
 */
public class RequestListAdapter extends ArrayAdapter<Request> {

    private int resourceLayout;
    private Context mContext;

    /**
     * Instantiates a new Request list adapter.
     *
     * @param context  the context
     * @param resource the resource
     * @param items    the items
     */
    public RequestListAdapter(Context context, int resource, List<Request> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }


    /**
     * Shows the details of a request
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

//        Button acceptButton = (Button) findViewById(R.id.acceptButton);
//
//
//        acceptButton.setOnClickListener(myButtonClickListener);
//        View.OnClickListener myButtonClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View parentRow = (View) v.getParent();
//                ListView listView = (ListView) parentRow.getParent();
//                final int position = listView.getPositionForView(parentRow);
//            }
//        };
//        acceptButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                View parentRow = (View) v.getParent();
//                ListView listView = (ListView) parentRow.getParent();
//                final int position = listView.getPositionForView(parentRow);
//
//            }
//        });

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Request p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.bookTitle);
            TextView tt2 = (TextView) v.findViewById(R.id.Usename);
            TextView tt3 = (TextView) v.findViewById(R.id.Email);
            TextView tt4 = (TextView) v.findViewById(R.id.Address);

            if (tt1 != null) {
                tt1.setText(p.getRequestedBookTitle());
            }

            if (tt2 != null) {
                tt2.setText("requester Username: " +p.getRequesterUsername());
            }

            if (tt3 != null) {
                tt3.setText("requester Email: " +p.getRequesterEmail());
            }

            if (tt4 != null) {
                tt4.setText("requester Address: " +p.getRequesterAddress());
            }
        }

        return v;
    }

}

//cited from https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view which is answered by Rakhita on date 1th - Feb 2019
