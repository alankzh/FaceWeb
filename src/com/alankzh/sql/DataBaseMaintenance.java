package com.alankzh.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alankzh.annotation.Immutable;
import com.alankzh.dao.ConnectionPool;

/**
 * 数据库基础维护
 * @author alankzh
 * 当前版本：1
 */
@Immutable
public class DataBaseMaintenance {
    private static final DataBaseVersion version=DataBaseVersion.Version_1;//数据库当前版本
    
    /**
     * 建立数据库表
     */
    public static synchronized void establish() {
        Connection connection=ConnectionPool.getConnection();
        ResultSet resultSet=null;
        Statement statement=null;
        try {
            DatabaseMetaData databaseMetaData=connection.getMetaData();
            resultSet=databaseMetaData.getTables(connection.getCatalog(), "root", null,new String[] {"TABLE"});
            boolean hasEstablish=false;
            while(resultSet.next()) {
                
                if(resultSet.getString("TABLE_NAME").equals(TableNames.DEPARTMENT_TABLE_NAME)) {
                    hasEstablish=true;
                    break;
                }
            }
            
            if(hasEstablish) {
                update();
                return;
            }else {
                statement=connection.createStatement();
                boolean success=false;
                success=statement.execute(CreateTableSQL.CREATE_TABLE_DEPARTMENT_SQL);
                System.out.println(success);
                success=statement.execute(CreateTableSQL.CREATE_TABLE_DEPARTMENT_RELATION_SQL);
                System.out.println(success);
                success=statement.execute(CreateTableSQL.CREATE_TABLE_EMPLOYEE_SQL);
                System.out.println(success);
                success=statement.execute(CreateTableSQL.CREATE_TABLE_EMPLOYEE_ALTERATION_SQL);
                System.out.println(success);
                success=statement.execute(CreateTableSQL.CREATE_TABLE_CLOCKING_TEMPLATE_SQL);
                System.out.println(success);
                success=statement.execute(CreateTableSQL.CREATE_TABLE_EMPLOYEE_CLOCKING_ARRANGE_SQL);
                System.out.println(success);
                
                success=connection.createStatement().execute(CreateTableSQL.CREATE_TABLE_ATTENDANCE_RECORD_SQL);
                System.out.println(success);
                
            }
            
        } catch (SQLException e) {
            System.out.println("分析数据库结构出错");
            e.printStackTrace();
        }finally{
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(statement!=null) {
                    statement.close();   
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void update() {
        switch(version) {
        case Version_1:
            //初始版本即为1，不做任何数据库升级
            break;
        }
    }
}
