package org.demo;

import com.basiscomponents.bc.SqlTableBC;
import org.dwcj.bbj.database.JDBCConnection;

public class ChileCompanyContactsBC extends SqlTableBC {

    public ChileCompanyContactsBC() throws Exception {
        super(JDBCConnection.getJDBCConnection("ChileCompany"));
        setTable("CUSTOMER");
    }

}