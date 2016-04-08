package com.qtparking.asynctaskdemo.demo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.qtparking.asynctaskdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    private ListView lvCourse;
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        lvCourse = (ListView) findViewById(R.id.lvCourse);

        new CourseTask().execute(URL);
    }

    class CourseTask extends AsyncTask<String,Void,List<Course>>{

        @Override
        protected List<Course> doInBackground(String... strings) {
            return getCourseList(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Course> courseList) {
            super.onPostExecute(courseList);
            CourseAdapter courseAdapter = new CourseAdapter(DemoActivity.this,courseList,lvCourse);
            lvCourse.setAdapter(courseAdapter);
        }
    }

    private List<Course> getCourseList(String url) {
        List<Course> courseList = new ArrayList<>();
        try {
            String json = parseStream(new URL(url).openStream());
            JSONObject jsonObject = new JSONObject(json);
            int status = jsonObject.getInt("status");
            String msg = jsonObject.getString("msg");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                Course course = new Course();
                course.setId(jsonObject.getInt("id"));
                course.setName(jsonObject.getString("name"));
                course.setPicSmall(jsonObject.getString("picSmall"));
                course.setPicBig(jsonObject.getString("picBig"));
                course.setDescription(jsonObject.getString("description"));
                course.setLearner(jsonObject.getInt("learner"));
                courseList.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    private String parseStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1){
            baos.write(buffer,0,len);
        }
        is.close();
        baos.close();
        return new String(baos.toByteArray(),"utf-8");
    }

}
