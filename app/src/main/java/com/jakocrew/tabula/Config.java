package com.jakocrew.tabula;

import android.content.Context;
import android.net.ParseException;


import com.jakocrew.tabula.util.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Config extends Properties {

    private static final String LOG_TAG = "Config";

    private static final long serialVersionUID = 201004210529L;

    private String PACKAGE = "com.jakocrew.tabula";

    private String VERSION = "1.0.0";

    private String FILE_PATH = "/data/data/" + PACKAGE + "/tabula.config." + VERSION + ".config";

    private static Config poThis = new Config();


    private static final String SYSCONFIG_NICKNAME = "prid"; //푸쉬 등록 id




    private Config() {
        loadConfig();
    }

    public static Config getInstance() {
        return poThis;
    }

    private void saveConfig() {

        try {
            OutputStream poOutStream = new FileOutputStream(FILE_PATH);
            this.store(poOutStream, "SYSTEM CONFIG");

            poOutStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        setProperty(SYSCONFIG_NICKNAME, "");
        saveConfig();
    }

    private void loadConfig() {
        Util.iLog(LOG_TAG, "loadConfig");
        try {
            InputStream is = new FileInputStream(FILE_PATH);
            this.load(is);
            //	loadFromXML(is);
            is.close();

        } catch (FileNotFoundException e) {
            saveDefaultConfig();

            try {
                InputStream is2;
                is2 = new FileInputStream(FILE_PATH);
                try {
                    //		loadFromXML(is2);
                    this.load(is2);
                    is2.close();

                } catch (InvalidPropertiesFormatException e1) {
                    e1.printStackTrace();

                } catch (IOException e1) {
                    e1.printStackTrace();

                }

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void setNickName(String val) {
        setProperty(SYSCONFIG_NICKNAME, val);
        saveConfig();
    }

    public String getNickName() {
        return getProperty(SYSCONFIG_NICKNAME);
    }




}
