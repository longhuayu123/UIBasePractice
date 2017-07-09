package com.example.administrator.uibasepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText editText;
    private Button send;
    private RecyclerView msgRecycleView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();
        editText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecycleView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(linearLayoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecycleView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecycleView.scrollToPosition(msgList.size());
                    editText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("hello", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("hi", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("bai", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
