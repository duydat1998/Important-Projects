USE [master]
GO
/****** Object:  Database [WorkShop1]    Script Date: 10/03/2018 22:48:45 ******/
CREATE DATABASE [WorkShop1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'WorkShop1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQL_EXPRESS\MSSQL\DATA\WorkShop1.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'WorkShop1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQL_EXPRESS\MSSQL\DATA\WorkShop1_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [WorkShop1] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [WorkShop1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [WorkShop1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [WorkShop1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [WorkShop1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [WorkShop1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [WorkShop1] SET ARITHABORT OFF 
GO
ALTER DATABASE [WorkShop1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [WorkShop1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [WorkShop1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [WorkShop1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [WorkShop1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [WorkShop1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [WorkShop1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [WorkShop1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [WorkShop1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [WorkShop1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [WorkShop1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [WorkShop1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [WorkShop1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [WorkShop1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [WorkShop1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [WorkShop1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [WorkShop1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [WorkShop1] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [WorkShop1] SET  MULTI_USER 
GO
ALTER DATABASE [WorkShop1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [WorkShop1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [WorkShop1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [WorkShop1] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [WorkShop1] SET DELAYED_DURABILITY = DISABLED 
GO
USE [WorkShop1]
GO
/****** Object:  Table [dbo].[tbl_Mobile]    Script Date: 10/03/2018 22:48:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Mobile](
	[mobileId] [varchar](10) NOT NULL,
	[description] [varchar](250) NOT NULL,
	[price] [float] NULL,
	[mobileName] [varchar](20) NOT NULL,
	[yearOfProduction] [int] NULL,
	[quantity] [int] NULL,
	[notSale] [bit] NULL,
 CONSTRAINT [PK_tbl_Mobile] PRIMARY KEY CLUSTERED 
(
	[mobileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_Order]    Script Date: 10/03/2018 22:48:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Order](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[total] [int] NULL,
	[userId] [varchar](20) NULL,
	[totalAmount] [float] NULL,
 CONSTRAINT [PK_tbl_Order] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_OrderDetail]    Script Date: 10/03/2018 22:48:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_OrderDetail](
	[orderDetailNo] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [int] NULL,
	[mobileId] [varchar](10) NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_tbl_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderDetailNo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 10/03/2018 22:48:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_User](
	[userId] [varchar](20) NOT NULL,
	[password] [int] NOT NULL,
	[fullName] [varchar](50) NOT NULL,
	[role] [int] NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'a', N'a', NULL, N'a', NULL, NULL, 1)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'HTCuUltra', N'New design, new life', NULL, N'HTC U Ultra', 2016, NULL, 1)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'HWi2', N'Infinitive display, dual camera', 160.3, N'Nova 2i', 2017, 3, 0)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'OPf3', N'Selfie Expert', 130, N'Oppo F3', 2016, 5, 0)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'SS7', N'flagship of 2016, super sexy edge', NULL, N'Galaxy S7', NULL, 0, 1)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'SSs8', N'One of flagship of 2017, infinitve display', 845, N'Galaxy S8', 2017, 20, 0)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'SSs8p', N'One of flagship of 2017, infinitive display', 900, N'Galaxy S8 Plus', 2017, 17, 0)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'SSs9', N'Flagship 2018', NULL, N'Galaxy S9', 2018, 0, 1)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'SSs9p', N'Flagship 2018', NULL, N'Galaxy S9 Plus', 2018, 0, 1)
SET IDENTITY_INSERT [dbo].[tbl_Order] ON 

INSERT [dbo].[tbl_Order] ([orderId], [total], [userId], [totalAmount]) VALUES (1, 1, N'datndus', NULL)
INSERT [dbo].[tbl_Order] ([orderId], [total], [userId], [totalAmount]) VALUES (2, 3, N'datndus', NULL)
INSERT [dbo].[tbl_Order] ([orderId], [total], [userId], [totalAmount]) VALUES (3, 1, N'hieubtus', NULL)
INSERT [dbo].[tbl_Order] ([orderId], [total], [userId], [totalAmount]) VALUES (4, 3, N'datndus', 2590)
SET IDENTITY_INSERT [dbo].[tbl_Order] OFF
SET IDENTITY_INSERT [dbo].[tbl_OrderDetail] ON 

INSERT [dbo].[tbl_OrderDetail] ([orderDetailNo], [orderId], [mobileId], [quantity]) VALUES (1, 1, N'HTCuUltra', 1)
INSERT [dbo].[tbl_OrderDetail] ([orderDetailNo], [orderId], [mobileId], [quantity]) VALUES (2, 2, N'SSs8', 2)
INSERT [dbo].[tbl_OrderDetail] ([orderDetailNo], [orderId], [mobileId], [quantity]) VALUES (3, 2, N'SSs8p', 1)
INSERT [dbo].[tbl_OrderDetail] ([orderDetailNo], [orderId], [mobileId], [quantity]) VALUES (4, 3, N'SSs8p', 1)
INSERT [dbo].[tbl_OrderDetail] ([orderDetailNo], [orderId], [mobileId], [quantity]) VALUES (5, 4, N'SSs8', 2)
INSERT [dbo].[tbl_OrderDetail] ([orderDetailNo], [orderId], [mobileId], [quantity]) VALUES (6, 4, N'SSs8p', 1)
SET IDENTITY_INSERT [dbo].[tbl_OrderDetail] OFF
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'datndmg', 1, N'Nguyen Duy Dat', 1)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'datndst', 1, N'Nguyen Duy Dat', 2)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'datndus', 1, N'Nguyen Duy Dat', 0)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'hieubtus', 123456, N'Bui Trung Hieu', 0)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'hoangdmst', 123456, N'Dao Minh Hoang', 2)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'huylmus', 123456, N'Le Minh Huy', 0)
USE [master]
GO
ALTER DATABASE [WorkShop1] SET  READ_WRITE 
GO
