USE [master]
GO
/****** Object:  Database [DeliveryManagement]    Script Date: 18/03/2018 15:04:18 ******/
CREATE DATABASE [DeliveryManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DeliveryManagement', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQL_EXPRESS\MSSQL\DATA\DeliveryManagement.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DeliveryManagement_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQL_EXPRESS\MSSQL\DATA\DeliveryManagement_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DeliveryManagement] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DeliveryManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DeliveryManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DeliveryManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DeliveryManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DeliveryManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DeliveryManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [DeliveryManagement] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DeliveryManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DeliveryManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DeliveryManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DeliveryManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DeliveryManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DeliveryManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DeliveryManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DeliveryManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DeliveryManagement] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DeliveryManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DeliveryManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DeliveryManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DeliveryManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DeliveryManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DeliveryManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DeliveryManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DeliveryManagement] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DeliveryManagement] SET  MULTI_USER 
GO
ALTER DATABASE [DeliveryManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DeliveryManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DeliveryManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DeliveryManagement] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [DeliveryManagement] SET DELAYED_DURABILITY = DISABLED 
GO
USE [DeliveryManagement]
GO
/****** Object:  Table [dbo].[tbl_book]    Script Date: 18/03/2018 15:04:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_book](
	[bookID] [varchar](10) NOT NULL,
	[title] [varchar](50) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_tbl_book] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_customer]    Script Date: 18/03/2018 15:04:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_customer](
	[custID] [varchar](10) NOT NULL,
	[password] [varchar](30) NOT NULL,
	[custName] [varchar](15) NOT NULL,
	[lastName] [varchar](15) NOT NULL,
	[middleName] [varchar](15) NOT NULL,
	[address] [varchar](250) NOT NULL,
	[phone] [varchar](11) NOT NULL,
	[custLevel] [int] NOT NULL,
 CONSTRAINT [PK_tbl_Customer] PRIMARY KEY CLUSTERED 
(
	[custID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_order]    Script Date: 18/03/2018 15:04:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_order](
	[orderID] [varchar](10) NOT NULL,
	[orderDate] [datetime] NOT NULL,
	[custID] [varchar](10) NOT NULL,
	[total] [float] NOT NULL,
	[isDeliver] [bit] NOT NULL,
	[Reason] [varchar](250) NULL,
 CONSTRAINT [PK_tbl_order] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_orderDetail]    Script Date: 18/03/2018 15:04:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_orderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productID] [varchar](10) NOT NULL,
	[quantity] [int] NOT NULL,
	[unitPrice] [float] NOT NULL,
	[total] [float] NOT NULL,
	[orderID] [varchar](10) NOT NULL,
 CONSTRAINT [PK_tbl_orderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tbl_book] ([bookID], [title], [price], [quantity]) VALUES (N'book1', N'Morning coffee', 75, 5)
INSERT [dbo].[tbl_book] ([bookID], [title], [price], [quantity]) VALUES (N'book2', N'I see yellow flowers on the grass', 82, 19)
INSERT [dbo].[tbl_book] ([bookID], [title], [price], [quantity]) VALUES (N'book3', N'Introduction to Java Web ', 13, 20)
INSERT [dbo].[tbl_book] ([bookID], [title], [price], [quantity]) VALUES (N'book4', N'Sofware Engineering', 45, 3)
INSERT [dbo].[tbl_customer] ([custID], [password], [custName], [lastName], [middleName], [address], [phone], [custLevel]) VALUES (N'datnd', N'1', N'Dat', N'Nguyen', N'Duy', N'417/26/13D Quang Trung, Go Vap Dist, Ho Chi Minh City', N'01627962333', 1)
INSERT [dbo].[tbl_customer] ([custID], [password], [custName], [lastName], [middleName], [address], [phone], [custLevel]) VALUES (N'datnd123', N'12345678', N'Dat', N'Nguyen', N'Duy', N'529 Truong Chinh St, Pleiku city, Gia Lai, Viet Nam', N'01627962333', 0)
INSERT [dbo].[tbl_customer] ([custID], [password], [custName], [lastName], [middleName], [address], [phone], [custLevel]) VALUES (N'hieubt', N'234432', N'Hieu', N'Bui', N'Trung', N'557 Le Loi', N'0165777886', 0)
INSERT [dbo].[tbl_customer] ([custID], [password], [custName], [lastName], [middleName], [address], [phone], [custLevel]) VALUES (N'linhnt', N'12345678', N'Linh', N'Nguyen', N'Thi', N'112 Benh vien tam than', N'0168999567', 0)
INSERT [dbo].[tbl_customer] ([custID], [password], [custName], [lastName], [middleName], [address], [phone], [custLevel]) VALUES (N'phuongnt', N'1', N'Phuong', N'Nguyen', N'Thi', N'529 Truong Chinh, Pleiku city', N'0976088128', 1)
INSERT [dbo].[tbl_customer] ([custID], [password], [custName], [lastName], [middleName], [address], [phone], [custLevel]) VALUES (N'phuongnt12', N'12345678', N'Phuong', N'Nguyen', N'Thi', N'222 Quang Trung, Phuong 10, Go Vap', N'0976088128', 0)
INSERT [dbo].[tbl_order] ([orderID], [orderDate], [custID], [total], [isDeliver], [Reason]) VALUES (N'o1', CAST(N'2018-03-12 00:00:00.000' AS DateTime), N'datnd', 26, 1, NULL)
INSERT [dbo].[tbl_order] ([orderID], [orderDate], [custID], [total], [isDeliver], [Reason]) VALUES (N'o2', CAST(N'2018-03-10 00:00:00.000' AS DateTime), N'datnd', 75, 1, NULL)
INSERT [dbo].[tbl_order] ([orderID], [orderDate], [custID], [total], [isDeliver], [Reason]) VALUES (N'o3', CAST(N'2018-03-11 00:00:00.000' AS DateTime), N'phuongnt', 120, 0, N'Customer wasn''t at home')
INSERT [dbo].[tbl_order] ([orderID], [orderDate], [custID], [total], [isDeliver], [Reason]) VALUES (N'o4', CAST(N'2018-03-13 00:00:00.000' AS DateTime), N'hieubt', 99, 0, NULL)
SET IDENTITY_INSERT [dbo].[tbl_orderDetail] ON 

INSERT [dbo].[tbl_orderDetail] ([id], [productID], [quantity], [unitPrice], [total], [orderID]) VALUES (2, N'book3', 2, 13, 26, N'o1')
INSERT [dbo].[tbl_orderDetail] ([id], [productID], [quantity], [unitPrice], [total], [orderID]) VALUES (3, N'book1', 1, 75, 75, N'o2')
INSERT [dbo].[tbl_orderDetail] ([id], [productID], [quantity], [unitPrice], [total], [orderID]) VALUES (4, N'book4', 1, 45, 45, N'o3')
INSERT [dbo].[tbl_orderDetail] ([id], [productID], [quantity], [unitPrice], [total], [orderID]) VALUES (5, N'book1', 1, 75, 75, N'o3')
INSERT [dbo].[tbl_orderDetail] ([id], [productID], [quantity], [unitPrice], [total], [orderID]) VALUES (6, N'book2', 1, 82, 82, N'o4')
INSERT [dbo].[tbl_orderDetail] ([id], [productID], [quantity], [unitPrice], [total], [orderID]) VALUES (7, N'book3', 1, 13, 13, N'o4')
SET IDENTITY_INSERT [dbo].[tbl_orderDetail] OFF
ALTER TABLE [dbo].[tbl_order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_order_tbl_customer] FOREIGN KEY([custID])
REFERENCES [dbo].[tbl_customer] ([custID])
GO
ALTER TABLE [dbo].[tbl_order] CHECK CONSTRAINT [FK_tbl_order_tbl_customer]
GO
ALTER TABLE [dbo].[tbl_orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_orderDetail_tbl_book] FOREIGN KEY([productID])
REFERENCES [dbo].[tbl_book] ([bookID])
GO
ALTER TABLE [dbo].[tbl_orderDetail] CHECK CONSTRAINT [FK_tbl_orderDetail_tbl_book]
GO
ALTER TABLE [dbo].[tbl_orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_orderDetail_tbl_order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_order] ([orderID])
GO
ALTER TABLE [dbo].[tbl_orderDetail] CHECK CONSTRAINT [FK_tbl_orderDetail_tbl_order]
GO
USE [master]
GO
ALTER DATABASE [DeliveryManagement] SET  READ_WRITE 
GO
