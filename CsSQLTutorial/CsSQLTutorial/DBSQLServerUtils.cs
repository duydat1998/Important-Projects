using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;

namespace CsSQLTutorial
{
    class DBSQLServerUtils
    {
        public static SqlConnection
                GetDBConnection(string datasource, string database, string username, string password)
        {
            //
            // Data Source=DATNDSE63093\SQLEXPRESS;Initial Catalog=Demo_C#_Database;Persist Security Info=True;User ID=sa;Password=1
            //
            string connString = @"Data Source=" + datasource + ";Initial Catalog="
                        + database + ";Persist Security Info=True;User ID=" + username + ";Password=" + password;

            SqlConnection conn = new SqlConnection(connString);

            return conn;
        }
    }
}
