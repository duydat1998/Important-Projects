using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;

namespace CsSQLTutorial
{
    class DBUtils
    {
        public static SqlConnection GetDBConnection()
        {
            string datasource = @"DATNDSE63093\SQLEXPRESS";

            string database = "Demo_C#_Database";
            string username = "sa";
            string password = "1";

            return DBSQLServerUtils.GetDBConnection(datasource, database, username, password);
        }
    }
}
