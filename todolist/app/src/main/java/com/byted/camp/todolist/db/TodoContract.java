package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE note(_id INTEGER PRIMARY KEY AUTOINCREMENT, date INTEGER, state INTEGER, content TEXT)";


    private TodoContract() {}

    public static class Todo implements BaseColumns{
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_STATE = "state";
        public static final String COLUNM_NAME = "note";
        public static final String COLUNM_PRIORITY = "priority";
    }


}
