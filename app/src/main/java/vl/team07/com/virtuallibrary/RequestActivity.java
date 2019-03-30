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
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class RequestActivity extends AppCompatActivity {
    private ListView RequestListView;
    private ArrayList<Request> RequestList;
    private ArrayAdapter<Request> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        RequestListView = (ListView) findViewById(R.id.RequestListView);
        RequestList = new ArrayList<Request>();
        TempList();
//        saveInFile();
//        loadFromFile();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                RequestList.get(0).acceptRequest();
                RequestList.clear();
                adapter.notifyDataSetChanged();
//                saveInFile();
            }
            if(resultCode == Activity.RESULT_CANCELED) {
                int result = Integer.parseInt(data.getStringExtra("PositionBack"));
                RequestList.remove(RequestList.get(result));
                adapter.notifyDataSetChanged();
//                saveInFile();
            }
        }
    }



    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        adapter = new RequestListAdapter(this,
                R.layout.list_request, RequestList);

        RequestListView.setAdapter(adapter);

        RequestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Request request = RequestList.get(position);
                String sendPosition = Integer.toString(position);
                Gson gson = new Gson();
                String type = gson.toJson(request);
                Intent intent = new Intent(RequestActivity.this, ConfirmRequest.class);
                intent.putExtra("GiveObject", type);
                intent.putExtra("position", sendPosition);
                startActivityForResult(intent, 1);

            }
        });
    }




        public void TempList(){

        User user1 = new User("user1", "Test name", "0", "email1", 0, "Canada", 0, "address1");
        User user2 = new User("user2", "Test name", "0", "email2", 0, "Canada", 0, "address2");
        User user3 = new User("user3", "Test name", "0", "email3", 0, "Canada", 0, "address3");

        Book testBook1 = new Book("First Book", "Second Author", 1234567890, user2, BookStatus.BORROWED, "Description","SSN",null);
        Request request1 = new Request(user1, testBook1);
        RequestList.add(request1);

        Request request2 = new Request(user2, testBook1);
        RequestList.add(request2);

        Request request3 = new Request(user3, testBook1);
        RequestList.add(request3);
    }

//    public void RejectClick(View view) {
//
//        RequestList.get(0).rejectRequest();
//        RequestList.remove(RequestList.get(0));
//        adapter.notifyDataSetChanged();
////        finish();
////        startActivity(getIntent());
//
//    }

//    private void loadFromFile() {
//
//        try {
//
//            FileReader in = new FileReader(new File(getFilesDir(),FILENAME));
//
//            Gson gson = new Gson();
//
//            // taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt on date 16th - Jan 2019
//
//            Type type = new TypeToken<ArrayList<Request>>(){}.getType();
//
//            RequestList = gson.fromJson(in, type);
//
//
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    //taken from lonelyTwitter of the lab
//    private void saveInFile() {
//        try {
//
//            FileWriter out = new FileWriter(new File(getFilesDir(),FILENAME));
//
//            Gson gson = new Gson();
//
//            gson.toJson(RequestList, out);
//            out.close();
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
