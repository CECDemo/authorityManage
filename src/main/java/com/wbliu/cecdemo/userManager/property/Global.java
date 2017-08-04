package com.wbliu.cecdemo.userManager.property;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class Global {



    private static Properties prop = new Properties();

    static {
        try {
            java.net.URL i = Global.class.getResource("/");
            InputStream in = new BufferedInputStream(new FileInputStream(i.getPath() + "application.properties"));
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //General
    public static int PAGESIZE = 50;
    public static final int ANALYSISPAGESIZE = 5001;
    public static int MAX_DOWNLOAD_SIZE = 30000;
    public static final int BATCHANALYSISPAGESIZE = 10002;
    public static final int MAXRESULTSIZE = 50000;
    public static final int GMTTIMEDIFF = 16;
    //public static final String WORKFLOWRESULTFILEPEFIX = "csv";
    public static final String WORKFLOWRESULTFILEPEFIX = "xls";
    public static final String[] DeleteFromChart = {"IVR","空"};


    public static final int CACHE_EXPIRE_DAY = 30;

    //Mysql
    public static String DatabaseHost = prop.getProperty("jdbc.host");
    public static String DatabaseUser = prop.getProperty("jdbc.user");
    public static String DatabasePassword = prop.getProperty("jdbc.password");
    public static String DriverClassName = prop.getProperty("jdbc.driverClassName");


    public  static String UserIsEnabled  = prop.getProperty("user.isEnabled");



    public static final String DatabaseName = "analysisplatform";
    public static final String WorkflowTableName = "workflow";
    public static final String WorkflowResultTableName = "unionsearchresult";
    public static final String FullColumnTableName = "callcolumninfo";
    public static final String UserDatabaseName = "loginsecurity";
    public static final String RoleInfoTableName = "roles";
    public static final String MenuInfoTableName = "usermenu";
    public static final String UserInfoTableName = "users";
    public static final String CallReasonMappingTable = "DimCallReason";
    public static final String TransCodeMappingTable = "DimTransaction";
    public static final String MysqlPathInfoTable = "mysqlpathinfo";
    public static final String OPGroupTable = "DimOPGroup";
    public static final String OPLocationTable = "DimOPLocation";
    public static final String OPTeamTable = "DimOPType";
    public static final String CallTypeTable = "DimCallType";
    public static final String WorksheetTypeTable = "DimWorksheetType";
    public static final String CustomAuthTypeTable = "DimCertType";
    public static final String DuplicateCallTypeTable = "DimDuplicateCallType";
    public static final String StepPrefix = "step";
    public static final String AverageLineName = "平均值";
    public static final String RateName = "比率";
    public static final String CountName = "计数";

    //ElasticSearch

    public static String ESIP = prop.getProperty("es.ip");

    public static String[] ESIP_ARRAY_BACKUP = {"10.18.7.97"};
    public static String[] ESIP_ARRAY = {"10.18.7.97", "10.18.7.97", "10.18.7.97", "10.18.7.97", "10.18.7.97"};


    public static String getRandomEsiP() {
        Random random = new Random();
        int index = random.nextInt(ESIP_ARRAY.length);
        System.out.println("find es ip:" + ESIP_ARRAY[index] + "--index:" + index);
        return ESIP_ARRAY[index];
    }

    public static String ESindexName = prop.getProperty("es.index");
    public static String ESclusterName = prop.getProperty("es.clusterName");

    public static final int ESport = Integer.parseInt(prop.getProperty("es.port")); //9300;
    public static final String EStypeName ="callrecord";

    // Envis
    public static final String EnvisIp = prop.getProperty("envis.ip");
    public static final String EnvisPort = prop.getProperty("envis.port");

    //Download
    public static final String DownloadFolder = prop.getProperty("DownloadFolder");
    public static final String ProcessingFolder = prop.getProperty("ProcessingFolder");

    public static final String DownloadPrefix = "downloadfile";

    //Security
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ROLE_ADMIN_WORKFLOW = "ROLE_ADMIN_WF";

    //add by fcbai
    //sort column
    public static final String SORT_DESC = "desc";
    public static final String SORT_ASC = "asc";
    public static final Integer MAX_NUMBER_OF_SORT = 5000;
    //end


    //add by wbliu
    public static final String PREFIXROLENAME =prop.getProperty("prefixRoleName") ;
    public static final java.lang.String DEFAULTROLELEVEL = prop.getProperty("defultRoleLevel");
    public static final String SUPERADMINNAME = prop.getProperty("SuperAdminName");


}
