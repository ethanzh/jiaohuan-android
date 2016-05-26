package com.jiaohuan.jiaohuan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class FakeDatabase {
    
    private static FakeDatabase ourInstance = new FakeDatabase();

    public static FakeDatabase getInstance() {
        return ourInstance;
    }

    private List<Contact> unsortedData;
    private static Contact myData;
    private List<Contact> fullyAlphaData;
    private List<Contact> fullyUnixData;

    // This is just fake data, it will eventually be replaced with calls to the server
    private FakeDatabase()  { // Maybe throw exception here?

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());

        Bitmap nyu = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.card_nyu);

        Bitmap flip = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.card_flip);

        Bitmap pp_xia = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.xia);

        Bitmap pp_ethan = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.pp_ethan);

        Bitmap pp_wookie = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.lin);

        Bitmap pp_tiange = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.pp_tiange);
        
        Bitmap pp_lin = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.lin);

        Bitmap pp_ma = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), R.drawable.ma);
        
        // My own data
        myData = new Contact(15435876, "李友", "Starwood Hotels", "+8615811556736", "nongxia@starwood.cn", "Beijing, China",
                pp_xia, "龙湖滟澜山", "", nyu, "CTO",
                "starwood.cn", -12303292 , flip, 1366038215, null, null);

        // Create a new ArrayList, then add each row to it individually
        unsortedData = new ArrayList<>();

        Contact row1 = new Contact(65973147, "王朋", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                pp_wookie, "北京望京", "很喜欢游泳", nyu, "CEO",
                "http://www.baidu.com/", -12303292, flip, 1366211015, null, null);

        Contact row2 = new Contact(14379862,"王小明", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                pp_tiange, "龙湖滟澜山", "力学是我最爱的课", nyu, "CEO",
                "http://www.baidu.com/", -12303292 , flip, 1260732717, null, null);

        Contact row3 = new Contact(75135972, "刘天哥", "CCP", "+86+8615811556736", "LTG@gmail.com", "Beijing, China",
                pp_lin, "Capital Paradise", "去过法国三次", nyu, "CFO",
                "https://www.baidu.com/", -12303292, flip, 1260342717, null, null);

        Contact row4 = new Contact(64793125, "王绍明", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                pp_lin, "北京望京", "很喜欢游泳", nyu, "CEO",
                "http://www.baidu.com/", -12303292 , flip, 1260346717, null, null);

        Contact row5 = new Contact(75431597, "郭岛礼", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                pp_xia, "龙湖滟澜山", "力学是我最爱的课", nyu, "CTO",
                "http://www.baidu.com/", -12303292, flip, 1220346717, null, null);

        Contact row6 = new Contact(32136985, "妈妈", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                pp_ma, "Capital Paradise", "去过法国三次", nyu, "CFO",
                "http://www.baidu.com/", -12303292, flip, 1120346717, null, null);

        Contact row7 = new Contact(98653265, "马宁", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                pp_lin, "北京望京", "很喜欢游泳", nyu, "CEO",
                "http://www.baidu.com/", -12303292, flip, 1120526717, null, null);

        Contact row8 = new Contact(44556213, "爸爸", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                pp_xia, "龙湖滟澜山", "力学是我最爱的课", nyu, "CTO",
                "http://www.baidu.com/", -12303292, flip, 1120546727, null, null);

        Contact row9 = new Contact(78543264, "马克", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                pp_ma, "Capital Paradise", "去过法国三次", nyu, "CFO",
                "http://www.baidu.com/", -12303292, flip, 1120543727, null, null);

        Contact row10 = new Contact(96325653, "魔月", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                pp_lin, "北京望京", "很喜欢游泳", nyu, "CEO",
                "http://www.baidu.com/", -12303292 , flip, 1122123727, null, null);

        Contact row11 = new Contact(74859645, "郭熬", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                pp_xia, "龙湖滟澜山", "力学是我最爱的课", nyu, "CTO",
                "http://www.baidu.com/", -12303292, flip, 1122123437, null, null);

        Contact row12 = new Contact(22532658, "本附近", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                pp_ma, "Capital Paradise", "去过法国三次", nyu, "CFO",
                "http://www.baidu.com/", -12303292, flip, 1121723437, null, null);

        Contact row13 = new Contact(96485969, "陈老师", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                pp_lin, "北京望京", "很喜欢游泳", nyu, "CEO",
                "http://www.baidu.com/", -12303292 , flip, 1472723437, null, null);

        Contact row14 = new Contact(14741214, "力王", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                pp_xia, "龙湖滟澜山", "物理是我最爱的课", nyu, "CTO",
                "http://www.baidu.com/", -12303292, flip, 1172723137, null, null);

        Contact row15 = new Contact(78976542, "模特", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                pp_ma, "Capital Paradise", "去过法国三次", nyu, "CFO",
                "http://www.baidu.com/", -12303292, flip, 157274337, null, null);

        Contact row16 = new Contact(13696454, "哦所", "Gate Education", "+8615811556736", "SWP@hotmail.com", "Beijing, China",
                pp_lin, "北京望京", "很喜欢游泳", nyu, "CEO",
                "http://www.baidu.com/", -12303292 , flip, 157234337, null, null);

        Contact row17 = new Contact(12967489, "懂你", "Jiao Huan Inc.", "+8615811556736", "e@gmail.com", "Beijing, China",
                pp_xia, "龙湖滟澜山", "力学是我最爱的课", nyu, "CTO",
                "http://www.baidu.com/", -12303292, flip, 137234335, null, null);

        Contact row18 = new Contact(46879321, "挖聪", "CCP", "+8615811556736", "LTG@gmail.com", "Beijing, China",
                pp_ma, "Capital Paradise", "去过法国三次", nyu, "CEO",
                "http://www.baidu.com/", -12303292, flip, 198534335, null, null);

        // Add the data
        unsortedData.add(row1);
        unsortedData.add(row2);
        unsortedData.add(row3);
        unsortedData.add(row4);
        unsortedData.add(row5);
        unsortedData.add(row6);
        unsortedData.add(row7);
        unsortedData.add(row8);
        unsortedData.add(row9);
        unsortedData.add(row10);
        unsortedData.add(row11);
        unsortedData.add(row12);
        unsortedData.add(row13);
        unsortedData.add(row14);
        unsortedData.add(row15);
        unsortedData.add(row16);
        unsortedData.add(row17);
        unsortedData.add(row18);

        try {
            setPinyinValue(unsortedData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fullyAlphaData = new ArrayList<>();
        fullyAlphaData = SortByNames(unsortedData);

        convertFromUnix(fullyAlphaData);

        fullyUnixData = new ArrayList<>();
        fullyUnixData = SortByUnix(fullyAlphaData);

        addToDir(fullyAlphaData);

        addOwnContact(myData);

    }

    public void addOwnContact(Contact me){

        String nameOfFolder = Integer.toString(me.getID());

        Bitmap currentBitmap = me.getBusiness_card();

        String conName = Environment.getExternalStorageDirectory() + File.separator + "Jiaohuan" + File.separator +
                "My Account" + File.separator + nameOfFolder;

        File conDir = new File(conName);

        if (!conDir.mkdirs()) {
            if (conDir.exists()) {
            } else {
                return;
            }
        }

        Log.d("IMAGE", "" + imageExists(true, me.getID()));

        if(!imageExists(true, me.getID())){

            try {
                FileOutputStream fos = new FileOutputStream(conName + File.separator + me.getID() +".jpg", true);
                currentBitmap.compress(Bitmap.CompressFormat.JPEG, 25, fos);

                fos.flush();
                fos.close();
            } catch (Exception e) {
                Log.e("MyLog", e.toString());
            }
        }

        File txtDir = new File(conName);

        try{
            OutputStream stream = new FileOutputStream(txtDir + File.separator + "information.txt");
            OutputStreamWriter writer = new OutputStreamWriter(stream);

            writer.write(me.getName());
            writer.write("\n");
            writer.write(me.getEmail());
            writer.write("\n");
            writer.write(me.getPhoneNum());
            writer.write("\n");
            writer.write(me.getLocation());

            writer.close();
            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addToDir(List<Contact> list){

        for(int i = 0; i < list.size(); i++){

            String nameOfFolder = Integer.toString(list.get(i).getID());

            Bitmap currentBitmap = list.get(i).getBusiness_card();

            String conName = Environment.getExternalStorageDirectory() + File.separator + "Jiaohuan" + File.separator +
                    "Connected Accounts" + File.separator + nameOfFolder;

            File conDir = new File(conName);

            if (!conDir.mkdirs()) {
                if (conDir.exists()) {
                } else {
                    return;
                }
            }

            Log.d("DIR", "" + imageExists(false, list.get(i).getID()));

            if(!imageExists(false, list.get(i).getID())){

                try {
                    FileOutputStream fos = new FileOutputStream(conName + File.separator + list.get(i).getID() +".jpg", true);
                    currentBitmap.compress(Bitmap.CompressFormat.JPEG, 25, fos);

                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    Log.e("MyLog", e.toString());
                }
            }
        }
    }

    public Boolean imageExists(boolean myOwn, int id){

        String nameOfFolder = Integer.toString(id);

        String conName;

        if(myOwn){
            conName = Environment.getExternalStorageDirectory() + File.separator + "Jiaohuan" + File.separator +
                    "My Account" + File.separator + nameOfFolder + File.separator + id + ".jpg";
        }else{
            conName = Environment.getExternalStorageDirectory() + File.separator + "Jiaohuan" + File.separator +
                    "Connected Accounts" + File.separator + nameOfFolder + File.separator + id + ".jpg";
        }

        File file = new File(conName);

        if(file.exists()){
            return true;
        } else{
            return false;
        }
    }

    public void convertFromUnix(List<Contact> list){
        for(int i = 0; i < list.size(); i++){
            long unixSeconds = list.get(i).getUnix_time();
            Date date = new Date(unixSeconds * 1000L);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

            String formattedDate = sdf.format(date);
            list.get(i).setSimpleDate(formattedDate);
        }
    }

    public List<Contact> SortByUnix(List<Contact> unsortedData){

        List<Contact> sortedData = new ArrayList<>();

        for (int i = 0; i < unsortedData.size(); i++) {
            Contact user_temp = unsortedData.get(i);
            sortedData.add(user_temp);
        }

        SortUnixTime comparator = new SortUnixTime();
        Collections.sort(sortedData, comparator);


        return sortedData;
    }

    public List<Contact> SortByNames(List<Contact> unsortedData){
        List<Contact> sortedData = new ArrayList<>();

        SortAlphaNames comparator = new SortAlphaNames();
        Collections.sort(unsortedData, comparator);

        for (int i = 0; i < unsortedData.size(); i++) {
            Contact user_temp = unsortedData.get(i);
            sortedData.add(user_temp);
        }
        return sortedData;
    }

    public List<Contact> getAlphaSorted() {
        return fullyAlphaData;
    }

    public List<Contact> getDateSorted() {
        return fullyUnixData;
    }

    // Return my data
    public static Contact getMyData() {return myData;}

    // Get pinyin names
    public void setPinyinValue(List<Contact> list) throws IOException {

        Map<String, String> data = readFromAssets(MyApplication.getContext(), "pinyin.txt");

        for(int i = 0; i < list.size(); i++){

            String chineseName = list.get(i).getName();
            StringBuilder fullPinyin = new StringBuilder();

            for(int j = 0; j < chineseName.length(); j++){
                String character = list.get(i).getName().substring(j, j + 1);
                char single = character.charAt(0);
                String myhex = String.format("%04X", (int)single);

                String pinyin = data.get(myhex);
                fullPinyin.append(pinyin);

            }
            //Log.wtf("PINYIN", chineseName + " : " + fullPinyin.toString());
            list.get(i).setPinyin(fullPinyin.toString());
        }
    }

    public static Map<String, String> readFromAssets(Context context, String filename) throws IOException {
        long startTime = System.nanoTime();
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        String conName = Environment.getExternalStorageDirectory() + File.separator + "Jiaohuan" + File.separator + "hashmap.ser";
        File conDir = new File(conName);

        // do reading, usually loop until end of file reading
        String mLine;
        Map<String, String> data = new HashMap<>();
        Character lastChar = null;

        if (conDir.exists()) {
            Log.wtf("EXISTS", "Using existing map");
            try
            {
                FileInputStream fis = new FileInputStream(conDir);
                ObjectInputStream ois = new ObjectInputStream(fis);
                data = (HashMap) ois.readObject();
                ois.close();
                fis.close();
            }catch(IOException ioe)
            {
                ioe.printStackTrace();
                return null;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Class not found");
                c.printStackTrace();
                return null;
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            Log.wtf("TIME", duration + "");
        }
        else{
            Log.wtf("DOESNT", "making new map");
            for(int i = 0; i < 20982; i++){

                mLine = reader.readLine();

                String hex = mLine.substring(0,4);
                String pinyin = mLine.substring(6);

                pinyin = pinyin.substring(0, pinyin.length() - 2);

                while (pinyin.contains(",")){
                    pinyin = pinyin.split(",", 2)[0];
                }

                if (pinyin.length() > 1){
                    lastChar = pinyin.charAt(pinyin.length() - 1);
                }

                String finalChar = String.valueOf(lastChar);

                if (finalChar.matches(".*[0-9].*")){
                    pinyin = pinyin.substring(0, (pinyin.length() - 1));
                }

                data.put(hex, pinyin);
            }
            try {
                FileOutputStream fos = new FileOutputStream(conName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(data);
                oos.close();
                fos.close();

            }catch(IOException ioe)
            {
                ioe.printStackTrace();
            }

            reader.close();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            Log.wtf("TIME", duration + "");
        }


        return data;
    }
}

