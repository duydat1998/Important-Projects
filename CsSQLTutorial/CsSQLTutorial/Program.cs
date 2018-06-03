using System;
using System.Data;
using System.Data.SqlClient;


namespace CsSQLTutorial
{
    class Program
    {
        private static SqlConnection conn = null;
        static void ReadData()
        {
            try
            {

                conn = DBUtils.GetDBConnection();
                if (conn != null)
                {
                    conn.Open();
                    string sql = "Select userID,name,age from tbl_User";
                    SqlCommand cmd = new SqlCommand(sql, conn);

                    using (SqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.HasRows)
                        {
                            while (reader.Read())
                            {
                                int idIndex = reader.GetOrdinal("userID");
                                int nameIndex = reader.GetOrdinal("name");
                                string userID = reader.GetString(idIndex);
                                string name = reader.GetString(nameIndex);
                                int age = reader.GetInt32(2);
                                Console.WriteLine("User Id: " + userID);
                                Console.WriteLine("Name: " + name);
                                Console.WriteLine("Age: " + age);
                                Console.WriteLine("-----------------------");
                            }
                        }
                    }

                }

            }
            catch (SqlException)
            {
                Console.WriteLine("Error when connect to database");
            }
            finally
            {
                if (conn != null)
                {
                    conn.Close();
                }

            }
        }
        static void Insert()
        {
            using (conn = DBUtils.GetDBConnection())
            {
                if(conn != null)
                {
                    conn.Open();
                    string sql = "Insert into tbl_User(userID,name,age) values(@userID,@name,@age)";
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.Parameters.Add("@userID", SqlDbType.NVarChar).Value="3";
                    cmd.Parameters.Add("@name", SqlDbType.NVarChar).Value = "Thi Phuong";
                    cmd.Parameters.Add("@age", SqlDbType.Int).Value = 23;
                    int count = cmd.ExecuteNonQuery();
                    if(count > 0)
                    {
                        Console.WriteLine(" Insert Successfully");
                    }
                }
            }
        }
        static void Update()
        {
            using (conn = DBUtils.GetDBConnection())
            {
                if (conn != null)
                {
                    conn.Open();
                    string sql = "Update tbl_User set name=@name where userID=@userID";
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.Parameters.Add("@userID", SqlDbType.NVarChar).Value = "1";
                    cmd.Parameters.Add("@name", SqlDbType.NVarChar).Value = "Nguyen Duy Dat";
                    int count = cmd.ExecuteNonQuery();
                    if (count > 0)
                    {
                        Console.WriteLine(" Update Successfully");
                    }
                }
            }
        }
        static void Main(string[] args)
        {
            Console.WriteLine("Before insert and update");
            ReadData();
            Insert();
            Console.WriteLine("After inserting");
            ReadData();
            Update();
            Console.WriteLine("After update");
            ReadData();
            
           
        }
    }
}
